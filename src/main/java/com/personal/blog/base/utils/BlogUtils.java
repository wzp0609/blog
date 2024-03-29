package com.personal.blog.base.utils;

import com.alibaba.fastjson.JSON;
import com.personal.blog.base.lang.Result;
import com.personal.blog.base.lang.Theme;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 工具类
 * @author weizp
 */
@Slf4j
public class BlogUtils {

    private static final Logger log = LoggerFactory.getLogger(BlogUtils.class);

    public static List<Theme> getThemes() {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        List<Theme> themes = null;
        try {
            Resource[] resources = resolver.getResources("templates/*/about.json");
            themes = loadDirectory(resources);
            String location = System.getProperty("site.location");
            if (null != location) {
                themes.addAll(loadDirectory(Paths.get(location, "storage", "templates")));
            }
        } catch (Exception e) {
            log.error("load themes error {}", e.getMessage(), e);
        }
        return themes;
    }

    private static List<Theme> loadDirectory(Path directory) throws IOException {
        if (Files.notExists(directory)) {
            return Collections.emptyList();
        }
        return Files.list(directory).filter(entry -> {
            String name = entry.getFileName().toString();
            return Files.isDirectory(entry) && !StringUtils.equals("__MACOSX", name) && !StringUtils.equals("admin", name);
        }).map(entry -> {
            Theme theme = null;
            try {
                Path about = entry.resolve("about.json");
                if (Files.exists(about)) {
                    StringBuilder json = new StringBuilder();
                    Files.readAllLines(about).forEach(json::append);
                    theme = JSON.parseObject(json.toString(), Theme.class);
                }
            } catch (Exception e) {
                log.error("BlogUtils.loadDirectory error {}", e.getMessage(), e);
            }
            if (null == theme) {
                theme = new Theme();
            }
            theme.setName(entry.getFileName().toString());
            theme.setPath(entry.toString());
            return theme;
        }).collect(Collectors.toList());
    }

    private static List<Theme> loadDirectory(File directory) throws IOException {
        final File[] files = directory.listFiles();

        if (null == files) {
            return Collections.emptyList();
        }

        List<Theme> themes = new ArrayList<>();
        Theme theme = null;
        for (File file : files) {
            String name = file.getName();
            log.info("list item {}", name);
            if (file.isDirectory() && !StringUtils.equals("__MACOSX", name) && !StringUtils.equals("admin", name)) {
                File about = new File(file, "about.json");
                if (about.exists()) {
                    String json = FileUtils.readFileToString(about);
                    theme = JSON.parseObject(json.toString(), Theme.class);
                }
                if (null == theme) {
                    theme = new Theme();
                }
                theme.setName(name);
                theme.setPath(file.getPath());
                themes.add(theme);
            }
        }
        return themes;
    }

    private static List<Theme> loadDirectory(Resource[] resources) throws IOException {
        if (null == resources) {
            return Collections.emptyList();
        }

        List<Theme> themes = new ArrayList<>();
        Theme theme = null;

        for (Resource r : resources) {
            String name = r.getFilename();
            log.info("list item {}", name);
            if (r.exists()) {
                @Cleanup InputStream inputStream = r.getInputStream();
                byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
                String json = new String(bytes);
                theme = JSON.parseObject(json, Theme.class);
                themes.add(theme);
            }
        }
        return themes;
    }
}
