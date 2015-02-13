package com.arca.esempi.activiti.processo1;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class TaskPariClass implements JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		System.out.println("TaskPariClass invocata da processo : " + arg0.getProcessInstanceId());
		System.out.println("PARI");
		return;
	}

}
