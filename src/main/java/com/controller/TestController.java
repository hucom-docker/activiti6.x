package com.controller;

import com.dto.Member;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by changbingquan on 2018/3/29.
 */
@Controller
@ResponseBody
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private ProcessEngine engine;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        Deployment dep = repositoryService.createDeployment().addClasspathResource("process/firstActivitiDrools.bpmn20.xml").addClasspathResource("rules/first.drl").deploy();
        ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().deploymentId(dep.getId()).singleResult();

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
        taskService.complete(task.getId(), vars2);

        List<Execution> list = runService.createExecutionQuery().processInstanceId(pi.getId()).list();

      /*  String name = (String) runService.getVariable(execution.getId(), "name");
        List<Member> list = (List<Member>) runService.getVariable(execution.getId(), "outVar");*/
        return "速度飞快独守空房士大夫十分";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2() {

        return "sdfdsfsdsd";
    }

}
