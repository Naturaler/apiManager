package com.yrx.dataSourceManager.apimanager.source;

import com.yrx.dataSourceManager.apimanager.annotation.ApiDesc;
import com.yrx.dataSourceManager.apimanager.pojo.ApiDocument;
import com.yrx.dataSourceManager.apimanager.source.vo.Demo;
import com.yrx.dataSourceManager.apimanager.util.ApiDocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by r.x on 2019/12/2.
 */
@RestController
@RequestMapping("/demo")
@ApiDesc("demo")
public class DemoApi {
    @Autowired
    private ApiDocumentHelper apiDocumentHelper;

    @ApiDesc("post")
    @PostMapping("/post")
    public String post(@RequestBody Demo demo) {
        List<ApiDocument> apiDocuments = apiDocumentHelper.parseApi(DemoApi.class);
        for (ApiDocument apiDocument : apiDocuments) {
            System.out.println("apiDocument.toString() = " + apiDocument.toString());
        }
        return "hello world";
    }

    @ApiDesc("get")
    @GetMapping("/get")
    public Demo get(@RequestParam String id) {
        return new Demo() {
            {
                setUsername(id);
                setPassword(id);
            }
        };
    }
}
