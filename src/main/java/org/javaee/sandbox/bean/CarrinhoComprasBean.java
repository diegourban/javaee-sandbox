package org.javaee.sandbox.bean;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.javaee.sandbox.dao.LivroDao;
import org.javaee.sandbox.model.CarrinhoCompras;
import org.javaee.sandbox.model.CarrinhoItem;
import org.javaee.sandbox.model.Livro;

@Model
public class CarrinhoComprasBean {

	@Inject
	private LivroDao livroDao;

	@Inject
	private CarrinhoCompras carrinho;

	public String add(Integer livroId) {
		final Livro livro = livroDao.buscarPorId(livroId);
		final CarrinhoItem carrinhoItem = new CarrinhoItem(livro);
		carrinho.add(carrinhoItem);

		return "/carrinho?faces-redirect=true";
	}
	
	public List<CarrinhoItem> getItens() {
		return carrinho.getItens();
	}
	
	public void remover(CarrinhoItem item) {
		carrinho.remover(item);
	}
}
