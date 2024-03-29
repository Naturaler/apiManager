package com.yrx.datasourcemanager.manager.dao;

import com.yrx.datasourcemanager.manager.pojo.DataSourceConfig;
import com.yrx.datasourcemanager.manager.pojo.DataSourceConfigExample;
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

public interface DataSourceConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_source_config
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    @SelectProvider(type=DataSourceConfigSqlProvider.class, method="countByExample")
    long countByExample(DataSourceConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_source_config
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    @DeleteProvider(type=DataSourceConfigSqlProvider.class, method="deleteByExample")
    int deleteByExample(DataSourceConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_source_config
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    @Delete({
        "delete from data_source_config",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_source_config
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    @Insert({
        "insert into data_source_config (name, create_author, ",
        "create_time, update_author, ",
        "update_time)",
        "values (#{name,jdbcType=VARCHAR}, #{createAuthor,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateAuthor,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(DataSourceConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_source_config
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    @InsertProvider(type=DataSourceConfigSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(DataSourceConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_source_config
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    @SelectProvider(type=DataSourceConfigSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_author", property="createAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_author", property="updateAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<DataSourceConfig> selectByExample(DataSourceConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_source_config
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    @Select({
        "select",
        "id, name, create_author, create_time, update_author, update_time",
        "from data_source_config",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_author", property="createAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_author", property="updateAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    DataSourceConfig selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_source_config
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    @UpdateProvider(type=DataSourceConfigSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") DataSourceConfig record, @Param("example") DataSourceConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_source_config
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    @UpdateProvider(type=DataSourceConfigSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") DataSourceConfig record, @Param("example") DataSourceConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_source_config
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    @UpdateProvider(type=DataSourceConfigSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(DataSourceConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table data_source_config
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    @Update({
        "update data_source_config",
        "set name = #{name,jdbcType=VARCHAR},",
          "create_author = #{createAuthor,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_author = #{updateAuthor,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(DataSourceConfig record);
}