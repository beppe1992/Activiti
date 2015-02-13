package com.arca.esempi.activiti.processo1;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class TaskMaggioreClass implements JavaDelegate {

	@Override
	public void execute(DelegateExecution arg0) throws Exception {
		System.out.println("TaskMaggioreClass invocata da processo : " + arg0.getProcessInstanceId());
		Integer valore = (Integer) arg0.getVariable("numeroDaValutare"); 
		Integer soglia = (Integer) arg0.getVariable("soglia");
		if(valore > soglia){
			System.out.println(" -> il numero " + valore.toString() + " e' maggiore di " + soglia );
		}
		return;
	}

}
