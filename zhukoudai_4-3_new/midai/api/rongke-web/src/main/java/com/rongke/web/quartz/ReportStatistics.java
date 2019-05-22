package com.rongke.web.quartz;

import com.rongke.model.*;
import com.rongke.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 每天定时执行生报表逻辑
 */
@CrossOrigin
public class ReportStatistics {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private EvaluationService evaluationService;

    @Resource
    private OrderSumService orderSumService;

    @Autowired
    private UserIdentityService userIdentityService;

    @Autowired
    private UserService userService;

    @Autowired
    private RepayRecordService repayRecordService;

    /**
     * 定时统计率
     */
    public void insertOrderSum() throws Exception {
        //按时还款笔数
        OrderSum orderSum = new OrderSum();
        orderSum.setDate(addOneDay(0));
        orderSumService.insert(orderSum);
        log.error("生成统计订单详情：");
    }




    public String addOneDay(Integer num) {
        String add = null;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar begin = Calendar.getInstance();
            begin.setTime(new Date());
            begin.add(Calendar.DAY_OF_MONTH, num);
            add = df.format(begin.getTime());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return add;
    }


}
