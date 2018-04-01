package com.service;

import com.dto.Member;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by changbingquan on 2018/3/29.
 */

public class MyService {
    Logger logger = LoggerFactory.getLogger(MyService.class);

    public void print(DelegateExecution execution, String name) {
        List<Member> list = (List<Member>) execution.getVariable("outVar");
        for (Member m : list) {
            System.out.print("服务处理：" + m.getPreferentialAmount() + "名字："+name);
        }

      /*  logger.info("自定义的服务：" +execution.getId()+ name);*/
    }
}
