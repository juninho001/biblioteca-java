package com.nickollascoelho.biblioteca.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.nickollascoelho.biblioteca.exception.EntityAlreadyExistsException;
import com.nickollascoelho.biblioteca.exception.EntityNotFoundException;
import com.nickollascoelho.biblioteca.model.Pessoa;
import com.nickollascoelho.biblioteca.repository.PessoaRepository;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Resource
	private PessoaRepository repository;

	@Override
	@Transactional
	public List<Pessoa> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	@Transactional
	public Pessoa findById(Long id) {
		Pessoa pessoa = repository.findOne(id);
		if (pessoa == null) {
			throw new EntityNotFoundException();
		}
		return pessoa;
	}

	@Override
	@Transactional
	public Pessoa create(Pessoa pessoa) {
		if (repository.exists(pessoa.getCpf())) {
			throw new EntityAlreadyExistsException();
		}
		
		return repository.save(pessoa);
	}

	@Override
	@Transactional(rollbackFor = EntityNotFoundException.class)
	public Pessoa update(Pessoa pessoa) {
		if (!repository.exists(pessoa.getCpf())) {
			throw new EntityNotFoundException();
		}

		pessoa.setId(repository.findByCpf(pessoa.getCpf()).getId());

		return repository.save(pessoa);
	}

	@Override
	@Transactional(rollbackFor = EntityNotFoundException.class)
	public Pessoa delete(Long id) {
		if (!repository.exists(id)) {
			throw new EntityNotFoundException();
		}
		Pessoa toDelete = repository.findOne(id);
		repository.delete(toDelete);
		return toDelete;
	}

}
