package br.com.cadastrocliente.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="contato")
public class Contato implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String telefone;
	private String whatsapp;
	private String celular;
	private String site;
	private String email;
	private Cliente cliente;
	
					
				//Método getters e setters com bean validation e anotações de jpa 
//------------------------------------------------------------------------------------------------------------------------
	
/*
 * Atribuindo uma chave primária(codigo) auto-increment	
 */
	@Id
	@GeneratedValue
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
/*
 *Column= atribuindo um nome para o campo telefone, tamanho, e que pode ser gravado caso não seja preenchido. 	
 */
	@Column(name="nr_telefone", nullable=true, length=20)
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
/*
 * Column= atribuindo um nome para o campo whatsapp, tamanho, e que pode ser gravado caso não seja preenchido. 	
 */
	@Column(name="nr_whatsapp", nullable = true, length=20)
	public String getWhatsapp() {
		return whatsapp;
	}
	public void setWhatsapp(String whatsapp) {
		this.whatsapp = whatsapp;
	}
	
/*
* Column= atribuindo um nome para o campo celular, tamanho, e que pode ser gravado caso não seja preenchido.
* NotBlank= o campo celular não pode ser vazio. 	
*/	
	@NotBlank
	@Column(name="nr_celular", nullable = false, length = 20)
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
/*
 * Column= atribuindo um nome para o campo site, e que pode ser gravado caso não seja preenchido.
 */
	@Column(name="nm_site", nullable= true)
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
/*
* Column= atribuindo um nome para o campo email, e que pode ser gravado caso não seja preenchido.
* Email= caso o campo não esteja vazio ele valida o email
*/	
	@Email
	@Column(name="nm_email", nullable = true)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@ManyToOne
	@JoinColumn(name="cliente_codigo")
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
							//Método equals e hashCode 
//------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Contato other = (Contato) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	

}
