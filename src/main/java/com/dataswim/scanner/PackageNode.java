package com.dataswim.scanner;

import com.thoughtworks.qdox.model.JavaClass;

import java.util.HashMap;
import java.util.Map;

public class PackageNode {

    private final String packageSegment;

    private final String pathToSegment;

    private final Map<String, JavaClass> classes;

    private final Map<String, PackageNode> packages;

    public PackageNode(String packageSegment, String pathToSegment) {
        this.packageSegment = packageSegment;
        this.pathToSegment = pathToSegment;
        this.classes = new HashMap<>();
        this.packages = new HashMap<>();
    }

    public String getPackageSegment() {
        return packageSegment;
    }

    public String getPathToSegment() {
        return pathToSegment;
    }

    public Map<String, JavaClass> getPackageClasses() {
        return classes;
    }

    public Map<String, PackageNode> getSubPackages() {
        return packages;
    }

    public void addPackageClasses(JavaClass clazz) {
        classes.put(clazz.toString(), clazz);
    }

    public void addPackageNode(PackageNode packageNode) {
        packages.put(packageNode.getPathToSegment(), packageNode);
    }


}
