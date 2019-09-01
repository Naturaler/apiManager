package com.yrx.datasourcemanager.manager.api;

import com.alibaba.fastjson.JSON;
import com.yrx.datasourcemanager.manager.dto.Response;
import com.yrx.datasourcemanager.manager.vo.DataSourceConfigVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by r.x on 2019/9/1.
 * 数据源配置
 */
@RestController
@RequestMapping("/dataSourceConfig")
@Slf4j
public class DataSourceConfigApi {


    @PostMapping("/config")
    public Response<String> config(@RequestBody DataSourceConfigVO vo) {
        log.info(JSON.toJSONString(vo));
        return Response.success("hello world");
    }
}
