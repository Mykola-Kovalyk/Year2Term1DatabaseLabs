package com.pavelchak.dao;

import com.pavelchak.domain.Book;
import com.pavelchak.domain.Person;

import java.util.List;

public interface PersonDao extends GeneralDao<Person, Integer> {
    List<Book> findAllBooksBy(Integer id);

    String addBookByNameToPersonBySurname(String bookName, String personSurname);
}
