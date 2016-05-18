package br.com.cadastrocliente.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cadastrocliente.model.Grupo;
import br.com.cadastrocliente.model.Usuario;
import br.com.cadastrocliente.repository.Grupos;
import br.com.cadastrocliente.service.CadastroUsuarioService;
import br.com.cadastrocliente.service.NegocioException;
import br.com.cadastrocliente.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroUsuarioBean implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Grupos grupos;
	
	@Inject
	private CadastroUsuarioService cadastroUsuarioService;
	
	private List<Grupo> gruposRaizes; 
	
	private Grupo grupoSelecionado;
	private Grupo removerGrupoSelecionado;
	
	private Usuario usuario;
	
/*
 * Construtor padrão para inicializar o usuário	
 */

	public CadastroUsuarioBean()
	{
		System.out.println("inicializou o construtor");
		this.limpar();
		System.out.println("saiu do construtor");
	}

/*
 * Método para carregar os grupos cadastrados no banco e exibir ao carregar a página	
 */
	public void inicializar()
	{
		if(usuario==null)
		{
			limpar();
		}
		System.out.println("inicializou o inicializar");
		this.gruposRaizes = grupos.buscarTodos();
		System.out.println("fechou o inicializar");
	}

/*
 * Método para salvar o usuário	
 */
	
	public void salvar()
	{
		try
		{
			this.cadastroUsuarioService.salvar(this.usuario);
			FacesUtil.addInfoMessage("usuário adicionado com sucesso.");
			
		}catch(NegocioException ne)
		{
			FacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void removerGrupo()
	{
		this.usuario.getGrupos().remove(this.removerGrupoSelecionado);
	}

/*
 * Método para adicionar um ou mais grupos, onde existe uma condição que se o grupoSelecionado for diferente de
 * nulo e grupoSelecionado já foi adicionado ele não pode cadastrar o mesmo grupo para essa pessoa e não pode
 * cadastrar um grupo nulo. 	
 */
	public void adicionarGrupo()
	{
		if(this.grupoSelecionado != null && !(this.usuario.getGrupos().contains(grupoSelecionado)))
		{
			this.usuario.getGrupos().add(this.grupoSelecionado);
			System.out.println("nome: "+ grupoSelecionado.getNome());
			
		}else
		{			
			FacesUtil.addErrorMessage("Grupo selecionado já existe.");
		}
		
	}
	
/*
 * Método para limpar um novo usuário.	
 */
	public void limpar()
	{
		this.usuario = new Usuario();
		
	}
	
									//metodos getters e setters	
//-----------------------------------------------------------------------------------------------------------------	
		
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Grupos getGrupos() {
		return grupos;
	}

	public void setGrupos(Grupos grupos) {
		this.grupos = grupos;
	}


	public List<Grupo> getGruposRaizes() {
		return gruposRaizes;
	}

	public void setGruposRaizes(List<Grupo> gruposRaizes) {
		this.gruposRaizes = gruposRaizes;
	}

	public Grupo getGrupoSelecionado() {
		return grupoSelecionado;
	}

	public void setGrupoSelecionado(Grupo grupoSelecionado) {
		this.grupoSelecionado = grupoSelecionado;
	}

	public Grupo getRemoverGrupoSelecionado() {
		return removerGrupoSelecionado;
	}

	public void setRemoverGrupoSelecionado(Grupo removerGrupoSelecionado) {
		this.removerGrupoSelecionado = removerGrupoSelecionado;
	}

	
	
	
	
	
	
	
	
}
