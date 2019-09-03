package com.yrx.datasourcemanager.manager.service;

import com.yrx.datasourcemanager.manager.service.paramProcessImpl.Base64ProcessServiceImpl;
import com.yrx.datasourcemanager.manager.service.paramProcessImpl.SortListProcessServiceImpl;
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
    @Autowired
    private SortListProcessServiceImpl sortListProcessService;
    @Autowired
    private Base64ProcessServiceImpl base64ProcessService;

    public IParamProcessStepService getProcessByType(String type) {
        switch (type) {
            case "uuid":
                return uuidProcessService;
            case "base64":
                return base64ProcessService;
            case "sortList":
                return sortListProcessService;
            default:
                return null;
        }
    }
}
