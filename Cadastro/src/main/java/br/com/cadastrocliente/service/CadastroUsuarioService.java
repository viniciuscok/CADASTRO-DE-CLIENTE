package br.com.cadastrocliente.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.cadastrocliente.model.Usuario;
import br.com.cadastrocliente.repository.Usuarios;
import br.com.cadastrocliente.util.jpa.Transactional;

public class CadastroUsuarioService implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuarios usuarios;
	
	@Transactional
	public Usuario salvar(Usuario usuario) throws NegocioException
	{
		//Usuario emailExistente = usuarios.porEmail(usuario.getNome());
		
		//if(emailExistente != null && emailExistente.equals(usuario))
		//{
		//	throw new NegocioException("Email já cadastrado");
		//}
		return usuarios.guardar(usuario);
	}

}
