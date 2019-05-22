package com.rongke.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.rongke.mapper.ChannelSumMapper;
import com.rongke.model.ChannelSum;
import com.rongke.service.ChannelSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelSumServiceImpl extends ServiceImpl<ChannelSumMapper, ChannelSum> implements ChannelSumService {

    @Autowired
    private ChannelSumMapper channelSumMapper;

    @Override
    public boolean channelSumAdd(ChannelSum channelSum) {
        return channelSumMapper.channelSumInsert(channelSum);
    }

    @Override
    public boolean modifyChennlSum() {
       return channelSumMapper.updateChennlSum();
    }

    @Override
    public boolean modifyChennlSumSqrs() {
        return channelSumMapper.updateChennlSumSqrs();
    }

    @Override
    public List<String> showFfrs() {
        return channelSumMapper.selectFfrs();
    }

    @Override
    public boolean modifyFfrs() {
        return channelSumMapper.updateFfrs();
    }

    @Override
    public boolean modifyAppZC() {
        return channelSumMapper.updateAppZC();
    }

    @Override
    public boolean modifyAppSQ() {
        return channelSumMapper.updateAppSQ();
    }

    @Override
    public boolean modifyAppFfrs() {
        return channelSumMapper.updateAppFfrs();
    }
}
