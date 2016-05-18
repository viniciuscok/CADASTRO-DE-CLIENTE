package br.com.cadastrocliente.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.cadastrocliente.model.Usuario;

public class Usuarios implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Usuario guardar(Usuario usuario)
	{
		return manager.merge(usuario);
	}

	public Usuario porCodigo(Long codigo) 
	{
		
		return manager.find(Usuario.class, codigo);
	}

	public List<Usuario> buscarPorNome(String nome) 
	{
		
		return manager.createQuery("from Usuario where upper(nome) like :nome", Usuario.class)
				.setParameter("nome", nome.toUpperCase()+"%").getResultList();
	}

	public Usuario porEmail(String nome) 
	{
		try
		{
			
			return manager.createQuery("from Usuario where upper(nome) :nome", Usuario.class)
					.setParameter("nome", nome.toUpperCase())
					.getSingleResult();
		}catch(NoResultException ne)
		{
			return null;
		}
	}
	
	/*public List<Cliente> porNome(String nome) 
	{
		
		return manager.createQuery("from Cliente where upper(nome) like :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() +"%").getResultList();
				
	}
*/
}
