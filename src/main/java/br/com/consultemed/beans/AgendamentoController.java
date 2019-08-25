
/**
 * 
 */
package br.com.consultemed.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import br.com.consultemed.models.Agendamento;
import br.com.consultemed.models.Medico;
import br.com.consultemed.models.Paciente;
import br.com.consultemed.models.StatusAgendamento;

import br.com.consultemed.services.AgendamentoService;
import br.com.consultemed.services.MedicoService;
import br.com.consultemed.services.PacienteService;
import lombok.Getter;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 *
 */
@Named
@RequestScoped
public class AgendamentoController{
	
	final static Logger logger = Logger.getLogger(AgendamentoController.class);
	
	@Getter
	@Setter
	private List<Agendamento> agendamentos;

	@Inject
	@Getter
	@Setter
	private Agendamento agendamento;
	
	@Inject
	@Getter
	@Setter
	private Agendamento agendamentoEditar;
		
	@Inject
	private AgendamentoService service;

	@Inject
	private MedicoService medicoservice;
	
	@Inject
	private PacienteService pacienteservice;

	
	public String editar() {
		this.agendamento = this.agendamentoEditar;
		return "/pages/agendamentos/addAgendamentos.xhtml";
	}
	
	public String excluir() throws Exception {
		this.agendamento = this.agendamentoEditar;
		this.service.deletarAgendamento(this.agendamento.getId());
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Agendamento " +agendamento+", excluído com sucesso", null));
		listaAgendamentos();
		return "/pages/agendamentos/agendamentos.xhtml";
	}
	
	public String novoAgendamento() {
		this.agendamento = new Agendamento();
		return "/pages/agendamentos/addAgendamentos.xhtml?faces-redirect=true";
	}
	
	public String addAgendamento() throws Exception {
		this.service.salvarAgendamento(this.agendamento);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Agendamento " +agendamento+", cadastrado com sucesso", null));
		listaAgendamentos();
		return "/pages/agendamentos/agendamentos.xhtml";
	}
	
	public List<Agendamento> listaAgendamentos() throws Exception{
		this.agendamentos = this.service.listaAgendamento();
		return this.agendamentos;
	}
	
	public List<Medico> listaMedicos() throws Exception{
		List<Medico> listaMedicos = this.medicoservice.listaMedico();
		return listaMedicos;
	}
	
	public StatusAgendamento[] getStatus() {
		return StatusAgendamento.values();
	}

	public List<Paciente> listaPacientes() throws Exception{
		List<Paciente> listaPacientes = this.pacienteservice.listaPaciente();
		return listaPacientes;
	}
	
}
