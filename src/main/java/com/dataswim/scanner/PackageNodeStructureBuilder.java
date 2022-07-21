package com.dataswim.scanner;

import com.thoughtworks.qdox.model.JavaClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PackageNodeStructureBuilder {

    public PackageNode addClass(PackageNode root, JavaClass javaClass) {
        String packageName = javaClass.getPackageName();
        String[] path = packageName.split("\\.");
        PackageNode targetNode = root;
        for(int i = 0; i < path.length; i++) {
            String recentPath = getRecentPath(path, i);
            if(targetNode.getPathToSegment().equals(packageName)) {
                break;
            } else {
                if(targetNode.getSubPackages().containsKey(recentPath)) {
                    targetNode = targetNode.getSubPackages().get(recentPath);
                } else {
                    PackageNode newNode = new PackageNode(path[i], recentPath);
                    targetNode.addPackageNode(newNode);
                    targetNode = newNode;
                }
            }
        }
        targetNode.addPackageClasses(javaClass);
        return targetNode;
    }

    private String getRecentPath(String[] path, int i) {
        return Arrays.stream(path)
                .map(Object::toString)
                .limit(i + 1)
                .collect(Collectors.joining("."));
    }

}
