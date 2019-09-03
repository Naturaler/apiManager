package com.yrx.datasourcemanager.manager.service.paramProcessImpl;

import com.yrx.datasourcemanager.manager.constant.ProcessServiceConstant;
import com.yrx.datasourcemanager.manager.pojo.ParamProcessStep;
import com.yrx.datasourcemanager.manager.service.IParamProcessStepService;
import com.yrx.datasourcemanager.manager.vo.ApiInvokeVO;

import java.util.Collections;
import java.util.Map;
import java.util.StringJoiner;

/**
 * Created by r.x on 2019/9/4.
 */
public class JoinStrProcessServiceImpl implements IParamProcessStepService {
    @Override
    public Map<String, Object> processParam(ParamProcessStep paramProcessStep, ApiInvokeVO vo, Map<String, Object> lastStepResult) {
        StringJoiner joiner = new StringJoiner(",");
        lastStepResult.forEach((key, value) -> joiner.add(key + "=" + value));
        return Collections.singletonMap(ProcessServiceConstant.SERVICE_JOIN_STRING, joiner.toString());
    }

    @Override
    public Class resultType() {
        return String.class;
    }
}
