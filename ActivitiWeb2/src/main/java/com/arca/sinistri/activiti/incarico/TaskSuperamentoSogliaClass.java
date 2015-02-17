package com.arca.sinistri.activiti.incarico;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

public class TaskSuperamentoSogliaClass implements JavaDelegate {
	
	private Logger log =  Logger.getLogger(TaskChiusuraClass.class);

	public void execute(DelegateExecution arg0) throws Exception {
		log.info("TaskSuperamentoSogliaClass invocata da processo : " + arg0.getProcessInstanceId());
		Integer codiceIncarico = (Integer) arg0.getVariable("codiceIncarico"); 
		Integer importo = (Integer) arg0.getVariable("importoIncarico"); 
		Integer soglia = (Integer) arg0.getVariable("soglia");
		log.info(" -> numero incarico processo: " + codiceIncarico);
		if(importo > soglia){
			log.info(" --> il valore importoIncarico e' " + importo + " > " + soglia );
		}
		return;
	}

}
