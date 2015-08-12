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

import com.nickollascoelho.biblioteca.exception.EntityNotFoundException;
import com.nickollascoelho.biblioteca.exception.EntityAlreadyExistsException;
import com.nickollascoelho.biblioteca.model.Livro;
import com.nickollascoelho.biblioteca.service.LivroService;

@RestController
public class LivroController {

	@Autowired
	LivroService service;

	@RequestMapping(value = "/livro", method = RequestMethod.GET)
	public ResponseEntity<List<Livro>> listAll() {
		System.out.println("listAll");
		return new ResponseEntity<List<Livro>>(service.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/livro/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Livro> getById(@PathVariable("id") long id) {
		try {
			Livro livro = service.findById(id);
			return new ResponseEntity<Livro>(livro, HttpStatus.OK);
		} catch (EntityNotFoundException p) {
			return new ResponseEntity<Livro>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/livro", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Livro livro, UriComponentsBuilder ucBuilder) {
		try {
			Livro saved = service.create(livro);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(saved.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (EntityAlreadyExistsException e) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/livro", method = RequestMethod.PUT)
	public ResponseEntity<Livro> update(@RequestBody Livro livro) {
		try {
			Livro updated = service.update(livro);
			return new ResponseEntity<Livro>(updated, HttpStatus.OK);
		} catch (EntityNotFoundException p) {
			return new ResponseEntity<Livro>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/livro/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Livro> delete(@PathVariable("id") long id) {
		try {
			service.delete(id);
			return new ResponseEntity<Livro>(HttpStatus.NO_CONTENT);
		} catch (EntityNotFoundException p) {
			return new ResponseEntity<Livro>(HttpStatus.NOT_FOUND);

		}
	}

}