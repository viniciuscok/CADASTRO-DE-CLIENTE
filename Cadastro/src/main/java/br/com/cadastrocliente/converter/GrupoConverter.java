package br.com.cadastrocliente.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.cadastrocliente.model.Grupo;
import br.com.cadastrocliente.repository.Grupos;

@FacesConverter(forClass=Grupo.class)
public class GrupoConverter implements Converter{

	@Inject
	private Grupos grupos;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) 
	{
		
		Grupo retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Long codigo = new Long(value);
			retorno = grupos.porCodigo(codigo);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) 
	{
		
		if (value != null) 
		{
			Grupo grupo = (Grupo) value;
			return grupo.getCodigo() == null ? null : grupo.getCodigo().toString();
		}
		
		return "";
	
	}

}
