package com.yrx.datasourcemanager.manager.api;

import com.alibaba.fastjson.JSON;
import com.yrx.datasourcemanager.manager.service.es.EsDemoService;
import com.yrx.datasourcemanager.manager.vo.DemoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by r.x on 2019/8/31.
 */
@RestController
@RequestMapping("/demo")
public class DemoApi {
    @Autowired
    private EsDemoService esDemoService;

    @PostMapping("/post")
    public String postDemo(@RequestBody DemoVO vo) {
        System.out.println(JSON.toJSONString(vo));
        return "接收内容为：" + JSON.toJSONString(vo);
    }

    @GetMapping("/es")
    public String esTest() {
        esDemoService.testEs();
        return "success";
    }

    public static void main(String[] args) {
        DemoApi api = new DemoApi();
        System.out.println("api.getClassName() = " + api.getClassName());
        System.out.println("Thread.currentThread().getStackTrace()[1].getMethodName() = " + Thread.currentThread().getStackTrace()[1].getMethodName());

        System.out.println("Thread.currentThread().getStackTrace()[1].getLineNumber() = " + Thread.currentThread().getStackTrace()[1].getLineNumber());
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
    }

    private String getClassName() {
        return this.getClass().getName();
    }

}
