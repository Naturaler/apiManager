package com.yrx.datasourcemanager.manager.api;

import com.alibaba.fastjson.JSON;
import com.yrx.datasourcemanager.manager.dto.Response;
import com.yrx.datasourcemanager.manager.service.GatewayService;
import com.yrx.datasourcemanager.manager.vo.ApiInvokeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by r.x on 20|19/9/1.
 * 接口执行入口
 */
@RestController
@RequestMapping("/gateway")
@Slf4j
public class GatewayApi {
    @Autowired
    private GatewayService service;

    @PostMapping("/invokeApi")
    public Response<String> invokeApi(@RequestBody ApiInvokeVO vo) {
        try {
            return service.invokeApi(vo);
        } catch (Exception e) {
            log.error("接口调用异常！vo:{}", JSON.toJSONString(vo), e);
            return Response.fail(e.getMessage());
        }
    }
}
