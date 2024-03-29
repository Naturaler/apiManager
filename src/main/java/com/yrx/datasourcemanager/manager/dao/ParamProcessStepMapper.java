package com.yrx.datasourcemanager.manager.dao;

import com.yrx.datasourcemanager.manager.pojo.ParamProcessStep;
import com.yrx.datasourcemanager.manager.pojo.ParamProcessStepExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface ParamProcessStepMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_process_step
     *
     * @mbg.generated Mon Sep 02 00:18:58 CST 2019
     */
    @SelectProvider(type=ParamProcessStepSqlProvider.class, method="countByExample")
    long countByExample(ParamProcessStepExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_process_step
     *
     * @mbg.generated Mon Sep 02 00:18:58 CST 2019
     */
    @DeleteProvider(type=ParamProcessStepSqlProvider.class, method="deleteByExample")
    int deleteByExample(ParamProcessStepExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_process_step
     *
     * @mbg.generated Mon Sep 02 00:18:58 CST 2019
     */
    @Delete({
        "delete from param_process_step",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_process_step
     *
     * @mbg.generated Mon Sep 02 00:18:58 CST 2019
     */
    @Insert({
        "insert into param_process_step (api_id, param_id, ",
        "process_type, process_value, ",
        "create_author, create_time, ",
        "update_author, update_time)",
        "values (#{apiId,jdbcType=INTEGER}, #{paramId,jdbcType=INTEGER}, ",
        "#{processType,jdbcType=VARCHAR}, #{processValue,jdbcType=VARCHAR}, ",
        "#{createAuthor,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateAuthor,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ParamProcessStep record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_process_step
     *
     * @mbg.generated Mon Sep 02 00:18:58 CST 2019
     */
    @InsertProvider(type=ParamProcessStepSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ParamProcessStep record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_process_step
     *
     * @mbg.generated Mon Sep 02 00:18:58 CST 2019
     */
    @SelectProvider(type=ParamProcessStepSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="api_id", property="apiId", jdbcType=JdbcType.INTEGER),
        @Result(column="param_id", property="paramId", jdbcType=JdbcType.INTEGER),
        @Result(column="process_type", property="processType", jdbcType=JdbcType.VARCHAR),
        @Result(column="process_value", property="processValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_author", property="createAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_author", property="updateAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ParamProcessStep> selectByExample(ParamProcessStepExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_process_step
     *
     * @mbg.generated Mon Sep 02 00:18:58 CST 2019
     */
    @Select({
        "select",
        "id, api_id, param_id, process_type, process_value, create_author, create_time, ",
        "update_author, update_time",
        "from param_process_step",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="api_id", property="apiId", jdbcType=JdbcType.INTEGER),
        @Result(column="param_id", property="paramId", jdbcType=JdbcType.INTEGER),
        @Result(column="process_type", property="processType", jdbcType=JdbcType.VARCHAR),
        @Result(column="process_value", property="processValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_author", property="createAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_author", property="updateAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ParamProcessStep selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_process_step
     *
     * @mbg.generated Mon Sep 02 00:18:58 CST 2019
     */
    @UpdateProvider(type=ParamProcessStepSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ParamProcessStep record, @Param("example") ParamProcessStepExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_process_step
     *
     * @mbg.generated Mon Sep 02 00:18:58 CST 2019
     */
    @UpdateProvider(type=ParamProcessStepSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ParamProcessStep record, @Param("example") ParamProcessStepExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_process_step
     *
     * @mbg.generated Mon Sep 02 00:18:58 CST 2019
     */
    @UpdateProvider(type=ParamProcessStepSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ParamProcessStep record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table param_process_step
     *
     * @mbg.generated Mon Sep 02 00:18:58 CST 2019
     */
    @Update({
        "update param_process_step",
        "set api_id = #{apiId,jdbcType=INTEGER},",
          "param_id = #{paramId,jdbcType=INTEGER},",
          "process_type = #{processType,jdbcType=VARCHAR},",
          "process_value = #{processValue,jdbcType=VARCHAR},",
          "create_author = #{createAuthor,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_author = #{updateAuthor,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ParamProcessStep record);
}