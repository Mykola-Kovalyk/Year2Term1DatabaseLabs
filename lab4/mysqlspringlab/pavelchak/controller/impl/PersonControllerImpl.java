package com.pavelchak.controller.impl;

import com.pavelchak.controller.PersonController;
import com.pavelchak.domain.Book;
import com.pavelchak.domain.Person;
import com.pavelchak.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonControllerImpl implements PersonController {
    @Autowired
    private PersonService personService;


    @Override
    public List<Person> findAll() {
        return personService.findAll();
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return personService.findById(id);
    }

    @Override
    public int create(Person person) {
        return personService.create(person);
    }

    @Override
    public int update(Integer id, Person person) {
        return personService.update(id, person);
    }

    @Override
    public int delete(Integer id) {
        return personService.delete(id);
    }

    @Override
    public List<Book> findAllBooksBy(Integer id) {
        return personService.findAllBooksBy(id);
    }

    @Override
    public String addBookByNameToPersonBySurname(String bookName, String personSurname) {
        return personService.addBookByNameToPersonBySurname(bookName, personSurname);
    }
}
