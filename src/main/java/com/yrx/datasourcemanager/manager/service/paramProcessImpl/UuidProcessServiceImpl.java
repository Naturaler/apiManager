package com.yrx.datasourcemanager.manager.service.paramProcessImpl;

import com.yrx.datasourcemanager.manager.constant.ProcessServiceConstant;
import com.yrx.datasourcemanager.manager.pojo.ParamProcessStep;
import com.yrx.datasourcemanager.manager.service.IParamProcessStepService;
import com.yrx.datasourcemanager.manager.vo.ApiInvokeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

/**
 * Created by r.x on 2019/9/2.
 */
@Service
@Slf4j
public class UuidProcessServiceImpl implements IParamProcessStepService {
    private final String SERVICE_NAME = "创建UUID";

    @Override
    public Map<String, Object> processParam(ParamProcessStep paramProcessStep, ApiInvokeVO vo, Map<String, Object> lastStepResult) {
        UUID uuid = UUID.randomUUID();
        return Collections.singletonMap(ProcessServiceConstant.SERVICE_UUID, uuid.toString());
    }

    @Override
    public Class resultType() {
        return String.class;
    }
}
