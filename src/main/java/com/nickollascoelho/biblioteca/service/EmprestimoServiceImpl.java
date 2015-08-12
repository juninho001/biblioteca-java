package com.nickollascoelho.biblioteca.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.nickollascoelho.biblioteca.exception.EntityNotFoundException;
import com.nickollascoelho.biblioteca.model.Emprestimo;
import com.nickollascoelho.biblioteca.repository.EmprestimoRepository;

@Service
public class EmprestimoServiceImpl implements EmprestimoService {

	@Resource
	private EmprestimoRepository repository;

	@Override
	@Transactional
	public List<Emprestimo> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	@Transactional
	public Emprestimo findById(Long id) {
		Emprestimo entity = repository.findOne(id);
		if (entity == null) {
			throw new EntityNotFoundException();
		}
		return entity;
	}

	@Override
	@Transactional
	public Emprestimo create(Emprestimo entity) {
		return repository.save(entity);
	}

	@Override
	@Transactional(rollbackFor = EntityNotFoundException.class)
	public Emprestimo update(Emprestimo entity) {
		if (!repository.exists(entity.getId())) {
			throw new EntityNotFoundException();
		}

		return repository.save(entity);
	}

	@Override
	@Transactional(rollbackFor = EntityNotFoundException.class)
	public Emprestimo delete(Long id) {
		if (!repository.exists(id)) {
			throw new EntityNotFoundException();
		}
		Emprestimo toDelete = repository.findOne(id);
		repository.delete(toDelete);
		return toDelete;
	}

}
