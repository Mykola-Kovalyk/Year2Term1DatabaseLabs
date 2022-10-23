package com.pavelchak.service;

import com.pavelchak.domain.Book;
import com.pavelchak.domain.Person;

import java.util.List;


public interface PersonService extends GeneralService<Person, Integer> {
    List<Book> findAllBooksBy(Integer id);
    String addBookByNameToPersonBySurname(String bookName, String personSurname);
}
