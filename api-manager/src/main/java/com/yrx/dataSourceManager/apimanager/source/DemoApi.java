package com.yrx.dataSourceManager.apimanager.source;

import com.yrx.dataSourceManager.apimanager.source.vo.Demo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by r.x on 2019/12/2.
 */
@RestController
@RequestMapping("/demo")
public class DemoApi {

    @PostMapping("/post")
    public String post(@RequestBody Demo demo) {
        return "hello world";
    }
}
