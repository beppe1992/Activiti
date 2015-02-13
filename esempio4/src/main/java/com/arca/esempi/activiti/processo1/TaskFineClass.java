package com.arca.esempi.activiti.processo1;

import org.activiti.engine.HistoryService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.history.HistoricProcessInstance;

public class TaskFineClass implements JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		String procId = arg0.getProcessInstanceId();
		System.out.println("TaskFineClass invocata da processo : " + procId);
		
		return;

	}

}
