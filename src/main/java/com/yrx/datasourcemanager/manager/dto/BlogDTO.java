package com.yrx.datasourcemanager.manager.dto;

import com.yrx.datasourcemanager.manager.pojo.Blog;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

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

    public static BlogDTO convertBlogToDto(Blog blog) {
        BlogDTO dto = new BlogDTO();
        BeanUtils.copyProperties(blog, dto);
        return dto;
    }
}
