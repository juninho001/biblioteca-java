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
import com.nickollascoelho.biblioteca.model.Emprestimo;
import com.nickollascoelho.biblioteca.service.EmprestimoService;

@RestController
public class EmprestimoController {

	@Autowired
	EmprestimoService service;

	@RequestMapping(value = "/emprestimo", method = RequestMethod.GET)
	public ResponseEntity<List<Emprestimo>> listAll() {
		return new ResponseEntity<List<Emprestimo>>(service.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/emprestimo/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Emprestimo> getById(@PathVariable("id") long id) {
		try {
			Emprestimo emprestimo = service.findById(id);
			return new ResponseEntity<Emprestimo>(emprestimo, HttpStatus.OK);
		} catch (EntityNotFoundException p) {
			return new ResponseEntity<Emprestimo>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/emprestimo", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Emprestimo emprestimo, UriComponentsBuilder ucBuilder) {
		try {
			Emprestimo saved = service.create(emprestimo);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(saved.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (EntityAlreadyExistsException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/emprestimo", method = RequestMethod.PUT)
	public ResponseEntity<Emprestimo> update(@RequestBody Emprestimo emprestimo) {
		try {
			Emprestimo updated = service.update(emprestimo);
			return new ResponseEntity<Emprestimo>(updated, HttpStatus.OK);
		} catch (EntityNotFoundException p) {
			return new ResponseEntity<Emprestimo>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/emprestimo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Emprestimo> delete(@PathVariable("id") long id) {
		try {
			service.delete(id);
			return new ResponseEntity<Emprestimo>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException p) {
			return new ResponseEntity<Emprestimo>(HttpStatus.NOT_FOUND);

		}
	}

}