/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2019 All Rights Reserved.
 */
package com.alibaba.mos.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.mos.api.SkuReadService;
import com.alibaba.mos.data.ChannelInventoryDO;
import com.alibaba.mos.data.SkuDO;
import com.alibaba.mos.util.excel.ExcelReaderUtil;
import com.alibaba.mos.util.excel.IRowReader;
import com.alibaba.mos.util.excel.SkuReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * TODO: 实现
 * @author superchao
 * @version $Id: SkuReadServiceImpl.java, v 0.1 2019年10月28日 10:49 AM superchao Exp $
 */
@Service
@Slf4j
public class SkuReadServiceImpl implements SkuReadService{



    /**
     * 这里假设excel数据量很大无法一次性加载到内存中
     * @param handler
     */
    @Override
    public void loadSkus(SkuHandler handler) {
        // 加载文件
        SkuReader skuReader = new SkuReader();
        try{
            Resource resource = new ClassPathResource("data/skus.xls");
            File file = resource.getFile();
            ExcelReaderUtil.readExcel(skuReader, file);
        }catch (IOException e){
            log.error("文件加载异常！",e);
        }catch (Exception e1){
            log.error("异常！",e1);
        }
        System.out.println("开始了");
        while(!skuReader.queue.isEmpty()){
            SkuDO skuDO = skuReader.queue.poll();
            handler.handleSku(skuDO);
        }
    }




}