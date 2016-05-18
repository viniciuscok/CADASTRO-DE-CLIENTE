package br.com.cadastrocliente.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.cadastrocliente.model.Cliente;
import br.com.cadastrocliente.repository.Clientes;

@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter 
{


	@Inject
	private Clientes clientes;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			retorno = clientes.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cliente cliente = (Cliente) value;
			return cliente.getCodigo() == null ? null : cliente.getCodigo().toString();
		}
		
		return "";
	}

}
