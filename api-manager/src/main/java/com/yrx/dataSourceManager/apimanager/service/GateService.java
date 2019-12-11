package com.yrx.dataSourceManager.apimanager.service;

import com.yrx.dataSourceManager.apimanager.dto.Response;
import com.yrx.dataSourceManager.apimanager.source.ApiFactory;
import com.yrx.dataSourceManager.apimanager.source.api.IApi;
import com.yrx.dataSourceManager.apimanager.source.vo.BaseVO;
import org.springframework.stereotype.Service;

/**
 * Created by r.x on 2019/12/11.
 */
@Service
public class GateService {

    public Response api(BaseVO vo) {
        ApiFactory factory = new ApiFactory();
        IApi iApi = factory.getApiByCode(vo.getApiCode());

        return Response.success("");
    }
}
