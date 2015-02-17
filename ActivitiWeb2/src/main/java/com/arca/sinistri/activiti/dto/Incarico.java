package com.arca.sinistri.activiti.dto;

public class Incarico {
	
	private String processoId;
	private Integer codiceIncarico;
	private String nomeTaskProcesso;
	
	public String getProcessoId() {
		return processoId;
	}
	public void setProcessoId(String processoId) {
		this.processoId = processoId;
	}
	public Integer getCodiceIncarico() {
		return codiceIncarico;
	}
	public void setCodiceIncarico(Integer codiceIncarico) {
		this.codiceIncarico = codiceIncarico;
	}
	public String getNomeTaskProcesso() {
		return nomeTaskProcesso;
	}
	public void setNomeTaskProcesso(String nomeTaskProcesso) {
		this.nomeTaskProcesso = nomeTaskProcesso;
	}

}
