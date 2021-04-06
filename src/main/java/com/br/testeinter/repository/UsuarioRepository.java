package com.br.testeinter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.testeinter.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByEmail(String email);
	
	@Query("SELECT count(u.id) > 0 FROM Usuario u WHERE u.email = :email AND (:id is null OR u.id <> :id)")
	public boolean existsByEmailNotInId(@Param("email") String email, @Param("id") Long id);
	
	public boolean existsById(Long id);
	
}
