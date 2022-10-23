package com.pavelchak.controller;

import com.pavelchak.domain.Book;

import java.util.Optional;

public interface BookController extends GeneralController<Book, Integer> {
    Optional<Book> findByBookName(String bookName);

    Optional<Book> findByAuthor(String author);
}
