package br.com.cadastrocliente.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.cadastrocliente.model.Usuario;
import br.com.cadastrocliente.repository.Usuarios;

@FacesConverter(forClass=Usuario.class)
public class UsuarioConverter implements Converter{

	@Inject
	private Usuarios usuarios;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) 
	{
		Usuario retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Long codigo = new Long(value);
			retorno = usuarios.porCodigo(codigo);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) 
	{
		
		if (value != null) 
		{
			Usuario usuario = (Usuario) value;
			return usuario.getCodigo() == null ? null : usuario.getCodigo().toString();
		}
		
		return "";
	
	}

}
