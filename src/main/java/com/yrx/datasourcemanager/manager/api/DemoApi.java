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
}
