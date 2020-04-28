package br.com.projeto.ProjetoFinal.Dao;

import org.springframework.data.repository.CrudRepository;

import br.com.projeto.ProjetoFinal.Model.Usuario;

public interface UsuarioDAO extends  CrudRepository<Usuario, Integer>{
	
	public Usuario findByEmailAndSenha(String email, String senha);
	public Usuario findByRacfAndSenha(String racf, String senha);

}
