package br.com.cadastrocliente.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cadastrocliente.model.Cliente;
import br.com.cadastrocliente.model.Contato;
import br.com.cadastrocliente.model.Endereco;
import br.com.cadastrocliente.model.TipoPessoa;
import br.com.cadastrocliente.service.CadastroClienteService;
import br.com.cadastrocliente.service.NegocioException;
import br.com.cadastrocliente.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteBean implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	
	private Contato contato;
	
	private Endereco endereco;
	
	@Inject
	private CadastroClienteService cadastroClienteService;
	
	
/*
 * CadastroClienteBean= Método construtor executando ao carregar a página o método limpar(onde limpa os campos da tela)
 */
	public CadastroClienteBean()
	{
		this.limpar();
	}

/*
 * 	
 */
	public void inicializar()
	{
		if(this.cliente ==null)
		{
			limpar();
		}
	}
	
/*
 * 	
 */
	public void salvar() 
	{
		
		try
		{
			this.cadastroClienteService.salvar(this.cliente);
			FacesUtil.addInfoMessage("Cliente Cadastrado com sucesso");
			
			this.limpar();
		}catch(NegocioException ne)
		{
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
/*
 * 	instanciando o contatocaso seja nulo vamos criar um novo objeto, se não for nulo ele retorna a 
 * lista de contatos	
 */
	public void novoContato()
	{
		if(this.contato == null)
		{
			this.contato = new Contato();
		}else
		{
			this.cliente.getContatos();
		}
		
	}
	
/*
 * novoEndereco = instanciando o endereco caso seja nulo vamos criar um novo objeto, se não for nulo ele retorna a 
 * lista de endereços	
 */
	public void novoEndereco()
	{	
		if(this.endereco == null)
		{
			this.endereco = new Endereco();			
		}else
		{
			this.cliente.getEnderecos();
		}
	}
	
/*
 * 	adicionarContato= Método que pega uma lista de contato, pegando uma lista de contatos no cliente
*/ 
	public void adicionarContato()
	{
		this.cliente.getContatos().add(contato);
		this.contato.setCliente(cliente);
	}
	
/*
 * 	adicionarContato= Método que pega uma lista de contato, pegando uma lista de contatos no cliente
 */
	public void adicionarEndereco()
	{
		
		this.cliente.getEnderecos().add(endereco);
		this.endereco.setCliente(cliente);
	} 
	
/*
 *limpar= Método para limpar os campos da página
*/
	private void limpar() 
	{
		this.cliente = new Cliente();		
		this.contato = new Contato();
		this.endereco = new Endereco();
	}

	
//método para retorna os valores do tipo do cliente	
	public TipoPessoa[] getTipoPessoas()
	{
		return TipoPessoa.values();
	}
	
	
										//metodos getters e setters	
//-----------------------------------------------------------------------------------------------------------------	
	
	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		
//editando os campos de contato e endereco, onde se o cliente não for nulo ele retorna o conato e o endereco 		
		if(this.cliente !=null)
		{
			this.contato= this.cliente.getContatos().get(0);
			this.endereco = this.cliente.getEnderecos().get(0);
		}
		
	}

	public Contato getContato() {
		return contato;
	}
	
	public void setContato(Contato contato)
	{
		this.contato = contato;	
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	
	

	
	
	
	

}
