package com.yrx.datasourcemanager.manager.pojo;

import java.util.Date;

public class DataSourceConfig {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_source_config.id
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_source_config.name
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_source_config.create_author
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    private String createAuthor;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_source_config.create_time
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_source_config.update_author
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    private String updateAuthor;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column data_source_config.update_time
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_source_config.id
     *
     * @return the value of data_source_config.id
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_source_config.id
     *
     * @param id the value for data_source_config.id
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_source_config.name
     *
     * @return the value of data_source_config.name
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_source_config.name
     *
     * @param name the value for data_source_config.name
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_source_config.create_author
     *
     * @return the value of data_source_config.create_author
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    public String getCreateAuthor() {
        return createAuthor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_source_config.create_author
     *
     * @param createAuthor the value for data_source_config.create_author
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    public void setCreateAuthor(String createAuthor) {
        this.createAuthor = createAuthor == null ? null : createAuthor.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_source_config.create_time
     *
     * @return the value of data_source_config.create_time
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_source_config.create_time
     *
     * @param createTime the value for data_source_config.create_time
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_source_config.update_author
     *
     * @return the value of data_source_config.update_author
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    public String getUpdateAuthor() {
        return updateAuthor;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_source_config.update_author
     *
     * @param updateAuthor the value for data_source_config.update_author
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    public void setUpdateAuthor(String updateAuthor) {
        this.updateAuthor = updateAuthor == null ? null : updateAuthor.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column data_source_config.update_time
     *
     * @return the value of data_source_config.update_time
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column data_source_config.update_time
     *
     * @param updateTime the value for data_source_config.update_time
     *
     * @mbg.generated Sun Sep 01 17:14:28 CST 2019
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}