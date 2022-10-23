package com.pavelchak.service.impl;

import com.pavelchak.dao.PersonDao;
import com.pavelchak.domain.Book;
import com.pavelchak.domain.Person;
import com.pavelchak.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public Optional<Person> findById(Integer id) {
        return personDao.findById(id);
    }

    @Override
    public int create(Person person) {
        return personDao.create(person);
    }

    @Override
    public int update(Integer id, Person person) {
        return personDao.update(id, person);
    }

    @Override
    public int delete(Integer id) {
        return personDao.delete(id);
    }

    @Override
    public List<Book> findAllBooksBy(Integer id) {
        return personDao.findAllBooksBy(id);
    }

    @Override
    public String addBookByNameToPersonBySurname(String bookName, String personSurname) {
        return personDao.addBookByNameToPersonBySurname(bookName, personSurname);
    }
}
