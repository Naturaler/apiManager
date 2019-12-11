package com.yrx.dataSourceManager.apimanager.source.api;

import com.yrx.dataSourceManager.apimanager.constant.ApiGroupEnum;

/**
 * Created by r.x on 2019/12/11.
 */
public interface IApi<Req, Rsp, Vo> {

    /**
     * 将标准输入转换为目标输入
     * @param vo 标准输入
     * @return
     */
    Req convertVoToReq(Vo vo);

    /**
     * 执行接口
     * @param req 入参
     * @return
     */
    Rsp perform(Req req);

    /**
     * 是否收费
     * @return
     * <ul>
     *     <li>true：收费</li>
     *     <li>false：不收费</li>
     * </ul>
     */
    boolean isCharge(Rsp rsp);

    /**
     * 接口组别
     */
    ApiGroupEnum setGroup();

}
