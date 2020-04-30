package br.com.alexandre.selfdesk.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.alexandre.selfdesk.dao.SoftwareDAO;
import br.com.alexandre.selfdesk.model.Software;

@CrossOrigin("*")
@RestController
public class SoftwareController {
	
	@Autowired
	private SoftwareDAO dao;
	
	@GetMapping("/softwares")
	public ResponseEntity<ArrayList<Software>> getAllSoftware(){
		ArrayList<Software> lista = (ArrayList<Software>)dao.findAll();
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/softwares/disponiveis")
	public ResponseEntity<ArrayList<Software>> getDisponiveis(){
		ArrayList<Software> disp =  (ArrayList<Software>) dao.findByQtdEstoqueGreaterThan(0);
	    return ResponseEntity.ok(disp);
	}
	
	@GetMapping("/softwares/{id}")
	public ResponseEntity<Software> getSoftwareById(@PathVariable int id){
		Software encontrei = dao.findById(id).get();
		if(encontrei == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(encontrei);
		}
	}
	
}
