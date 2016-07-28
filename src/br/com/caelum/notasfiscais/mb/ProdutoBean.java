package br.com.caelum.notasfiscais.mb;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;

@Transactional
@Named
@SessionScoped
public class ProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Produto produto = new Produto();
	private List<Produto> produtos;
	private Double total;
	
	@Inject
	private ProdutoDao dao;

	public Produto getProduto() {
		return produto;
	}

	public Double getTotal() {
		return total;
	}

	public void grava() {
		System.out.println("Será que vai passsar por aqui? ");
		if (produto.getId() == null) {
			dao.adiciona(produto);
		} else {
			dao.atualiza(produto);
		}
		this.produtos = dao.listaTodos();
		this.produto = new Produto();
	}

	public List<Produto> getProdutos() {
		if (produtos == null) {
			System.out.println("Carregando produtos...");
			produtos = dao.listaTodos();
		}
		return produtos;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double somar() {
		total = 0.0;
		for (Produto produto : produtos) {
			total = total + produto.getPreco();
		}
		return total;
	}

	public void remove(Produto p) {
		dao.remove(p);
		this.produtos = dao.listaTodos();
	}

	public void limpaFormulario() {
		this.produto = new Produto();
	}

}
