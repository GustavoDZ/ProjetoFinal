  
package br.com.alexandre.selfdesk.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.alexandre.selfdesk.model.Software;

public interface SoftwareDAO extends CrudRepository<Software, Integer>   {
	
	public List<Software> findByQtdEstoqueGreaterThan(int qtdEstoque);

}
