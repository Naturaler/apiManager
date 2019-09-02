package com.yrx.datasourcemanager.manager.service.paramProcessImpl;

import com.yrx.datasourcemanager.manager.pojo.ParamProcessStep;
import com.yrx.datasourcemanager.manager.service.IParamProcessStepService;
import com.yrx.datasourcemanager.manager.vo.ApiInvokeVO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

/**
 * Created by r.x on 2019/9/2.
 */
@Service
public class UuidProcessServiceImpl implements IParamProcessStepService<String> {
    private final String SERVICE_NAME = "创建UUID";

    @Override
    public String processParam(ParamProcessStep paramProcessStep, ApiInvokeVO vo, Map<String, Object> lastStepResult) {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
