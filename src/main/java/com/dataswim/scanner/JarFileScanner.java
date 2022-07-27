package com.dataswim.scanner;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;

import java.io.File;
import java.util.*;

@Slf4j
public class JarFileScanner {

    public List<File> findJarFiles(File dir) {
        List<File> files = new ArrayList<>(FileUtils.listFiles(dir,
                new RegexFileFilter(".*\\.jar"),
                DirectoryFileFilter.DIRECTORY
        ));
        logFoundFiles(dir, files);
        return files;
    }

    private void logFoundFiles(File dir, List<File> files) {
        for (File file : files) {
            log.info("Java file found: " + file.getAbsolutePath().replace(dir.getAbsolutePath(), ""));
        }
    }

}
