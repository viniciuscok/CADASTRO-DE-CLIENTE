package teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.cadastrocliente.model.Cliente;
import br.com.cadastrocliente.model.TipoPessoa;

public class Teste 
{
	public static void main(String[] args) 
	{
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("CadastroPU");
		EntityManager manager = factory.createEntityManager();

		EntityTransaction trx = manager.getTransaction();
		trx.begin();

		Cliente cliente = new Cliente();
		cliente.setNome("João das Couves");
		cliente.setTipoPessoa(TipoPessoa.FISICA);
		cliente.setDocumento("123.456.789-90");
	
		//cliente.setDocumentoReceitaFederal("123.123.123-12");
		//cliente.setTipo(TipoPessoa.FISICA);

		//Contato contato = new Contato();
		//contato.setEmail("vinicius_cok@hotmail.com");
		//contato.setCelular("11111111");
		//contato.setSite("www");
		//contato.setTelefone("111111");
		//contato.setWhatsapp("1111111");
		//contato.setCliente(cliente);
		
		
		//Pedido pedido = new Pedido();
		//pedido.setNome("tito");
		//pedido.setCliente(cliente);
		
		//Endereco endereco = new Endereco();
		//endereco.setLogradouro("Rua das Aboboras Vermelhas");
		//endereco.setNumero("111");
		//endereco.setCidade("Uberlândia");
		//endereco.setUf("MG");
		//endereco.setCep("38400-000");
		//endereco.setCliente(cliente);

		
		
		//cliente.getPedidos().add(pedido);
		//cliente.getContatos().add(contato);
		//cliente.getEnderecos().add(endereco);
	

		//if(cliente.getContatos() !=null && cliente.getEnderecos() != null)
		
		if(cliente.getNome() != null)
		{
			System.out.println("array não é nulo");
			manager.persist(cliente);
		}else
		{
			System.out.println("array nulo");
		}
		

		trx.commit();
		
	}

}
