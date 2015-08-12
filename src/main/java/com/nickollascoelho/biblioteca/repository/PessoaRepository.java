package com.nickollascoelho.biblioteca.repository;

import com.nickollascoelho.biblioteca.model.Pessoa;
import org.springframework.data.repository.CrudRepository;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
	
	boolean exists(String cpf);
	
	Pessoa findByCpf(String cpf);

}
