package com.yrx.datasourcemanager.manager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by r.x on 2019/10/13.
 */
@Data
public class ArchiveDTO {
    private String year;
    private List<ArchiveBlog> archiveBlogs;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ArchiveBlog {
        private Integer id;
        private String title;
    }
}
