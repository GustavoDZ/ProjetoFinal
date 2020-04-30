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

import br.com.alexandre.selfdesk.dao.UsuarioDAO;
import br.com.alexandre.selfdesk.model.Usuario;

@CrossOrigin("*")
@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO dao;
	
	@GetMapping("/usuarios")
	public ResponseEntity<ArrayList<Usuario>> getAllUsers(){
		ArrayList<Usuario> lista = (ArrayList<Usuario>)dao.findAll();
		return ResponseEntity.ok(lista);
		
	}
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> getUserById(@PathVariable int id){
		Usuario encontrei = dao.findById(id).get();
		if(encontrei == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(encontrei);
		}
	}
	@PostMapping("/login")
	public ResponseEntity<Usuario> login(@RequestBody Usuario userDoForm){
		Usuario logado = dao.findByEmailAndSenha(userDoForm.getEmail(), userDoForm.getSenha());
		if(logado == null) {
			return ResponseEntity.status(403).build();
		}
		else {
			return ResponseEntity.ok(logado);
		}
	}
	
	@PostMapping("/login/racf")
	public ResponseEntity<Usuario> loginracf(@RequestBody Usuario userDoForm){
		Usuario logado = dao.findByRacfAndSenha(userDoForm.getRacf(), userDoForm.getSenha());
		if(logado == null) {
			return ResponseEntity.status(403).build();
		}
		else {
			return ResponseEntity.ok(logado);
		}
	}
}
