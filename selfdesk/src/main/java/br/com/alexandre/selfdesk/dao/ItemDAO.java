package br.com.alexandre.selfdesk.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.alexandre.selfdesk.model.Item;

public interface ItemDAO extends CrudRepository<Item, Integer>   {

}
