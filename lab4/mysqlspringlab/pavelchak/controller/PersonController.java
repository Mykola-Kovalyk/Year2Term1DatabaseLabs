package com.pavelchak.controller;

import com.pavelchak.domain.Book;
import com.pavelchak.domain.Person;

import java.util.List;


public interface PersonController extends GeneralController<Person, Integer> {
    List<Book> findAllBooksBy(Integer id);
    String addBookByNameToPersonBySurname(String bookName, String personSurname);
}
