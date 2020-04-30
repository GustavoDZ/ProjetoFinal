package br.com.alexandre.selfdesk.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexandre.selfdesk.dao.MaquinaDAO;
import br.com.alexandre.selfdesk.model.Maquina;

@CrossOrigin("*")
@RestController
public class MaquinaController {

	@Autowired
	private MaquinaDAO dao;
	
	@GetMapping("/maquinas")
	public ResponseEntity<ArrayList<Maquina>> getAllMaquina(){
		ArrayList<Maquina> lista = (ArrayList<Maquina>)dao.findAll();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/maquinas/disponiveis")
	public ResponseEntity<ArrayList<Maquina>> getDisponiveis(){
		ArrayList<Maquina> disp =  (ArrayList<Maquina>) dao.findByQtdEstoqueGreaterThan(0);
	    return ResponseEntity.ok(disp);
	}
	
	@GetMapping("/maquinas/{id}")
	public ResponseEntity<Maquina> getMaquinaById(@PathVariable int id){
		Maquina encontrei = dao.findById(id).get();
		if(encontrei == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(encontrei);
		}
	}
}
