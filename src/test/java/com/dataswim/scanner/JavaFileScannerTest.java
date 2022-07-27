package com.dataswim.scanner;

import com.thoughtworks.qdox.model.JavaClass;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class JavaFileScannerTest {

    private final String PROJECT_PATH_TO_SCAN = "";
    private final String DISTRIBUTION_PATH = "";

    @Test
    public void scanJavaFileTree() {
        JavaFileScanner scanner = new JavaFileScanner();
        PackageNodeStructureBuilder builder = new PackageNodeStructureBuilder();
        Map<String, JavaClass> map = scanner.findJavaClasses(Path.of(PROJECT_PATH_TO_SCAN).toFile());
        PackageNode packageNode = new PackageNode("root", "root"); //root, non package prefix initialization
        for(JavaClass clazz : map.values()) {
            builder.addClass(packageNode, clazz);
        }
    }


    @Test
    public void scanJarFileTree() {
        JarFileScanner scanner = new JarFileScanner();
        List<File> list = scanner.findJarFiles(Path.of(DISTRIBUTION_PATH).toFile());
    }

    @Test
    public void scanJavaFiles() {
        JavaFileScanner scanner = new JavaFileScanner();
        List<File> list = scanner.getJavaFiles(Path.of(PROJECT_PATH_TO_SCAN).toFile());
    }
}