package br.com.alexandre.selfdesk.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.alexandre.selfdesk.model.Pedido;

public interface PedidoDAO extends CrudRepository<Pedido, Integer>  {

}
