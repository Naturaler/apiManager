package com.yrx.datasourcemanager.manager.dao.extend;

import lombok.Data;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.List;

/**
 * Created by r.x on 2019/9/1.
 */
public interface ParamMapper {
    @Select({
            "select",
            "a.id api_id, p.id param_id, p.param_name, p.param_type, m.is_required, m.process_step, m.param_zone",
            "from param_config p",
            "join api_param_mapping m on m.param_id = p.id",
            "join api_config a on a.id = m.api_id",
            "where a.id = #{apiId,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "api_id", property = "apiId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "param_id", property = "paramId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "param_name", property = "paramName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "param_type", property = "paramType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "param_zone", property = "paramZone", jdbcType = JdbcType.TINYINT),
            @Result(column = "process_step", property = "paramProcessSteps", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_required", property = "isRequired", jdbcType = JdbcType.BOOLEAN),
    })
    List<ApiParam> listParamByApiId(Integer apiId);

    @Data
    class ApiParam {
        private Integer apiId;
        private Integer paramId;
        private String paramName;
        private String paramType;
        private Short paramZone;
        private String paramProcessSteps;
        private Boolean isRequired;
    }
}
