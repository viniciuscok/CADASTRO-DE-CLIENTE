package br.com.cadastrocliente.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cadastrocliente.model.Usuario;
import br.com.cadastrocliente.repository.Usuarios;

@Named
@ViewScoped
public class PesquisaUsuariosBean implements Serializable
{

	private static final long serialVersionUID = 1L;

	@Inject
	private Usuarios usuarios;
	
	private List<Usuario> buscarUsuarios;
	
	private Usuario usuario;
	
	
	public PesquisaUsuariosBean()
	{
		usuario = new Usuario();
	}
	
	public void pesquisar()
	{
		this.buscarUsuarios = usuarios.buscarPorNome(this.usuario.getNome());
	}

	public List<Usuario> getBuscarUsuarios() {
		return buscarUsuarios;
	}

	public void setBuscarUsuarios(List<Usuario> buscarUsuarios) {
		this.buscarUsuarios = buscarUsuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
