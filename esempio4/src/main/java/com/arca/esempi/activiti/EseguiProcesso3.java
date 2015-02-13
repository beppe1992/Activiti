package com.arca.esempi.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;



public class EseguiProcesso3 {

	public static void main(String [ ] args){

		String filenameConf = "./activiti.cfg.xml";
		String filenameProc = "./Processi/Processo3.bpmn";  

		System.out.println("inizio");
		//ProcessEngine processEngine = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration().buildProcessEngine();
		ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource(filenameConf).buildProcessEngine();

		System.out.println("\n"+ processEngine.getName()+"\n");

		// Get Activiti services
		RepositoryService repositoryService = processEngine.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();

		// Deploy the process definition
		repositoryService.createDeployment()
		.addClasspathResource(filenameProc)
		.deploy();

		System.out.println("\n"+ "fine deploy" +"\n");


		System.out.println("\n"+ "avvio ProcessInstance");
		// Start a process instance
		//String procId = runtimeService.startProcessInstanceByKey("processo1").getId();
		

		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("numeroDaValutare", new Integer(20));
		variables.put("soglia", new Integer(15));
		String procId = runtimeService.startProcessInstanceByKey("processo3",variables).getId();
		
		System.out.println("avviato ProcessInstance id: " + procId +"\n");
		


		System.out.println("fine");
	}
}
