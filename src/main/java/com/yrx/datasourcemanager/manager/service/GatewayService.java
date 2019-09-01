package com.yrx.datasourcemanager.manager.service;

import com.yrx.datasourcemanager.manager.dao.ApiConfigMapper;
import com.yrx.datasourcemanager.manager.dao.ParamProcessStepMapper;
import com.yrx.datasourcemanager.manager.dao.extend.ParamMapper;
import com.yrx.datasourcemanager.manager.dto.Response;
import com.yrx.datasourcemanager.manager.pojo.ApiConfig;
import com.yrx.datasourcemanager.manager.pojo.ParamProcessStep;
import com.yrx.datasourcemanager.manager.util.HttpUtil;
import com.yrx.datasourcemanager.manager.vo.ApiInvokeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by r.x on 2019/9/1.
 */
@Slf4j
@Service
public class GatewayService {
    @Autowired
    private ApiConfigMapper apiConfigMapper;
    @Autowired
    private ParamMapper paramMapper;
    @Autowired
    private ParamProcessStepMapper paramProcessStepMapper;

    /**
     * 根据dataSource id从数据库中获取接口的请求方式、加密方式、参数 列表，组装并执行请求
     *
     * @param vo
     * @return
     */
    public Response<String> invokeApi(ApiInvokeVO vo) {
        Integer apiId = vo.getApiId();
        ApiConfig apiConfig = apiConfigMapper.selectByPrimaryKey(apiId);
        List<ParamMapper.ApiParam> apiParams = paramMapper.listParamByApiId(apiId);
        Map<String, String> requestHeader = constructHeader(apiConfig, apiParams, vo);
        String requestBody = constructRequestBody(apiParams, vo.getParams(), apiConfig);
        String response = HttpUtil.postJson(apiConfig.getHttpUrl(), requestBody);
        return Response.success(response);
    }

    private Map<String, String> constructHeader(ApiConfig apiConfig, List<ParamMapper.ApiParam> apiParams, ApiInvokeVO vo) {
        List<ParamMapper.ApiParam> headerParams = apiParams.stream()
                .filter(apiParam -> apiParam.getParamZone() == 0)
                .collect(Collectors.toList());
        Map<String, String> headers = new HashMap<>();
        for (ParamMapper.ApiParam headerParam : headerParams) {
            String paramProcessSteps = headerParam.getParamProcessSteps();
            String[] processStepIds = paramProcessSteps.split(",");
            for (String processStepId : processStepIds) {
                ParamProcessStep paramProcessStep = paramProcessStepMapper.selectByPrimaryKey(Integer.valueOf(processStepId.trim()));
                processParam(paramProcessStep, vo);
            }
        }
        return null;
    }

    private String constructRequestBody(List<ParamMapper.ApiParam> apiParams, List<ApiInvokeVO.ParamVO> params, ApiConfig apiConfig) {

        return null;
    }

    private void processParam(ParamProcessStep paramProcessStep, ApiInvokeVO vo) {

    }
}
