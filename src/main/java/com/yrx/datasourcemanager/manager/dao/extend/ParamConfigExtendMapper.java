package com.yrx.datasourcemanager.manager.dao.extend;

import com.yrx.datasourcemanager.manager.pojo.ParamConfig;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Created by r.x on 2019/9/2.
 */
public interface ParamConfigExtendMapper {

    @Select({
            "select",
            "id, param_name, param_type, description, create_author, create_time, update_author, ",
            "update_time",
            "from param_config",
            "where id in",
            "<foreach item=\"item\" collection=\"list\" separator=\",\" open=\"(\" close=\")\" index=\"index\">",
            "#{item, jdbcType=INTEGER}",
            "</foreach>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "param_name", property = "paramName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "param_type", property = "paramType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_author", property = "createAuthor", jdbcType = JdbcType.VARCHAR),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_author", property = "updateAuthor", jdbcType = JdbcType.VARCHAR),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP)
    })
    List<ParamConfig> listParamByIds(List<Integer> paramConfigIds);
}
