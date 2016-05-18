package br.com.cadastrocliente.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import br.com.cadastrocliente.model.Grupo;
import br.com.cadastrocliente.service.NegocioException;

public class Grupos implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	
	public Grupo guardar(Grupo grupo)
	{
		return manager.merge(grupo);
	}
	
	public List<Grupo> buscarTodos()
	{
		return manager.createQuery("from Grupo", Grupo.class).getResultList();
	}

	public Grupo porCodigo(Long codigo) 
	{
		
		return manager.find(Grupo.class, codigo);
	}
	
	public void remover(Grupo grupo)
	{
		try
		{
			grupo = porCodigo(grupo.getCodigo());
			manager.remove(grupo);
			manager.flush();
		}catch(PersistenceException ne)
		{
			new NegocioException("Grupo não pode ser removido");
		}
	}
}
