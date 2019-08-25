	/**
 * 
 */
package br.com.consultemed.models;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author carlosbarbosagomesfilho
 *
 */

@NamedQueries({ @NamedQuery(name = "Agendamento.findAll", query = "SELECT m FROM Agendamento m")})
@NoArgsConstructor
@Entity
@Table(name = "TB_AGENDAMENTOS")
public class Agendamento implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Getter
	@Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter
	@Setter
	@Inject
	@ManyToOne
	@JoinColumn(name = "ID_MEDICO")
	private Medico medico;
		
	@Getter
	@Setter
	@ManyToOne
	@JoinColumn(name = "ID_PACIENTE")
	private Paciente paciente;

	@Getter
	@Setter
	@Column(name = "DATA_AGENDAMENTO")
	private String data_agendamento;

	@Getter
	@Setter
	@Column(name = "DATA_CONSULTA")
	private String data_consulta;
	
	@Getter
	@Setter
	@Enumerated(EnumType.STRING)
	private StatusAgendamento status;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agendamento other = (Agendamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
	
	
	
	
	
}
