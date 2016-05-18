package br.com.cadastrocliente.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="endereco")
public class Endereco implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String logradouro;
	private String numero;
	private String complemento;
	private String cidade;
	private String cep;
	private String uf;
	private Cliente cliente;
	
								//Método getters e setters com bean validation e anotações de jpa 
//------------------------------------------------------------------------------------------------------------------------

//Atribuindo uma chave primária(codigo) auto-increment
	@Id
	@GeneratedValue
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
/*
 * NotBlank = O campo logradouro não pode ser vazio
 * Column= modificando o nome do campo, o tamanho, não pode cadastrar quando for nulo	
 */
	@NotBlank
	@Column(name="nm_logradouro", nullable=false, length=100)
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
/*
* NotBlank = O campo numero não pode ser vazio
* Column= modificando o nome do campo, o tamanho, não pode cadastrar quando for nulo	
*/	
	@NotBlank
	@Column(name="nr_numero", nullable=false, length=20)
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
/*
* Size = tamanho maximo permitido no campo 
* Column= modificando o nome do campo, o tamanho, pode cadastrar quando o campo for nulo	
*/	
	@Size(max =80)
	@Column(name="nm_complemento", nullable = true, length= 80)
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
/*
* NotBlank = O campo cidade não pode ser vazio
* Size= tamanho maximo de caracter para esse campo é 30
* Column= modificando o nome do campo, o tamanho, não pode cadastrar quando for nulo	
*/	
	@NotBlank @Size(max = 30)
	@Column(name="nm_cidade", nullable = false, length = 30)
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
/*
* NotBlank = O campo cep não pode ser vazio
* Size= tamanho maximo de caracter para esse campo é 10
* Column= modificando o nome do campo, o tamanho, não pode cadastrar quando for nulo	
*/		
	@NotBlank @Size(max=10)
	@Column(name="nr_cep", nullable = false, length = 10)
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
/*
* NotBlank = O campo cidade não pode ser vazio
* Size= tamanho maximo de caracter para esse campo é 30
* Column= modificando o nome do campo, o tamanho, não pode cadastrar quando for nulo	
*/	
	@NotBlank @Size(max = 30)
	@Column(name="nm_estado", nullable = false, length = 30)
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@ManyToOne
	@JoinColumn(name="cliente_codigo")
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
							//Método Equals e hashCode 
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
		Endereco other = (Endereco) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	//@Transient
	//public boolean isCodigo()
	//{
	//	return this.codigo == null;
	//}
	
	
}
