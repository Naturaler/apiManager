package com.yrx.datasourcemanager.manager.service;

import com.yrx.datasourcemanager.manager.dao.BlogMapper;
import com.yrx.datasourcemanager.manager.pojo.Blog;
import com.yrx.datasourcemanager.manager.vo.BlogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        pojo.setTags(vo.getTag());
        pojo.setInsertTime(new Date());
        pojo.setUpdateTime(new Date());
        blogMapper.insert(pojo);
        return "success";
    }

    public String getById(Integer id) {
        return blogMapper.selectByPrimaryKey(id).getBlog();
    }
}
