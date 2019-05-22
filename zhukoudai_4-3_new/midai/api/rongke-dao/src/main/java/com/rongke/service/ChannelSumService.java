package com.rongke.service;

import com.baomidou.mybatisplus.service.IService;
import com.rongke.model.ChannelSum;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ChannelSumService extends IService<ChannelSum> {

    boolean channelSumAdd(ChannelSum channelSum);

    //    把统计注册，认证，申请的人数统计出来放到表中
    boolean modifyChennlSum();

    //    把统计注册，认证人数统计出来放到表中
    boolean modifyChennlSumSqrs();

    //    统计出放款人数，与放款总次数
    List<String> showFfrs();

    //    统计出放款人数与放款次数放入表中
    boolean modifyFfrs();

    //    把没有渠道认证，认证人数统计出来
    boolean modifyAppZC();

    //    把没有渠道放款，申请人数统计出来
    boolean modifyAppSQ();

    boolean modifyAppFfrs();
}
