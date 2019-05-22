package com.rongke.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.rongke.model.TongDunMessage;

/**
 * 同盾的数据
 */
public interface TongDunMessageMapper extends BaseMapper<TongDunMessage> {

    /**
     * 判断是否添加数据
     * @param userId
     * @return
     */
   Long selectTDMUserId(Long userId);


}
