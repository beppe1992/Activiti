package com.arca.sinistri.activiti.incarico;

import java.util.List;
import java.util.Random;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.task.Task;
import org.apache.log4j.Logger;

public class TaskAssegnaIncarico implements JavaDelegate {
	
	private Logger log =  Logger.getLogger(TaskAssegnaIncarico.class);

	public void execute(DelegateExecution arg0) throws Exception {
		String procId = arg0.getProcessInstanceId();
		log.info("TaskAssegnaIncarico invocata da processo : " + procId);
		
		
		/*RuntimeService runtimeService = arg0.getEngineServices().getRuntimeService();
		List<String> activityIds = runtimeService.getActiveActivityIds(procId);
		
		TaskService taskService = arg0.getEngineServices().getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskDefinitionKey("usertask1").includeProcessVariables().orderByTaskCreateTime().desc().list();
		*/
		/*
		TaskService taskService = arg0.getEngineServices().getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskDefinitionKey("usertask1").taskUnassigned().list();
		Integer codiceIncarico = (Integer) arg0.getVariable("codiceIncarico"); 
		
		for (Task task : tasks) {
			taskService.claim(task.getId(), "fozzie");
			log.info("task assegnato a fozzie: "  + task.getId() + " - " + task.getName() + ", del processo: " + task.getProcessInstanceId() ); 
			log.info(" -> numero incarico processo: " + codiceIncarico);
			
		}
		*/
		
		TaskService taskService = arg0.getEngineServices().getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
		log.info("TaskAssegnaIncarico totali incarichi assegnati: " + tasks.size());
		return;
	}

}
