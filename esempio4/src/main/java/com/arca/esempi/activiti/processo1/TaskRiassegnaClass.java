package com.arca.esempi.activiti.processo1;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;

public class TaskRiassegnaClass implements JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		String procId = arg0.getProcessInstanceId();
		System.out.println("TaskRiassegnaClass invocata da processo : " + procId);
		TaskService taskService = arg0.getEngineServices().getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
				
		for (Task task : tasks) {
			System.out.println("task assegnato a fozzie: "  + task.getId() + " - " + task.getName() );
			//taskService.claim(task.getId(), "fozzie");
			System.out.println("completo Task " + task.getName() + " id: " + task.getId() + ", del processo: " + task.getProcessInstanceId() ); 
			//taskService.complete(task.getId());
		}
		
		return;
	}

}
