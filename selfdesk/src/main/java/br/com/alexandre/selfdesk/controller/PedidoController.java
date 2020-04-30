package br.com.alexandre.selfdesk.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexandre.selfdesk.dao.MaquinaDAO;
import br.com.alexandre.selfdesk.dao.PedidoDAO;
import br.com.alexandre.selfdesk.dao.SoftwareDAO;
import br.com.alexandre.selfdesk.model.Item;
import br.com.alexandre.selfdesk.model.Maquina;
import br.com.alexandre.selfdesk.model.Pedido;
import br.com.alexandre.selfdesk.model.Software;


@CrossOrigin("*")
@RestController
public class PedidoController {
	
	@Autowired
	private PedidoDAO dao;
	
	@Autowired
	private SoftwareDAO softwareDao;
	
	@Autowired
	private MaquinaDAO maquinaDao;
	
	
	@PostMapping("/pedido/novo")
	public ResponseEntity<Pedido> adicionarPedio(@RequestBody Pedido novo){
		try {
			
			for (Item i: novo.getItens()) {
				i.setPedido(novo);
			}
			dao.save(novo);
			
		    for( Item i: novo.getItens()) {
		    	Software s = i.getItemSoftware();
		    	s = softwareDao.findById(s.getId()).get();
		    	s.setQtdEstoque(s.getQtdEstoque()-1);
		    	softwareDao.save(s);
		    }
		    if(novo.getComputador()!=null) {
		    	Maquina m = novo.getComputador();
		    	m = maquinaDao.findById(m.getId()).get();
		    	m.setQtdEstoque(m.getQtdEstoque()-1);
		    	maquinaDao.save(m);
		    }
			return ResponseEntity.ok(novo);
		}
		catch(Exception e) {
			// bad request
			e.printStackTrace();
			return ResponseEntity.status(400).build();
		}
		
	}
	
	@GetMapping("/pedidos")
	public ResponseEntity<ArrayList<Pedido>> getAllPedido(){
		ArrayList<Pedido> lista = (ArrayList<Pedido>)dao.findAll();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/pedidos/{pedido}")
	public ResponseEntity<Pedido> getPedidoById(@PathVariable int pedido){
		Pedido encontrei = dao.findById(pedido).get();
		if(encontrei == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(encontrei);
		}
	}
	
	

}
