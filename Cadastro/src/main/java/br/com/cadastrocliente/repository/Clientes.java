package br.com.cadastrocliente.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.cadastrocliente.model.Cliente;
import br.com.cadastrocliente.repository.filter.ClienteFilter;
import br.com.cadastrocliente.service.NegocioException;
import br.com.cadastrocliente.util.jpa.Transactional;

public class Clientes implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	
	public Cliente guardar(Cliente cliente)
	{
		return manager.merge(cliente);
	}
	
	public List<Cliente> porNome(String nome) 
	{
		
		return manager.createQuery("from Cliente where upper(nome) like :nome", Cliente.class)
				.setParameter("nome", nome.toUpperCase() +"%").getResultList();
				
	}
	
	@SuppressWarnings("unchecked")
	public List<Cliente> filtrados(ClienteFilter filtro)
	{
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Cliente.class);
		
		if(StringUtils.isNotBlank(filtro.getCpf()))
		{
			criteria.add(Restrictions.eq("cpf", filtro.getCpf()));
		}
		if(StringUtils.isNotBlank(filtro.getNome()))
		{
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
	 
	/*
	@SuppressWarnings("unchecked")
	public List<Produto> filtrados(ProdutoFilter filtro) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Produto.class);
		
		if (StringUtils.isNotBlank(filtro.getSku())) {
			criteria.add(Restrictions.eq("sku", filtro.getSku()));
		}
		
		if (StringUtils.isNotBlank(filtro.getNome())) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}
		
		return criteria.addOrder(Order.asc("nome")).list();
	}
*/
	public Cliente porId(Long id) 
	{
		
		return manager.find(Cliente.class, id);
	}

	@Transactional
	public void remover(Cliente cliente) throws NegocioException
	{
		try
		{
			cliente = porId(cliente.getCodigo());
			manager.remove(cliente);
			manager.flush();
		}catch(PersistenceException ne)
		{
			new NegocioException("Cliente não pode ser removido");
		}
		
	}

	//createQuery("from Produto where upper(nome) like :nome",Produto.class)
	//.setParameter("nome", nome.toUpperCase() + "%").getResultList();
	
}
