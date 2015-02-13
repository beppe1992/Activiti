package com.arca.esempi.activiti.processo1;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class TaskInizioClass implements JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		System.out.println("TaskInizioClass invocata da processo : " + arg0.getProcessInstanceId());
		//arg0.setVariable("numeroDaValutare",10);
		
		System.out.println(" -> numeroDaValutare : " + arg0.getVariable("numeroDaValutare"));
		
		return;
	}

}
