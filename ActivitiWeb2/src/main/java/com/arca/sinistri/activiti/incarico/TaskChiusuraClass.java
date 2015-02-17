package com.arca.sinistri.activiti.incarico;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

public class TaskChiusuraClass implements JavaDelegate {

	private Logger log =  Logger.getLogger(TaskChiusuraClass.class);

	
	public void execute(DelegateExecution arg0) throws Exception {
		String procId = arg0.getProcessInstanceId();
		//TaskService taskService = arg0.getEngineServices().getTaskService();
		//List<Task> tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
		Integer codiceIncarico = (Integer) arg0.getVariable("codiceIncarico");
		Integer importo  = (Integer) arg0.getVariable("importoIncarico"); 
		log.info("TaskChiusuraClass invocata da processo : " + procId);
		log.info(" -> numero incarico: " + codiceIncarico);
		if(null != importo){
			log.info(" -> importo: " + importo);
		}
		
		return;

	}

}
