package com.nickollascoelho.biblioteca.repository;

import org.springframework.data.repository.CrudRepository;

import com.nickollascoelho.biblioteca.model.Emprestimo;

public interface EmprestimoRepository extends CrudRepository<Emprestimo, Long> {

}
