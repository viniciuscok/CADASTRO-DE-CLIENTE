package br.com.cadastrocliente.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.cadastrocliente.model.Cliente;
import br.com.cadastrocliente.repository.Clientes;
import br.com.cadastrocliente.util.jpa.Transactional;

public class CadastroClienteService implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Clientes clientes;
	
	@Transactional
	public Cliente salvar(Cliente cliente) throws NegocioException
	{
		if(cliente.getContatos()==null || cliente.getEnderecos() == null)
			
		{
			System.out.println("entrou noservice");
			throw new NegocioException("Usuário não pode ser cadastrado");
		}
		return clientes.guardar(cliente); 
	}

}
