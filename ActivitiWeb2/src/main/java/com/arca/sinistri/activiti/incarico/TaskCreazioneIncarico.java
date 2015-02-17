package com.arca.sinistri.activiti.incarico;

import java.util.Random;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.log4j.Logger;

public class TaskCreazioneIncarico implements JavaDelegate {
	
	private Logger log =  Logger.getLogger(TaskCreazioneIncarico.class);

	public void execute(DelegateExecution arg0) throws Exception {
		String procId = arg0.getProcessInstanceId();
		log.info("TaskCreazioneIncarico invocata da processo : " + procId);
	
		Integer codiceIncarico = (Integer) arg0.getVariable("codiceIncarico"); 
		if(null == codiceIncarico || codiceIncarico.intValue() == 0){
			Random rnd = new Random();
			codiceIncarico = new Integer(rnd.nextInt(500));
			arg0.setVariable("codiceIncarico",codiceIncarico);
		}
				
		log.info(" -> numero incarico processo: " + codiceIncarico);
		
		return;
	}

}
