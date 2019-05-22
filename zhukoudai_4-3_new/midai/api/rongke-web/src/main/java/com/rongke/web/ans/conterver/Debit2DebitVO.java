package com.rongke.web.ans.conterver;

import com.google.common.collect.Lists;
import com.rongke.model.ans.UserDebitEntity;
import com.rongke.service.impl.ans.utils.UserDebitUtils;
import com.rongke.web.ans.vo.DebitVO;
import org.springframework.beans.BeanUtils;

import java.util.List;

public class Debit2DebitVO {


    public static DebitVO converter(UserDebitEntity debit){
        DebitVO vo = new DebitVO();
        BeanUtils.copyProperties(debit,vo);
        vo.setFrom(debit.getFrom());
        return vo;
    }
    public static List<DebitVO> converter(List<UserDebitEntity> debit){
        List<DebitVO> list = Lists.newArrayList();
        for (UserDebitEntity d:debit){
            list.add(converter(d));
        }
        return list;
    }

}
