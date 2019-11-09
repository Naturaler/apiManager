package com.yrx.datasourcemanager.manager.api;

import com.alibaba.fastjson.JSON;
import com.yrx.datasourcemanager.manager.vo.DemoVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by r.x on 2019/8/31.
 */
@RestController
@RequestMapping("/demo")
public class DemoApi {

    @PostMapping("/post")
    public String postDemo(@RequestBody DemoVO vo) {
        System.out.println(JSON.toJSONString(vo));
        return "接收内容为：" + JSON.toJSONString(vo);
    }

    public static void main(String[] args) {
        DemoApi api = new DemoApi();
        System.out.println("api.getClassName() = " + api.getClassName());
        System.out.println("Thread.currentThread().getStackTrace()[1].getMethodName() = " + Thread.currentThread().getStackTrace()[1].getMethodName());

        System.out.println("Thread.currentThread().getStackTrace()[1].getLineNumber() = " + Thread.currentThread().getStackTrace()[1].getLineNumber());
    }

    private String getClassName() {
        return this.getClass().getName();
    }

}
