package br.com.alexandre.selfdesk.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.alexandre.selfdesk.model.Maquina;

public interface MaquinaDAO extends CrudRepository<Maquina, Integer>   {
	
	public List<Maquina> findByQtdEstoqueGreaterThan(int qtdEstoque);
}
