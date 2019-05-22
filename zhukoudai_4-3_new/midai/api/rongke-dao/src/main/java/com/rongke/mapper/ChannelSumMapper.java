package com.rongke.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rongke.model.ChannelSum;

import java.util.List;

public interface ChannelSumMapper extends BaseMapper<ChannelSum> {

//    给channelSum表中添加名称，器名称为渠道商的名称
    boolean channelSumInsert(ChannelSum channelSum);

//    把统计注册，认证人数统计出来放到表中
    boolean updateChennlSum();

//    申请的人数统计出来放到表中
    boolean updateChennlSumSqrs();

//    统计出放款人数，与放款总次数
    List<String> selectFfrs();

//    统计出放款人数与放款次数放入表中
    boolean updateFfrs();

    //    把没有渠道认证，认证人数统计出来
    boolean updateAppZC();

    //    把没有渠道放款，申请人数统计出来
    boolean updateAppSQ();

    //    统计未经渠道出放款人数，与放款总次数
    boolean updateAppFfrs();

}
