package com.arca.activiti.esempio2.test;
 
import static org.junit.Assert.assertNotNull;
 
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.annotation.Resource;
 
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring-config/beans.xml"})
public class ProcessTestHumanAndServiceTaskExample {
 
	private String filename = "C:/Development/Workspaces/WKS_Activiti/prova1/esempio2/HumanAndServiceTaskExample.bpmn";
	/**
	 * Inject repository service
	 */
	@Resource
	private RepositoryService repositoryService;
	/**
	 * Inject runtime service
	 */	
	@Resource
	private RuntimeService runtimeService;
 
	/**
	 * Inject task service
	 */	
	@Resource
	private TaskService taskService;
 
	@Test
	public void startProcess() throws Exception {
 
		/*
		 * Deploy the process
		 */
		repositoryService.createDeployment().enableDuplicateFiltering().addInputStream("HumanAndServiceTaskExample.bpmn",
				new FileInputStream(filename)).deploy();
 
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("processStartedBy", "Emanuele");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("HumanAndServiceTaskExample", variableMap);
 
		assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());
	}
 
	@Test
	public void claimAndCompleteHumanTask() throws Exception {
		List<Task> tasks= taskService.createTaskQuery().processDefinitionKey("HumanAndServiceTaskExample").taskDefinitionKey("HumanTaskExample").list();
		for(Task task:tasks){
			taskService.claim(task.getId(), "El");
			Map<String, Object> variableMap = new HashMap<String, Object>();
			variableMap.put("HumanTaskCompletedBy", "El");
			taskService.complete(task.getId(),variableMap);
		}
	}
}