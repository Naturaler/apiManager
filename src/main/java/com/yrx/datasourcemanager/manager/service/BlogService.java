package com.yrx.datasourcemanager.manager.service;

import com.yrx.datasourcemanager.manager.dao.BlogMapper;
import com.yrx.datasourcemanager.manager.dto.BlogDTO;
import com.yrx.datasourcemanager.manager.dto.Response;
import com.yrx.datasourcemanager.manager.pojo.Blog;
import com.yrx.datasourcemanager.manager.vo.BlogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by r.x on 2019/10/2.
 */
@Service
@Slf4j
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;

    public String save(BlogVO vo) {
        Blog pojo = new Blog();
        pojo.setBlog(vo.getBlog());
        pojo.setTitle(vo.getTitle());
        pojo.setDescription(vo.getDescription());
        pojo.setTags(vo.getTag());
        pojo.setInsertTime(new Date());
        pojo.setUpdateTime(new Date());
        blogMapper.insert(pojo);
        return "success";
    }

    public String getById(Integer id) {
        return blogMapper.selectByPrimaryKey(id).getBlog();
    }

    public Response<List<BlogDTO>> list() {
        List<Blog> blogs = blogMapper.selectByExample(null);
        List<BlogDTO> dtos = new ArrayList<>(blogs.size());
        blogs.forEach(blog -> {
            BlogDTO dto = new BlogDTO();
            BeanUtils.copyProperties(blog, dto);
            dtos.add(dto);
        });
        return Response.success(dtos);
    }

    public Response<BlogDTO> getByBlogId(Integer id) {
        Blog blog = blogMapper.selectByPrimaryKey(id);
        BlogDTO dto = BlogDTO.convertBlogToDto(blog);
        return Response.success(dto);
    }
}
