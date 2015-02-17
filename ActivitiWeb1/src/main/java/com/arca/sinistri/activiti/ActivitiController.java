
package com.arca.sinistri.activiti;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.impl.DefaultProcessDiagramGenerator;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arca.sinistri.activiti.dto.Incarico;


@Controller
public class ActivitiController {

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private ProcessEngine processEngine;
	
	@Autowired
	private HistoryService historyService;

	@Autowired
	private RepositoryService repositoryService;
	
	
	private Logger log =  Logger.getLogger(ActivitiController.class);
	
	private String nomeTipoProcesso = null;

	@PostConstruct
	public void init() {

		String filenameProc = "./Processi/EsempioProcessoIncaricoWeb.bpmn";  
		nomeTipoProcesso = "processoIncarico1";

		log.info("processEngine: "+ processEngine.getName()+"\n");

		//RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment()
		.addClasspathResource(filenameProc)
		.deploy();

		log.info("inizializzato ActivitiController");

	}

	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String forwardHome() {
		return "forward:index";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String printHello(ModelMap model) {
		model.addAttribute("message", "Hello Spring MVC Framework!");

		return "index";
	}

	
	@RequestMapping(value = "/avviaIncarico", method = RequestMethod.GET)
	public String avviaIncarico(@RequestParam("codice") String parCodice) {
		log.info("parametro codice: " + parCodice);
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("codiceIncarico", Integer.parseInt(parCodice));
		//TODO aggiungere controllo non sia già creato processo con stessa variabile
		String procId = runtimeService.startProcessInstanceByKey(nomeTipoProcesso,variables).getId();

		log.info("avviato ProcessInstance id: " + procId +"\n");
		return "index";
	}
	
	
	@RequestMapping(value = "/accettaIncarico", method = RequestMethod.GET)
	public String accettaIncarico(@RequestParam("codice") String parCodice) {
		log.info("parametro codice: " + parCodice);
		TaskService taskService = processEngine.getTaskService();
		//List<Task> tasks1 = taskService.createTaskQuery().taskVariableValueEquals("codiceIncarico",Integer.parseInt(parCodice)).list();
		List<Task> tasks = taskService.createTaskQuery().processVariableValueEquals("codiceIncarico",Integer.parseInt(parCodice)).list();

		for (Task task : tasks) {
			log.info("completo Task id: " + task.getId() + ", del processo: " + task.getProcessInstanceId() );
			taskService.complete(task.getId());
		}
		log.info("incarico parametro codice: " + parCodice + " accettato\n");
		return "index";
	}

	
	@RequestMapping(value = "/visualizzaIncarico", method = RequestMethod.GET)
	public String visualizzaIncarico(@RequestParam("codice") String parCodice, ModelMap model) {
		log.info("parametro codice: " + parCodice);
		String processo = null;
		TaskService taskService = processEngine.getTaskService();
		Task task = taskService.createTaskQuery().processVariableValueEquals("codiceIncarico",Integer.parseInt(parCodice)).singleResult();
		if(null != task){
			processo = task.getProcessInstanceId();
		}else{
			HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().
					variableValueEquals("codiceIncarico",Integer.parseInt(parCodice)).singleResult();
			processo = historicProcessInstance.getId();
		}
		model.addAttribute("message", "stato incarico " + parCodice);
		model.addAttribute("processo",  processo); 
		
		log.info("incarico parametro codice: " + parCodice + " recuperato\n");
		return "visualizzaIncarico";
	}
	
	
	@RequestMapping(value = "/incarichiAttivi", method = RequestMethod.GET)
	public String elencoIncarichiAttivi(ModelMap model){
		log.info("recupero incarichi attivi");
		List<Incarico> incarichiList = new ArrayList<Incarico>(0);
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().processDefinitionKey(nomeTipoProcesso).active().includeProcessVariables().list();
		
		for (Task task : tasks) {
			Incarico incarico = new Incarico();
			incarico.setProcessoId(task.getProcessInstanceId());
			Map<String, Object> variables = task.getProcessVariables();
			incarico.setCodiceIncarico((Integer) variables.get("codiceIncarico"));
			incarichiList.add(incarico);
		}
				
		model.addAttribute("message", "incarichi tipo: " + nomeTipoProcesso);
		model.addAttribute("totale",  tasks.size()); 
		model.addAttribute("listaIncarichi", incarichiList); 
		log.info("elenco incarichi recuperato\n");
		return "incarichiAttivi";
	}
	
	
	//TODO metodi per visualizzarele immmagini -> da sposate in un altra classe

	/*
	@RequestMapping(value = "/activiti/process-definition/diagram", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] processDefinitionDiagram(String id) throws IOException {
		return workflowService.getProcessDefinitionDiagram(id);
	}
	*/
	
	@RequestMapping(value = "/activiti/process-instance/diagram", produces = MediaType.IMAGE_PNG_VALUE)
	@ResponseBody
	public byte[] processInstanceDiagram(String id) throws IOException {
		log.debug("parametro id processo: " + id);
		HistoricProcessInstance historicProcessInstance = historyService
				.createHistoricProcessInstanceQuery().processInstanceId(id)
				.singleResult();
		BpmnModel bpmnModel = repositoryService
				.getBpmnModel(historicProcessInstance.getProcessDefinitionId());
		List<String> activeActivityIds;
		try {
			activeActivityIds = runtimeService
					.getActiveActivityIds(historicProcessInstance.getId());
		} catch (ActivitiObjectNotFoundException e) {
			activeActivityIds = new ArrayList<String>();
		}
		InputStream resourceAsStream = new DefaultProcessDiagramGenerator().generateDiagram(bpmnModel, "png", activeActivityIds);
		log.debug("immagine processo id: " + id + " generata\n");
		return IOUtils.toByteArray(resourceAsStream);
	}



}


