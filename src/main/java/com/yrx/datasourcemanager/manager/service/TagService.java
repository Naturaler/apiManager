package com.yrx.datasourcemanager.manager.service;

import com.yrx.datasourcemanager.manager.dao.BlogMapper;
import com.yrx.datasourcemanager.manager.dto.BlogDTO;
import com.yrx.datasourcemanager.manager.dto.Response;
import com.yrx.datasourcemanager.manager.pojo.Blog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by r.x on 2019/10/11.
 */
@Service
@Slf4j
public class TagService {
    @Autowired
    private BlogMapper blogMapper;

    public Response<Set<String>> listTags() {
        List<Blog> blogs = blogMapper.selectByExample(null);
        Set<String> tags = new HashSet<>();
        blogs.stream().map(Blog::getTags).forEach(item -> tags.addAll(Arrays.asList(item.split(","))));
        return Response.success(tags);
    }

    public Response<List<BlogDTO>> listByTag(String tag) {
        List<Blog> blogs = blogMapper.selectByExample(null);
        return Response.success(BlogDTO.convertBlogToDto(blogs));
    }
}
