package br.com.cadastrocliente.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import br.com.cadastrocliente.validation.CPFCNPJ;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String nome;
	private String documento;
	private Date dataCadastro;
	private TipoPessoa tipoPessoa = TipoPessoa.FISICA;
	private List<Contato> contatos = new ArrayList<Contato>();
	private List<Endereco> enderecos = new ArrayList<Endereco>();
	private String observacao;

	
	

							//Método getters e setters com bean validation e anotações de jpa 
//------------------------------------------------------------------------------------------------------------------------
	
//Atribuindo uma chave primária(codigo) auto-increment
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	//@NotBlank(message="CPF/CNPJ deve ser preenchido")
	@CPFCNPJ
	@Column(name="nr_documento", nullable=false)
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
//NotBlank= O campo não pode ficar em branco(espaços apenas), Column= atribuindo o nome do campo, tamanho e um valor não nulo.
	@NotBlank @Size(max=100)
	@Column(name="nm_cliente", nullable = false, length = 100)
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
/*NotBlank= o campo cpf não pode ser vazio, Enumerated= anotação para enum pegando a String, 
 *column= Nome do campo, não pode cadastrar quando for nulo, e o tamnho do campo  	
*/	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_clinte", nullable = false, length= 30)
	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}
	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}
	
/*
 * NotNull= o contato não pode ser nulo, OneToMany= um para muitos mappedby campo da outra classe que está sendo mapeado
 * CascadeType.ALL= quando um cliente for excluído o contato é excluído junto com ele.	
 */
	
	@NotNull(message="o contato deve ser informado")
	@OneToMany(mappedBy="cliente", cascade= CascadeType.ALL)	
	public List<Contato> getContatos() {
		return contatos;
	}
	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}
	
/*
* NotNull= o endereço não pode ser nulo, OneToMany= um para muitos mappedby campo da outra classe que está sendo mapeado
* CascadeType.ALL= quando um cliente for excluído o endereço é excluído junto com ele.	
*/	
	
	@NotNull
	@OneToMany(mappedBy="cliente", cascade= CascadeType.ALL)	
	public List<Endereco> getEnderecos() {
		return enderecos;
	}
	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}
	
//Column= define o tamnho do texto para armazenamento
	@Column(columnDefinition="text")
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
		
	
/* NotNull = o campo não pode ser nulo, Temporal= tipo de anotação para pegar a data, 
 * Collunm = seta o nome da coluna e atribui para que ela nã oseja nula
*/ 	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_cadastro", nullable = false)
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
	
	//@OneToMany(mappedBy="cliente", cascade= CascadeType.ALL)	
	//public List<Pedido> getPedidos() {
	//	return pedidos;
	//}
	//public void setPedidos(List<Pedido> pedidos) {
	//	this.pedidos = pedidos;
	//}
	
											//Método equals e hashcode
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
		Cliente other = (Cliente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
									//outros métodos
//----------------------------------------------------------------------------------------------------------------------	
/*
 * Transient= não é persistido no banco de dados
 * Esse método verifica se pessoa FISICA for igual A pessoa fisica ele retorna true.
 */
	@Transient
	public boolean isPessoaFisica()
	{
		return TipoPessoa.FISICA !=null && tipoPessoa.equals(TipoPessoa.FISICA);
	}
	
/*
* Transient= não é persistido no banco de dados
* Esse método verifica se pessoa JURIDICA for igual A (pessoa juridica) ele retorna true.
*/	
	@Transient
	public boolean isPessoaJuridica()
	{
		return TipoPessoa.JURIDICA !=null && TipoPessoa.JURIDICA.equals(this.getTipoPessoa());
	}
	
	
}
