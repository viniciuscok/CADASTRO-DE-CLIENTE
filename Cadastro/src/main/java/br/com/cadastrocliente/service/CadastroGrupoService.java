package br.com.cadastrocliente.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.cadastrocliente.model.Grupo;
import br.com.cadastrocliente.repository.Grupos;
import br.com.cadastrocliente.util.jpa.Transactional;

public class CadastroGrupoService implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Grupos grupos;
	
	@Transactional
	public Grupo salvar(Grupo grupo)
	{
					
		return grupos.guardar(grupo);
	}
}
