package com.yrx.datasourcemanager.manager.service;

import com.yrx.datasourcemanager.manager.pojo.ParamProcessStep;
import com.yrx.datasourcemanager.manager.vo.ApiInvokeVO;

import java.util.Map;

/**
 * Created by r.x on 2019/9/2.
 */
public interface IParamProcessStepService<T> {

    /**
     * 参数加工逻辑
     * @param paramProcessStep 参数加工步骤
     * @param vo 入参
     * @param lastStepResult 上一步执行结果
     * @return
     */
    T processParam(ParamProcessStep paramProcessStep, ApiInvokeVO vo, Map<String, Object> lastStepResult);
}
