package com.nickollascoelho.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.nickollascoelho.biblioteca.exception.EntityAlreadyExistsException;
import com.nickollascoelho.biblioteca.exception.EntityNotFoundException;
import com.nickollascoelho.biblioteca.model.Pessoa;
import com.nickollascoelho.biblioteca.service.PessoaService;

@RestController
public class PessoaController {

	@Autowired
	PessoaService service;

	@RequestMapping(value = "/pessoa", method = RequestMethod.GET)
	public ResponseEntity<List<Pessoa>> listAll() {
		return new ResponseEntity<List<Pessoa>>(service.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pessoa> getById(@PathVariable("id") long id) {
		try {
			Pessoa pessoa = service.findById(id);
			return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
		} catch (EntityNotFoundException p) {
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/pessoa", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Pessoa pessoa, UriComponentsBuilder ucBuilder) {
		try {
			Pessoa saved = service.create(pessoa);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(saved.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (EntityAlreadyExistsException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/pessoa", method = RequestMethod.PUT)
	public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa) {
		try {
			Pessoa updated = service.update(pessoa);
			return new ResponseEntity<Pessoa>(updated, HttpStatus.OK);
		} catch (EntityNotFoundException p) {
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/pessoa/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Pessoa> delete(@PathVariable("id") long id) {
		try {
			service.delete(id);
			return new ResponseEntity<Pessoa>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException p) {
			return new ResponseEntity<Pessoa>(HttpStatus.NOT_FOUND);

		}
	}

}