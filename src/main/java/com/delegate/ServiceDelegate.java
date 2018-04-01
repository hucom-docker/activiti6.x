package com.delegate;

import com.dto.Member;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.List;

/**
 * Created by changbingquan on 2018/3/29.
 */
public class ServiceDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) {
        List<Member> list = (List<Member>) execution.getVariable("outVar");
        for (Member m : list) {
            System.out.print("服务处理：" + m.getPreferentialAmount());
        }
    }
}
