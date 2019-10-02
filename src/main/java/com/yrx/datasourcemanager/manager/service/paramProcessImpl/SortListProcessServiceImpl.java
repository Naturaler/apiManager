package com.yrx.datasourcemanager.manager.service.paramProcessImpl;

import com.yrx.datasourcemanager.manager.constant.ProcessServiceConstant;
import com.yrx.datasourcemanager.manager.dao.extend.ParamConfigExtendMapper;
import com.yrx.datasourcemanager.manager.pojo.ParamProcessStep;
import com.yrx.datasourcemanager.manager.service.IParamProcessStepService;
import com.yrx.datasourcemanager.manager.vo.ApiInvokeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by r.x on 2019/9/3.
 */
@Slf4j
@Service
public class SortListProcessServiceImpl implements IParamProcessStepService {
    @Autowired
    private ParamConfigExtendMapper paramConfigMapper;

    @Override
    public Map<String, Object> processParam(ParamProcessStep paramProcessStep, ApiInvokeVO vo, Map<String, Object> lastStepResult) {
        Map<String, Object> treeMap = new TreeMap<>(lastStepResult);
        return Collections.singletonMap(ProcessServiceConstant.SERVICE_SORT_LIST, treeMap);
    }

    @Override
    public Class resultType() {
        return TreeMap.class;
    }
}
