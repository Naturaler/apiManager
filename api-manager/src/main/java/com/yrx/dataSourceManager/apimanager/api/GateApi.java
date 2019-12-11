package com.yrx.dataSourceManager.apimanager.api;

import com.yrx.dataSourceManager.apimanager.dto.Response;
import com.yrx.dataSourceManager.apimanager.service.GateService;
import com.yrx.dataSourceManager.apimanager.source.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by r.x on 2019/12/11.
 */
@RestController
@RequestMapping("/gate")
public class GateApi {
    @Autowired
    private GateService gateService;

    @PostMapping("/api")
    public Response api(@RequestBody BaseVO vo) {
        return gateService.api(vo);
    }
}
