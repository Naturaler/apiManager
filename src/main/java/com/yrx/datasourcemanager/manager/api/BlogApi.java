package com.yrx.datasourcemanager.manager.api;

import com.yrx.datasourcemanager.manager.dto.BlogDTO;
import com.yrx.datasourcemanager.manager.dto.Response;
import com.yrx.datasourcemanager.manager.service.BlogService;
import com.yrx.datasourcemanager.manager.vo.BlogVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by r.x on 2019/10/1.
 */
@RestController
@RequestMapping("/blog")
@Slf4j
public class BlogApi {

    @Autowired
    private BlogService blogService;

    @PostMapping("/api")
    public String blog() {
        log.info("blog api");
        String source = "# spring boot整合mybatis \n" +
                "spring boot项目整合mybatis \n" +
                "### jdbc配置 \n" +
                "``` \n" +
                "spring.datasource.url=jdbc:mysql://localhost:3306/data_source?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true \n" +
                "spring.datasource.username=root \n" +
                "spring.datasource.password=root \n" +
                "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver \n" +
                "```\n" +
                "### mybatis相关配置 \n" +
                "``` xml\n" +
                "<?xml version=\\1.0\\ encoding=\\UTF-8\\?> \n" +
                "<!DOCTYPE generatorConfiguration \n" +
                "  PUBLIC \\-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN\\ \n" +
                "  \\http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd\\> \n" +
                " \n" +
                "<generatorConfiguration> \n" +
                "    <classPathEntry location=\\C:\\\\Users\\\\r.x\\\\.m2\\\\repository\\\\mysql\\\\mysql-connector-java\\\\8.0.17\\\\mysql-connector-java-8.0.17.jar\\ /> \n" +
                " \n" +
                "    <context id=\\DB2Tables\\ targetRuntime=\\MyBatis3\\> \n" +
                "        <jdbcConnection driverClass=\\com.mysql.cj.jdbc.Driver\\ \n" +
                "                        connectionURL=\\jdbc:mysql://localhost:3306/data_source?serverTimezone=UTC\\ \n" +
                "                        userId=\\root\\ \n" +
                "                        password=\\root\\> \n" +
                "```\n" +
                "### mapper代码示例 \n" +
                "``` java\n" +
                " \n" +
                "package com.yrx.datasourcemanager.manager.dao.extend; \n" +
                " \n" +
                "import com.yrx.datasourcemanager.manager.pojo.ParamConfig; \n" +
                "import org.apache.ibatis.annotations.Result; \n" +
                "import org.apache.ibatis.annotations.Results; \n" +
                "import org.apache.ibatis.annotations.Select; \n" +
                "import org.apache.ibatis.type.JdbcType; \n" +
                " \n" +
                "import java.util.List; \n" +
                " \n" +
                "/** \n" +
                " * Created by r.x on 2019/9/2. \n" +
                " */ \n" +
                "public interface ParamConfigExtendMapper { \n" +
                "} \n" +
                "```";
        return source;
    }

    @GetMapping("/list")
    public Response<List<BlogDTO>> list() {
        return blogService.list();
    }

    @PostMapping("/get")
    public String getById(@RequestParam Integer id) {
        return blogService.getById(id);
    }

    @PostMapping("/getByBlogId")
    public Response<BlogDTO> getByBlogId(@RequestParam Integer id) {
        return blogService.getByBlogId(id);
    }

    @PostMapping("/save")
    public String save(@RequestBody BlogVO vo) {
        return blogService.save(vo);
    }
}
