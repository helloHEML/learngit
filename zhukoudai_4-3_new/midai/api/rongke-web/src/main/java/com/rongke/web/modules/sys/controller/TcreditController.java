package com.rongke.web.modules.sys.controller;


import com.rongke.utils.ans.R;
import com.rongke.web.modules.sys.service.TianzhenMessagessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @TcreditController
 * @天针Controller
 * @version : Ver 1.0
 */
@RestController
@RequestMapping(value="/api/tianZhen")
@Transactional
@CrossOrigin
public class TcreditController {

    @Autowired
    private TianzhenMessagessService tianzhenMessageService;

    /**
     * 获取探针数据
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public R youdunApi(Long userId) {
        return  tianzhenMessageService.mesInfo(userId);
    }

    /**
     * 更新探针数据
     */
    @RequestMapping(value = "/infoUpdate", method = RequestMethod.GET)
    public R infoUpdate(Long userId) {
        return  tianzhenMessageService.mesInfoUpdate(userId);
    }


}
