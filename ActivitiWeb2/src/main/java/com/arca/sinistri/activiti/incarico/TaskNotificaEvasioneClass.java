package com.arca.sinistri.activiti.incarico;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

public class TaskNotificaEvasioneClass implements JavaDelegate {
	
	private Logger log =  Logger.getLogger(TaskChiusuraClass.class);

	public void execute(DelegateExecution arg0) throws Exception {
		log.info("TaskNotificaEvasioneClass invocata da processo : " + arg0.getProcessInstanceId());
		Integer codiceIncarico = (Integer) arg0.getVariable("codiceIncarico"); 
		log.info(" -> numero incarico processo: " + codiceIncarico);
		return;
	}

}
