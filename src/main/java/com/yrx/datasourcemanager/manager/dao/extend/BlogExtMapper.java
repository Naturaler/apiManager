package com.yrx.datasourcemanager.manager.dao.extend;

import lombok.Data;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Created by r.x on 2019/10/13.
 */
public interface BlogExtMapper {

    @Select({
            "select",
            "year(insert_time) blog_year, id, title",
            "from blog",
            "group by blog_year, id, title",
            "order by insert_time desc"
    })
    @Results({
            @Result(column = "blog_year", property = "year", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
    })
    List<Archive> listArchives();

    @Data
    class Archive {
        private String year;
        private Integer id;
        private String title;
    }
}
