package com.rongke.service;

import com.baomidou.mybatisplus.service.IService;
import com.rongke.model.TongDunMessage;

public interface TongDunMessageService extends IService<TongDunMessage> {

    Long showTDMUserId(Long userId);

    String cutOutData(String  data);

}
