package br.com.cadastrocliente.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	
	private Long codigo;
	private String nome;
	private String email;
	private String senha;
	private List<Grupo> grupos = new ArrayList<Grupo>();
	
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
 *  Size= tamanho máximo permitido para o campo é de 100 caracter.
 *  Column= atribuindo o nome do campo, tamanho e um valor não nulo. 
 */
	@NotBlank @Size(max=100)
	@Column(name="nm_usuario", nullable = false, length= 100)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
/*
 *  NotBlank= O campo não pode está vazio.
 *  Email= Validando o email do campo.
 *  Column= atribuindo o nome do campo, tamanho e um valor não nulo.	
 */
	@NotBlank @Email
	@Column(name="nm_email", nullable = false, length = 80)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
/*
 * NotBlank= O campo não pode está vazio.
 * Size= tamanho máximo permitido para o campo é de 100 caracter.
 * Column= atribuindo o nome do campo, tamanho e um valor não nulo.
 */
	@NotBlank @Size(max=6 )
	@Column(name="senha", nullable = false, length = 6)
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@NotNull
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name= "usuario_grupo", joinColumns= @JoinColumn(name="usuario_codigo"), 
			inverseJoinColumns = @JoinColumn(name="grupo_codigo"))
	public List<Grupo> getGrupos() {
		return grupos;
	}
	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
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
		Usuario other = (Usuario) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	

}
