package com.yrx.datasourcemanager.manager.dao;

import com.yrx.datasourcemanager.manager.pojo.ApiConfig;
import com.yrx.datasourcemanager.manager.pojo.ApiConfigExample;
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

public interface ApiConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_config
     *
     * @mbg.generated Sun Sep 01 20:48:26 CST 2019
     */
    @SelectProvider(type=ApiConfigSqlProvider.class, method="countByExample")
    long countByExample(ApiConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_config
     *
     * @mbg.generated Sun Sep 01 20:48:26 CST 2019
     */
    @DeleteProvider(type=ApiConfigSqlProvider.class, method="deleteByExample")
    int deleteByExample(ApiConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_config
     *
     * @mbg.generated Sun Sep 01 20:48:26 CST 2019
     */
    @Delete({
        "delete from api_config",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_config
     *
     * @mbg.generated Sun Sep 01 20:48:26 CST 2019
     */
    @Insert({
        "insert into api_config (data_source_id, api_name, ",
        "api_type, http_url, ",
        "http_type, bill_type, ",
        "bill_field_path, bill_value, ",
        "api_docs, create_author, ",
        "create_time, update_author, ",
        "update_time)",
        "values (#{dataSourceId,jdbcType=INTEGER}, #{apiName,jdbcType=VARCHAR}, ",
        "#{apiType,jdbcType=VARCHAR}, #{httpUrl,jdbcType=VARCHAR}, ",
        "#{httpType,jdbcType=VARCHAR}, #{billType,jdbcType=VARCHAR}, ",
        "#{billFieldPath,jdbcType=VARCHAR}, #{billValue,jdbcType=VARCHAR}, ",
        "#{apiDocs,jdbcType=VARCHAR}, #{createAuthor,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateAuthor,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(ApiConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_config
     *
     * @mbg.generated Sun Sep 01 20:48:26 CST 2019
     */
    @InsertProvider(type=ApiConfigSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(ApiConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_config
     *
     * @mbg.generated Sun Sep 01 20:48:26 CST 2019
     */
    @SelectProvider(type=ApiConfigSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="data_source_id", property="dataSourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="api_name", property="apiName", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_type", property="apiType", jdbcType=JdbcType.VARCHAR),
        @Result(column="http_url", property="httpUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="http_type", property="httpType", jdbcType=JdbcType.VARCHAR),
        @Result(column="bill_type", property="billType", jdbcType=JdbcType.VARCHAR),
        @Result(column="bill_field_path", property="billFieldPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="bill_value", property="billValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_docs", property="apiDocs", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_author", property="createAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_author", property="updateAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<ApiConfig> selectByExample(ApiConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_config
     *
     * @mbg.generated Sun Sep 01 20:48:26 CST 2019
     */
    @Select({
        "select",
        "id, data_source_id, api_name, api_type, http_url, http_type, bill_type, bill_field_path, ",
        "bill_value, api_docs, create_author, create_time, update_author, update_time",
        "from api_config",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="data_source_id", property="dataSourceId", jdbcType=JdbcType.INTEGER),
        @Result(column="api_name", property="apiName", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_type", property="apiType", jdbcType=JdbcType.VARCHAR),
        @Result(column="http_url", property="httpUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="http_type", property="httpType", jdbcType=JdbcType.VARCHAR),
        @Result(column="bill_type", property="billType", jdbcType=JdbcType.VARCHAR),
        @Result(column="bill_field_path", property="billFieldPath", jdbcType=JdbcType.VARCHAR),
        @Result(column="bill_value", property="billValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="api_docs", property="apiDocs", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_author", property="createAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_author", property="updateAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    ApiConfig selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_config
     *
     * @mbg.generated Sun Sep 01 20:48:26 CST 2019
     */
    @UpdateProvider(type=ApiConfigSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") ApiConfig record, @Param("example") ApiConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_config
     *
     * @mbg.generated Sun Sep 01 20:48:26 CST 2019
     */
    @UpdateProvider(type=ApiConfigSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") ApiConfig record, @Param("example") ApiConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_config
     *
     * @mbg.generated Sun Sep 01 20:48:26 CST 2019
     */
    @UpdateProvider(type=ApiConfigSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(ApiConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table api_config
     *
     * @mbg.generated Sun Sep 01 20:48:26 CST 2019
     */
    @Update({
        "update api_config",
        "set data_source_id = #{dataSourceId,jdbcType=INTEGER},",
          "api_name = #{apiName,jdbcType=VARCHAR},",
          "api_type = #{apiType,jdbcType=VARCHAR},",
          "http_url = #{httpUrl,jdbcType=VARCHAR},",
          "http_type = #{httpType,jdbcType=VARCHAR},",
          "bill_type = #{billType,jdbcType=VARCHAR},",
          "bill_field_path = #{billFieldPath,jdbcType=VARCHAR},",
          "bill_value = #{billValue,jdbcType=VARCHAR},",
          "api_docs = #{apiDocs,jdbcType=VARCHAR},",
          "create_author = #{createAuthor,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_author = #{updateAuthor,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(ApiConfig record);
}