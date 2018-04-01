package com.test;

import com.dto.Member;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by changbingquan on 2018/3/29.
 */
public class TestSpringActiviti {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[]{"spring-global.xml"});
        //获取activiti相关的bean
        ProcessEngine engine = (ProcessEngine) ctx.getBean("processEngine");
        RepositoryService rs = (RepositoryService) ctx.getBean("repositoryService");
        RuntimeService runService = (RuntimeService) ctx.getBean("runtimeService");
        TaskService taskService = (TaskService) ctx.getBean("taskService");
        //部署
        Deployment dep = rs.createDeployment().addClasspathResource("process/firstActivitiDrools.bpmn20.xml").addClasspathResource("rules/first.drl").deploy();
        ProcessDefinition pd = rs.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();

        Map<String, Object> vars = new HashMap<String, Object>();
        vars.put("name", "angus");
        ProcessInstance pi = runService.startProcessInstanceById(pd.getId(), vars);
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        System.out.println("当前流程节点：" + task.getName());

        //创建一个业务事实
        Member member = new Member();
        member.setIdentity("copper");
        member.setConsumption(1000);
        Map<String, Object> vars2 = new HashMap<String, Object>();
        vars2.put("inputVar", member);
        //添加业务事实为任务参数，提供给业务规则任务的入参
       /* taskService.complete(task.getId(), vars2);*/
        System.exit(0);
    }
}
