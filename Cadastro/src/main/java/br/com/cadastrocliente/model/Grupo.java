package br.com.cadastrocliente.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="grupo")
public class Grupo implements Serializable 
{

	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String nome;
	private String descricao;
	
	
	
							//Método getters e setters com bean validation e anotações de jpa 
//------------------------------------------------------------------------------------------------------------------------


/*
 * Id= chave primária
 * @GenereratedValue= gerando a chave primária automaticamente	
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
 * 	NotBlank= O campo não pode está vazio.
 *  Size= tamanho máximo permitido para o campo é de 50 caracter.
 *  Column= atribuindo o nome do campo, tamanho e um valor não nulo. 
 */
	@NotBlank @Size(max=80)
	@Column(name="nm_grupo", nullable = false, length= 50)	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
/*
 * Column= atribuindo o nome do campo e o valor desse campo pode ser nulo.	
 */
	@Column(name="nm_descricao", nullable = true)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
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
		Grupo other = (Grupo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	

}
