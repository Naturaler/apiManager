package com.yrx.datasourcemanager.manager.dto;

import com.yrx.datasourcemanager.manager.pojo.Blog;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by r.x on 2019/10/3.
 */
@Data
public class BlogDTO {
    private Integer id;
    private String title;
    private String description;
    private String blog;
    private Date insertTime;
    private String tags;
    private String category;

    public static BlogDTO convertBlogToDto(Blog blog) {
        BlogDTO dto = new BlogDTO();
        BeanUtils.copyProperties(blog, dto);
        return dto;
    }

    public static List<BlogDTO> convertBlogToDto(List<Blog> blogs) {
        return blogs.stream().map(item -> convertBlogToDto(item)).collect(Collectors.toList());
    }
}
