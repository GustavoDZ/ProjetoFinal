package br.com.alexandre.selfdesk.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.alexandre.selfdesk.model.Usuario;

public interface UsuarioDAO extends CrudRepository<Usuario, Integer> {
	
//data access object
	
	public Usuario findByEmailAndSenha(String email, String senha);
	public Usuario findByRacfAndSenha(String racf, String senha);
}
