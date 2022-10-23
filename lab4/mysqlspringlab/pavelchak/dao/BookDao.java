package com.pavelchak.dao;

import com.pavelchak.domain.Book;

import java.util.Optional;

public interface BookDao extends GeneralDao<Book, Integer> {
    Optional<Book> findByBookName(String bookName);

    Optional<Book> findByAuthor(String author);
}
