package br.com.caelum.notasfiscais.mb;


import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.notasfiscais.dao.NotaFiscalDao;
import br.com.caelum.notasfiscais.dao.ProdutoDao;
import br.com.caelum.notasfiscais.modelo.Item;
import br.com.caelum.notasfiscais.modelo.NotaFiscal;
import br.com.caelum.notasfiscais.modelo.Produto;
import br.com.caelum.notasfiscais.tx.Transactional;
@Transactional
@Named
@ViewScoped
public class NotaFiscalBean {

	private Item item = new Item();
	private Long idProduto = 0L;
	private NotaFiscal notaFiscal = new NotaFiscal();
	
	@Inject
	private ProdutoDao produtoDao;
	@Inject
	private NotaFiscalDao notaFiscalDao;
	
	
	public void gravar(){
		this.notaFiscalDao.adiciona(notaFiscal);
		this.notaFiscal = new NotaFiscal();
	}
	
	public void guardaItem(){
		Produto produto = produtoDao.buscaPorId(idProduto);
		item.setProduto(produto);
		item.setValorUnitario(produto.getPreco());
		
		notaFiscal.getItens().add(item);
		item.setNotaFiscal(notaFiscal);
		
		item = new Item();
		idProduto = null;
		
	}
	
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}
	
	public Item getItem() {
		return item;
	}
	
	public Long getIdProduto() {
		return idProduto;
	}
	
	public ProdutoDao getProdutoDao() {
		return produtoDao;
	}
	
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
}
