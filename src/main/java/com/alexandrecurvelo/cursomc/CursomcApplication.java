package com.alexandrecurvelo.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alexandrecurvelo.cursomc.domain.Categoria;
import com.alexandrecurvelo.cursomc.domain.Cidade;
import com.alexandrecurvelo.cursomc.domain.Cliente;
import com.alexandrecurvelo.cursomc.domain.Endereco;
import com.alexandrecurvelo.cursomc.domain.Estado;
import com.alexandrecurvelo.cursomc.domain.Pagamento;
import com.alexandrecurvelo.cursomc.domain.PagamentoComBoleto;
import com.alexandrecurvelo.cursomc.domain.PagamentoComCartao;
import com.alexandrecurvelo.cursomc.domain.Pedido;
import com.alexandrecurvelo.cursomc.domain.Produto;
import com.alexandrecurvelo.cursomc.domain.enums.EstadoPagamento;
import com.alexandrecurvelo.cursomc.domain.enums.TipoCliente;
import com.alexandrecurvelo.cursomc.repositories.CategoriaRepository;
import com.alexandrecurvelo.cursomc.repositories.CidadeRepository;
import com.alexandrecurvelo.cursomc.repositories.ClienteRepository;
import com.alexandrecurvelo.cursomc.repositories.EnderecoRepository;
import com.alexandrecurvelo.cursomc.repositories.EstadoRepository;
import com.alexandrecurvelo.cursomc.repositories.PagamentoRepository;
import com.alexandrecurvelo.cursomc.repositories.PedidoRepository;
import com.alexandrecurvelo.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
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
		
		//Categoria - produto
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
				
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		//Estado - Cidade
		Estado est1 = new Estado(null,"Rio de Janeiro");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Rio de Janeiro", est1);
		Cidade c2 = new Cidade(null, "Santos", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));		
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		//Cliente
		Cliente cli1 = new Cliente(null, "Alexandre Curvelo", "allexolic@gmail.com", "10066046777", TipoCliente.PESSOAFISICA);
		
		//Telefone
		cli1.getTelefones().addAll(Arrays.asList("965155338", "95154022"));
		
		//Endereco
		Endereco e1 = new Endereco(null, "Rua Conde de Baependi", "54", "Apto 301", "Flamengo", "22231140", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Gustavo Riedel", "376", "Casa 1", "Engenho de Dentro", "20730010", cli1, c1);
		Endereco e3 = new Endereco(null, "Rua Paulina", "42", "apto 1", "Moca", "22234482", cli1, c3);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2,e3));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2,e3));
		
		//Pedido
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2020 11:34"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("11/01/2021 21:02"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 5);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("13/01/2021 21:50"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
	}

}
