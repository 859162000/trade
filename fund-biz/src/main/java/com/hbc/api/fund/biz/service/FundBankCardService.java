/*
 * Copyright (c) 2015-2016, CCLX.COM. All rights reserved.
 * WANDA GROUP PROPRIETARY/CONFIDENTIAL. 
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is private property; you can't redistribute it and/or modify it
 * under the terms of the LICENSE you obtained from
 *
 *    http://www.cclx.com/
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. 
 *
 * Author: Jongly Ran
 * Revision: 1.0
 */
package com.hbc.api.fund.biz.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hbc.api.fund.biz.enums.FundBankCardType;
import com.hbc.api.fund.biz.enums.FundBankStatus;
import com.hbc.api.fund.biz.enums.FundBankValidateStatus;
import com.hbc.api.fund.biz.enums.FundReturnCodeEnum;
import com.hbc.api.fund.biz.exception.FundException;
import com.hbc.api.fund.biz.mapping.gen.FundBankCardMapper;
import com.hbc.api.fund.biz.mapping.gen.bean.FundBankCard;
import com.hbc.api.fund.biz.mapping.gen.bean.FundBankCardExample;
import com.hbc.api.fund.biz.mapping.genx.FundBankCardMapperEnhance;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundBankCardBindBean;
import com.hbc.api.fund.biz.mapping.genx.xbean.FundBankCardQueryBean;
import com.hbc.api.fund.biz.third.GuideService;
import com.hbc.api.fund.biz.validator.FundValidator;
import com.hbc.api.trade.bdata.common.IDGenerotor;
import com.hbc.api.trade.bdata.mapper.guide.gen.bean.GuideBean;

/**
 * @author Jongly Ran
 */
@Service
public class FundBankCardService {
    private final static Logger log = LoggerFactory.getLogger(FundBankCardService.class);

    @Autowired
    private FundBankCardMapper fundBankCardMapper;
    @Autowired
    private FundBankCardMapperEnhance fundBankCardMapperEnhance;
    @Autowired
    private GuideService guideService;

    public FundBankCard selectFundBankCard(String bankNo) {
        return fundBankCardMapper.selectByPrimaryKey(bankNo);
    }

    public List<FundBankCard> selectFundBankCardList(FundBankCardQueryBean queryBean) {
        return fundBankCardMapperEnhance.selectFundBankCardList(queryBean);
    }

    public int selectFundBankCardListTotalSize(FundBankCardQueryBean queryBean) {
        return fundBankCardMapperEnhance.selectFundBankCardListTotalSize(queryBean);
    }

    public void deleteFundBankCard(String bankNo) {
        if (fundBankCardMapperEnhance.deleteFundBankCard(bankNo) == 0) {
            log.error("逻辑删除银行卡失败, bankNo=" + bankNo);
            throw new FundException(FundReturnCodeEnum.ERR_UPDATE, "删除银行卡失败");
        }
    }

    public void bindNewBankCard(FundBankCardBindBean bindBean) {
        ValidateBindNewBankCard(bindBean);
        // 初始化目标对象
        FundBankCard bankCard = new FundBankCard();

        // 获取导游对应的地接社信息
        GuideBean guideBean = guideService.getGuide(bindBean.getGuideId());
        
        bankCard.setCityId(bindBean.getCityId());
        bankCard.setCityName(bindBean.getCityName());
        
        bankCard.setGuideId(bindBean.getGuideId());
        bankCard.setGuideAgencyId(guideBean.getAgencyId() != null ? guideBean.getAgencyId().toString() : null);
        bankCard.setGuideName(guideBean.getGuideName());
        bankCard.setGuideAgencyName(guideBean.getAgencyBossId());

        // 绑定其他基础信息
        Date currentTime = new Date();
        bankCard.setCreateTime(currentTime);
        bankCard.setUpdateTime(currentTime);
        bankCard.setStatus(FundBankStatus.BIND.value);
        bankCard.setIsValid(FundBankValidateStatus.NO.value);
        bankCard.setBankNo(IDGenerotor.generateBankNo());
        bankCard.setType(bindBean.getType());
        bankCard.setAccount(bindBean.getAccount());
        bankCard.setBank(bindBean.getBank());
        bankCard.setAccountHolderName(bindBean.getAccountHolderName());

        try {
            fundBankCardMapper.insert(bankCard);
        } catch (Exception e) {
            log.error("新增银行卡帐号失败" + JSON.toJSONString(bankCard), e);
            throw new FundException(FundReturnCodeEnum.ERR_INSERT, "新增银行卡帐号失败");
        }
    }

    /**
     * @param bindBean
     */
    private void ValidateBindNewBankCard(FundBankCardBindBean bindBean) {
        // 验证输入参数
        FundValidator.validateParamString(bindBean.getGuideId(), "导游或地接社ID");
        FundValidator.validateParamString(bindBean.getAccountHolderName(), "开户人姓名");
        FundValidator.validateParamString(bindBean.getAccount(), "银行账号");
        if (FundBankCardType.getStatus(bindBean.getType()).equals(FundBankCardType.BANK)) {
            FundValidator.validateParamString(bindBean.getBank(), "银行卡开户行名称");
        }

        // 校验是否可以绑定
        FundBankCardExample example = new FundBankCardExample();
        example.createCriteria().andAccountEqualTo(bindBean.getAccount()).andGuideIdEqualTo(bindBean.getGuideId())
        		.andStatusEqualTo(FundBankStatus.BIND.value);
        List<FundBankCard> results = fundBankCardMapper.selectByExample(example);
        if (results != null && results.size() > 0) {
            log.error("银行卡已绑定，输入参数信息：" + JSON.toJSONString(bindBean));
            throw new FundException(FundReturnCodeEnum.ERR_EXISTED, "银行卡帐号");
        }
    }

    /**
     * @param bankNo
     * @param guideId
     */
    public void unbindBankCard(String bankNo, String guideId) {
        FundBankCard fundBankCard = fundBankCardMapper.selectByPrimaryKey(bankNo);
        if (fundBankCard == null || !fundBankCard.getGuideId().equals(guideId)) {
            log.error("银行卡解绑失败,bankNo:" + bankNo);
            throw new FundException(FundReturnCodeEnum.ERR_UPDATE, "银行卡解绑失败");
        }
        FundBankCard record = new FundBankCard();
        record.setBankNo(bankNo);
        record.setStatus(0);
        if (fundBankCardMapper.updateByPrimaryKeySelective(record) == 0) {
            log.error("银行卡解绑失败,bankNo:" + bankNo);
            throw new FundException(FundReturnCodeEnum.ERR_UPDATE, "银行卡解绑失败");

        }
    }
}
