package br.com.cadastrocliente.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cadastrocliente.model.Grupo;
import br.com.cadastrocliente.service.CadastroGrupoService;
import br.com.cadastrocliente.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroGrupoBean implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroGrupoService cadastroGrupoService;
	
	private Grupo grupo;
	
	public CadastroGrupoBean()
	{
		this.limpar();
	}
	
	public void salvar()
	{
		cadastroGrupoService.salvar(this.grupo);
		FacesUtil.addInfoMessage("Grupo cadastrado com sucesso.");
		this.limpar();
	}
	
	public void limpar()
	{
		grupo = new Grupo();
	}
	
	
								//metodos getters e setters	
//-----------------------------------------------------------------------------------------------------------------	

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	
	
	

}
