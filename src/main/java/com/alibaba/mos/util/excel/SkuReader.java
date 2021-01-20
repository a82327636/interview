package com.alibaba.mos.util.excel;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.mos.data.ChannelInventoryDO;
import com.alibaba.mos.data.SkuDO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @ProjectName: interview
 * @Package: com.alibaba.mos.util.excel
 * @ClassName: SkuReader
 * @Author: zwj
 * @Description: 注释
 * @Date: 2021/1/19 16:33
 * @Version: 1.0
 */
public class SkuReader implements IRowReader {

    public BlockingDeque<SkuDO> queue = new LinkedBlockingDeque<>();

    @Override
    public void getRows(int sheetIndex, int curRow, List<String> rowlist) {
        /**
         * 因为第一行是列头
         */
        if(curRow >0){
            SkuDO skuDO = new SkuDO();
            skuDO.setId(rowlist.get(0));
            skuDO.setName(rowlist.get(1));
            skuDO.setArtNo(rowlist.get(2));
            skuDO.setSpuId(rowlist.get(3));
            skuDO.setSkuType(rowlist.get(4));
            skuDO.setPrice(new BigDecimal(rowlist.get(5).toString()));
            skuDO.setInventoryList(JSONObject.parseArray(rowlist.get(6), ChannelInventoryDO.class));
            queue.add(skuDO);
        }
    }

}
