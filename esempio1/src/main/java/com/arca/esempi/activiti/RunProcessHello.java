package com.arca.esempi.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;



public class RunProcessHello {

	public static void main(String [ ] args){
		
		String filenameConf = "./activiti.cfg.xml";
		String filenameProc = "./diagrams/hello.bpmn";   ///NB!!The process definition XML file needs to end with .bpmn20.xml or .bpmn otherwise it's not parsed and deployed to the Activiti Engine.

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
		
		// Start a process instance
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("helloWorld");


		System.out.println("fine");
	}
}
