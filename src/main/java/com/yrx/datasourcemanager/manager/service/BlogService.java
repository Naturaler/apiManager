package com.yrx.datasourcemanager.manager.service;

import com.yrx.datasourcemanager.manager.dao.BlogMapper;
import com.yrx.datasourcemanager.manager.dto.BlogDTO;
import com.yrx.datasourcemanager.manager.dto.Response;
import com.yrx.datasourcemanager.manager.pojo.Blog;
import com.yrx.datasourcemanager.manager.pojo.BlogExample;
import com.yrx.datasourcemanager.manager.vo.BlogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by r.x on 2019/10/2.
 */
@Service
@Slf4j
public class BlogService {
    @Autowired
    private BlogMapper blogMapper;

    public String save(BlogVO vo) {
        if (vo.getId() != null) {
            Blog blog = blogMapper.selectByPrimaryKey(vo.getId());
            blog.setBlog(vo.getBlog());
            blog.setTitle(vo.getTitle());
            blog.setDescription(vo.getDescription());
            blog.setTags(vo.getTag());
            blog.setUpdateTime(new Date());
            blog.setCategory(vo.getCategory());
            blogMapper.updateByPrimaryKeyWithBLOBs(blog);
            return "update success";
        }
        Blog pojo = new Blog();
        pojo.setBlog(vo.getBlog());
        pojo.setTitle(vo.getTitle());
        pojo.setDescription(vo.getDescription());
        pojo.setTags(vo.getTag());
        pojo.setInsertTime(new Date());
        pojo.setUpdateTime(new Date());
        pojo.setSoftDelete(Byte.valueOf("0"));
        pojo.setCategory(vo.getCategory());
        blogMapper.insert(pojo);
        return "success";
    }

    public String getById(Integer id) {
        return blogMapper.selectByPrimaryKey(id).getBlog();
    }

    public Response<List<BlogDTO>> list() {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        // 只查询未软删除的blog
        criteria.andSoftDeleteEqualTo(Byte.valueOf("0"));
        List<Blog> blogs = blogMapper.selectByExample(example);
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

    public Response<Integer> deleteById(Integer id) {
        Blog blog = blogMapper.selectByPrimaryKey(id);
        blog.setSoftDelete(Byte.valueOf("1"));
        blogMapper.updateByPrimaryKey(blog);
        return Response.success(id);
    }

    public Response<List<String>> listTitles(String keyword) {
        BlogExample example = new BlogExample();
        BlogExample.Criteria criteria = example.createCriteria();
        criteria.andTitleLike("%" + keyword + "%");
        List<Blog> blogs = blogMapper.selectByExample(example);
        List<String> titles = blogs.stream().map(Blog::getTitle).collect(Collectors.toList());
        return Response.success(titles);
    }

    public Response<List<BlogDTO>> search(BlogVO vo) {
        BlogExample example = new BlogExample();

        BlogExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(vo.getTitle())) {
            criteria.andTitleLike("%" + vo.getTitle() + "%");
        }
        List<Blog> blogs = blogMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(blogs)) {
            blogs.addAll(blogMapper.selectByExample(new BlogExample()));
        }
        List<BlogDTO> dtos = BlogDTO.convertBlogToDto(blogs);
        return Response.success(dtos);
    }
}
