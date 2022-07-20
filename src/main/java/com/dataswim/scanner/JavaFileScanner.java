package com.dataswim.scanner;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.io.FileReader;
import java.util.*;

@Slf4j
public class JavaFileScanner {

    public Map<String, JavaClass> findJavaClasses(File dir) {
        return scanJavaFilesForJavaSources(getJavaFiles(dir));
    }

    private List<File> getJavaFiles(File dir) {
        List<File> files = new ArrayList<>(FileUtils.listFiles(dir,
                new RegexFileFilter(".*\\.java"),
                DirectoryFileFilter.DIRECTORY
        ));
        logFoundFiles(files);
        return files;
    }

    private Map<String, JavaClass> scanJavaFilesForJavaSources(List<File> files) {
        Map<String, JavaClass> map = new HashMap<>();
        for (File file : files) {
            List<JavaSource> srcs = getJavaSourcesFromJavaFile(file);
            List<JavaClass> flatClasses = getJavaClassesFromJavaSource(srcs);
            addToMap(flatClasses, map);
        }
        return map;
    }

    private List<JavaSource> getJavaSourcesFromJavaFile(File javaFile) {
        List<JavaSource> srcs = new ArrayList<>();
        try {
            JavaProjectBuilder builder = new JavaProjectBuilder();
            builder.addSource(new FileReader(javaFile));
            srcs = new ArrayList<>(builder.getSources());
        } catch (Exception e) {
            log.error(javaFile.getName() + " " + javaFile.length(), e);
        }
        return srcs;
    }

    private List<JavaClass> getJavaClassesFromJavaSource(List<JavaSource> srcs) {
        List<JavaClass> clazzes = new ArrayList<>();
        for (JavaSource src : srcs) {
            for (JavaClass clazz : src.getClasses()) {
                clazzes.add(clazz);
                clazzes.addAll(scanClassForClasses(clazz.getNestedClasses()));
            }
        }
        return clazzes;
    }

    private List<JavaClass> scanClassForClasses(List<JavaClass> clazzes) {
        List<JavaClass> returnClazzes = new ArrayList<>();
        for (JavaClass clazz : clazzes) {
            returnClazzes.add(clazz);
            returnClazzes.addAll(scanClassForClasses(clazz.getNestedClasses()));
        }
        return returnClazzes;
    }

    private void addToMap(List<JavaClass> flatClasses, Map<String, JavaClass> map) {
        for (JavaClass clazz : flatClasses) {
            String className = clazz.toString();
            if (map.containsKey(className)) {
                log.info("Class already exists: " + className);
                className = className + "_duplicate_" + UUID.randomUUID();
            }
            log.info("Adding class: " + className);
            map.put(className, clazz);
        }
    }

    private void logFoundFiles(List<File> files) {
        for (File file : files) {
            log.info("Java file found: " + file.getAbsolutePath());
        }
    }

}
