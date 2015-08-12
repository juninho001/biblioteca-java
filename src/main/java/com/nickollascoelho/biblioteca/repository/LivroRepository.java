package com.nickollascoelho.biblioteca.repository;

import com.nickollascoelho.biblioteca.model.Livro;
import org.springframework.data.repository.CrudRepository;

public interface LivroRepository extends CrudRepository<Livro, Long> {

}
