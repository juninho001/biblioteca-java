package com.nickollascoelho.biblioteca.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.nickollascoelho.biblioteca.exception.EntityNotFoundException;
import com.nickollascoelho.biblioteca.model.Livro;
import com.nickollascoelho.biblioteca.repository.LivroRepository;

@Service
public class LivroServiceImpl implements LivroService {

	@Resource
	private LivroRepository repository;

	@Override
	@Transactional
	public List<Livro> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	@Transactional
	public Livro findById(Long id) {
		Livro entity = repository.findOne(id);
		if (entity == null) {
			throw new EntityNotFoundException();
		}
		return entity;
	}

	@Override
	@Transactional
	public Livro create(Livro entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional(rollbackFor = EntityNotFoundException.class)
	public Livro update(Livro entity) {
		if (!repository.exists(entity.getId())) {
			throw new EntityNotFoundException();
		}

		return repository.save(entity);
	}

	@Override
	@Transactional(rollbackFor = EntityNotFoundException.class)
	public Livro delete(Long id) {
		if (!repository.exists(id)) {
			throw new EntityNotFoundException();
		}
		Livro toDelete = repository.findOne(id);
		repository.delete(toDelete);
		return toDelete;
	}

}
