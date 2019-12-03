package com.yrx.dataSourceManager.apimanager.util;

import com.yrx.dataSourceManager.apimanager.annotation.ApiDesc;
import com.yrx.dataSourceManager.apimanager.constant.HttpType;
import com.yrx.dataSourceManager.apimanager.pojo.ApiDocument;
import com.yrx.dataSourceManager.apimanager.pojo.ParamDescriptor;
import com.yrx.dataSourceManager.apimanager.source.DemoApi;
import org.springframework.asm.Type;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by r.x on 2019/12/2.
 * 自动扫描生成接口文档
 */
@Component
public class ApiDocumentHelper {

    public List<ApiDocument> parseApi(Class<?> source) {
        List<ApiDocument> apiDocuments = new ArrayList<>();
        RestController restController = source.getAnnotation(RestController.class);
        String prefix = restController.value();
        ApiDesc apiDesc = DemoApi.class.getAnnotation(ApiDesc.class);
        String descPrefix = apiDesc.value();

        Method[] methods = source.getMethods();
        for (Method method : methods) {
            HttpType httpType = extraHttpType(method);
            if (httpType == null) {
                continue;
            }
            String desc = extraDesc(method, descPrefix);
            List<ParamDescriptor> params = extraParam(method);
            Class<?> response = extraResponse(method);
            String url = extraUrl(method, httpType, prefix);

            ApiDocument apiDocument = ApiDocument.builder()
                    .httpType(httpType)
                    .desc(desc)
                    .header(Collections.emptyMap())
                    .params(params)
                    .response(response)
                    .url(url)
                    .build();
            apiDocuments.add(apiDocument);
        }
        return apiDocuments;
    }

    private HttpType extraHttpType(Method method) {
        HttpType httpType = null;
        GetMapping getMapping = method.getAnnotation(GetMapping.class);
        if (getMapping != null) {
            httpType = HttpType.GET;
        } else {
            PostMapping postMapping = method.getAnnotation(PostMapping.class);
            if (postMapping != null) {
                httpType = HttpType.POST;
            }
        }
        return httpType;
    }

    private String extraDesc(Method method, String descPrefix) {
        StringBuilder descBuilder = new StringBuilder(descPrefix);
        ApiDesc methodDesc = method.getAnnotation(ApiDesc.class);
        if (methodDesc != null) {
            descBuilder.append(methodDesc.value());
        }
        return descBuilder.toString();
    }

    private List<ParamDescriptor> extraParam(Method method) {
        List<ParamDescriptor> list = new ArrayList<>(method.getParameterCount());
        for (Parameter parameter : method.getParameters()) {
            ParamDescriptor paramDescriptor = new ParamDescriptor();
            Class<?> parameterType = parameter.getType();
            String parameterName = parameter.getName();
            paramDescriptor.setName(parameterName);
            paramDescriptor.setType(Type.getType(parameterType));
            list.add(paramDescriptor);
        }
        return list;
    }

    private Class<?> extraResponse(Method method) {
        return method.getReturnType();
    }

    private String extraUrl(Method method, HttpType httpType, String prefix) {
        String subPath = extraSubPath(method, httpType);
        return prefix + subPath;
    }

    private String extraSubPath(Method method, HttpType httpType) {
        switch (httpType) {
            case GET:
                GetMapping getMapping = method.getAnnotation(GetMapping.class);
                return extraFromGet(getMapping);
            case POST:
                PostMapping postMapping = method.getAnnotation(PostMapping.class);
                return extraFromPost(postMapping);
            default:
                return "";

        }
    }

    private String extraFromPost(PostMapping postMapping) {
        String[] subPaths = postMapping.value();
        return assembleSubPath(subPaths);
    }

    private String extraFromGet(GetMapping getMapping) {
        String[] subPaths = getMapping.value();
        return assembleSubPath(subPaths);
    }

    private String assembleSubPath(String[] pieces) {
        StringJoiner joiner = new StringJoiner("");
        for (String piece : pieces) {
            joiner.add(piece);
        }
        return joiner.toString();
    }

}
