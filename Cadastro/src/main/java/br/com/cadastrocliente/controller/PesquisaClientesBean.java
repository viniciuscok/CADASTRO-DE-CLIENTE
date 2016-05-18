package br.com.cadastrocliente.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cadastrocliente.model.Cliente;
import br.com.cadastrocliente.repository.Clientes;
import br.com.cadastrocliente.repository.filter.ClienteFilter;
import br.com.cadastrocliente.service.NegocioException;
import br.com.cadastrocliente.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClientesBean implements Serializable
{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private Clientes clientes;
	
	private List<Cliente> listaClientes; 
	
	private ClienteFilter filtro;
	
	private Cliente clienteSelecionado;
	
	public PesquisaClientesBean()
	{
		filtro = new ClienteFilter();
	}
	
	public void excluir()
	{
		try
		{
			clientes.remover(clienteSelecionado);
			listaClientes.remove(clienteSelecionado);
			FacesUtil.addInfoMessage("Cliente '"+clienteSelecionado.getNome()+"' excluído com sucesso.");
		}catch(NegocioException ne)
		{
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void pesquisar()
	{
		this.listaClientes = clientes.filtrados(filtro);
	}

									//metodos getters e setters	
//-----------------------------------------------------------------------------------------------------------------	

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public ClienteFilter getFiltro() {
		return filtro;
	}

	public void setFiltro(ClienteFilter filtro) {
		this.filtro = filtro;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	
	
	
} 
