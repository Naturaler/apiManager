package com.yrx.datasourcemanager.manager.api;

import com.yrx.datasourcemanager.manager.dto.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by r.x on 2019/10/19.
 */
@RestController
@RequestMapping("/config")
public class ConfigApi {

    @GetMapping("/listCategories")
    public Response<List<String>> listCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("代码片段");
        categories.add("基础入门");
        categories.add("心灵鸡汤");
        return Response.success(categories);
    }
}
