package com.yrx.datasourcemanager.manager.service.paramProcessImpl;

import com.yrx.datasourcemanager.manager.dao.extend.ParamConfigExtendMapper;
import com.yrx.datasourcemanager.manager.pojo.ParamConfig;
import com.yrx.datasourcemanager.manager.pojo.ParamProcessStep;
import com.yrx.datasourcemanager.manager.service.IParamProcessStepService;
import com.yrx.datasourcemanager.manager.vo.ApiInvokeVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by r.x on 2019/9/3.
 */
public class SortListProcessServiceImpl implements IParamProcessStepService<List> {
    @Autowired
    private ParamConfigExtendMapper paramConfigMapper;

    @Override
    public List processParam(ParamProcessStep paramProcessStep, ApiInvokeVO vo, Map<String, Object> lastStepResult) {
        String processValue = paramProcessStep.getProcessValue();
        List<Integer> paramConfigIds = Arrays.stream(processValue.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        Map<String, Object> pairs = new HashMap<>();
        List<ParamConfig> paramConfigs = paramConfigMapper.listParamByIds(paramConfigIds);
        // Map<String, Object> pairs = vo.getParams().stream()
        //         .filter(item -> item.getParamName().equals(paramConfig.getParamName())
        //                 && item.getParamType().equals(paramConfig.getParamType()))
        //         .collect(Collectors.toMap(ApiInvokeVO.ParamVO::getParamName, ApiInvokeVO.ParamVO::getParamValue));
        return pairs.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.toList());
    }
}
