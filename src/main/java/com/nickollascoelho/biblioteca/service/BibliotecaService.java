package com.nickollascoelho.biblioteca.service;

import java.util.List;

public interface BibliotecaService<T> {
	
	 List<T> findAll();
	 T findById(Long id);
	 T create(T entity);
	 T update(T entity);
	 T delete(Long id);

}
