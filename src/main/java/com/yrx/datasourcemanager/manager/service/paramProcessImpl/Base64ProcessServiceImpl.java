package com.yrx.datasourcemanager.manager.service.paramProcessImpl;

import com.yrx.datasourcemanager.manager.dao.ParamConfigMapper;
import com.yrx.datasourcemanager.manager.pojo.ParamConfig;
import com.yrx.datasourcemanager.manager.pojo.ParamProcessStep;
import com.yrx.datasourcemanager.manager.service.IParamProcessStepService;
import com.yrx.datasourcemanager.manager.util.encry.Base64Util;
import com.yrx.datasourcemanager.manager.vo.ApiInvokeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * Created by r.x on 2019/9/2.
 */
@Service
public class Base64ProcessServiceImpl implements IParamProcessStepService<String> {
    @Autowired
    private ParamConfigMapper paramConfigMapper;

    @Override
    public String processParam(ParamProcessStep paramProcessStep, ApiInvokeVO vo, Map<String, Object> lastStepResult) {
        String processValue = paramProcessStep.getProcessValue();
        String[] paramConfigIds = processValue.split(",");
        ParamConfig paramConfig = paramConfigMapper.selectByPrimaryKey(Integer.valueOf(paramConfigIds[0]));
        Optional<Object> value = vo.getParams().stream()
                .filter(item -> item.getParamName().equals(paramConfig.getParamName())
                        && item.getParamType().equals(paramConfig.getParamType()))
                .map(ApiInvokeVO.ParamVO::getParamValue)
                .findFirst();
        if (value.isPresent()) {
            return Base64Util.encode(value.toString());
        }
        return null;
    }
}
