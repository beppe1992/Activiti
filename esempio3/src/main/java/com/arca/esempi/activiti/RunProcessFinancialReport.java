package com.arca.esempi.activiti;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;



public class RunProcessFinancialReport {

	public static void main(String [ ] args){

		String filenameConf = "./activiti.cfg.xml";
		String filenameProc = "./Processi/Processo1.bpmn";   ///NB!!The process definition XML file needs to end with .bpmn20.xml or .bpmn otherwise it's not parsed and deployed to the Activiti Engine.

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
		String procId = runtimeService.startProcessInstanceByKey("financialReport").getId();
		System.out.println("ProcessInstance id: " + procId +"\n");
		String procId2 = runtimeService.startProcessInstanceByKey("financialReport").getId();
		System.out.println("ProcessInstance id: " + procId2 +"\n");

		// Get the first task
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
		for (Task task : tasks) {
			System.out.println("Following task is available for accountancy group: " + task.getName());

			// claim it
			taskService.claim(task.getId(), "fozzie");
		}

		// Verify Fozzie can now retrieve the task
		tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
		System.out.println("Number of tasks for fozzie: "
				+ taskService.createTaskQuery().taskAssignee("fozzie").count());
		for (Task task : tasks) {
			System.out.println("completo Task for fozzie: " + task.getName() + " id: " + task.getId() + ", del processo: " + task.getProcessInstanceId() ); //runtimeService.createProcessInstanceQuery().list().get(0).getId()
		
			// Complete the task
			taskService.complete(task.getId());
		}

		System.out.println("Number of tasks for fozzie: "
				+ taskService.createTaskQuery().taskAssignee("fozzie").count());

		// Retrieve and claim the second task
		tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		for (Task task : tasks) {
			System.out.println("Following task is available for management group: " + task.getName());
			taskService.claim(task.getId(), "kermit");
		}

		System.out.println("Number of tasks for kermit: "
				+ taskService.createTaskQuery().taskAssignee("kermit").count());

		// Completing the second task ends the process
		for (Task task : tasks) {
			System.out.println("completo Task for kermit: " + task.getName() + " id: " + task.getId() + ", del processo: " + task.getProcessInstanceId() );
			taskService.complete(task.getId());
		}

		System.out.println("Number of tasks for kermit: "
				+ taskService.createTaskQuery().taskAssignee("kermit").count());

		// verify that the process is actually finished
		HistoryService historyService = processEngine.getHistoryService();
		HistoricProcessInstance historicProcessInstance =
				historyService.createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult();
		System.out.println("Process instance start time: " + historicProcessInstance.getStartTime());
		System.out.println("Process instance  end  time: " + historicProcessInstance.getEndTime());

		System.out.println("fine");
	}
}
