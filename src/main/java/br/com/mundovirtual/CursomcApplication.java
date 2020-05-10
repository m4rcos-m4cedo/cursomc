package br.com.mundovirtual;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mundovirtual.domain.Categoria;
import br.com.mundovirtual.domain.Cidade;
import br.com.mundovirtual.domain.Cliente;
import br.com.mundovirtual.domain.Endereco;
import br.com.mundovirtual.domain.Estado;
import br.com.mundovirtual.domain.Pagamento;
import br.com.mundovirtual.domain.PagamentoComBoleto;
import br.com.mundovirtual.domain.PagamentoComCartao;
import br.com.mundovirtual.domain.Pedido;
import br.com.mundovirtual.domain.Produto;
import br.com.mundovirtual.domain.enums.EstadoPagamento;
import br.com.mundovirtual.domain.enums.TipoCliente;
import br.com.mundovirtual.repositories.CategoriaRepository;
import br.com.mundovirtual.repositories.CidadeRepository;
import br.com.mundovirtual.repositories.ClienteRepository;
import br.com.mundovirtual.repositories.EnderecoRepository;
import br.com.mundovirtual.repositories.EstadoRepository;
import br.com.mundovirtual.repositories.PagamentoRepository;
import br.com.mundovirtual.repositories.PedidoRepository;
import br.com.mundovirtual.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 50.00);
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cliente = new Cliente(null, "Marcos Macedo", "ricardo.paiva.macedo@hotmail.com", "36915363874", TipoCliente.PESSOAFISICA);
		
		cliente.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cliente, c1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cliente, c2);
		
		cliente.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(cliente));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		
		Pedido p1 = new Pedido(null, sdf.parse("01/05/2020 10:32"), end1 , cliente);
		Pedido p2 = new Pedido(null, sdf.parse("02/05/2020 11:49"), end2, cliente);
		
		Pagamento pg1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, p1, 6);
		Pagamento pg2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, p2, sdf.parse("15/05/2020 00:00"), null);
		
		p1.setPagamento(pg1);
		p2.setPagamento(pg2);
		
		cliente.getPedidos().addAll(Arrays.asList(p1, p2));
		
		pedidoRepository.saveAll(Arrays.asList(p1, p2));
		pagamentoRepository.saveAll(Arrays.asList(pg1, pg2));
		
	}

}
