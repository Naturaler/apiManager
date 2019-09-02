package com.yrx.datasourcemanager.manager.service;

import com.yrx.datasourcemanager.manager.service.paramProcessImpl.UuidProcessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by r.x on 2019/9/2.
 */
@Component
public class ParamProcessServiceFactory {
    @Autowired
    private UuidProcessServiceImpl uuidProcessService;

    public IParamProcessStepService getProcessByType(String type) {
        switch (type) {
            case "uuid":
                return uuidProcessService;
            default:
                return null;
        }
    }
}
