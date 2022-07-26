package com.dataswim.scanner;

import com.thoughtworks.qdox.model.JavaClass;
import org.testng.annotations.Test;

import java.nio.file.Path;
import java.util.Map;

public class JavaFileScannerTest {

    private final String PROJECT_PATH_TO_SCAN = "";

    @Test
    public void scanFileTree() {
        JavaFileScanner scanner = new JavaFileScanner();
        PackageNodeStructureBuilder builder = new PackageNodeStructureBuilder();
        Map<String, JavaClass> map = scanner.findJavaClasses(Path.of(PROJECT_PATH_TO_SCAN).toFile());
        PackageNode packageNode = new PackageNode("de", "de");
        for(JavaClass clazz : map.values()) {
            builder.addClass(packageNode, clazz);
        }
    }

}