package com.pavelchak.controller.impl;

import com.pavelchak.controller.BookController;
import com.pavelchak.domain.Book;
import com.pavelchak.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookControllerImpl implements BookController {
    @Autowired
    private BookService bookService;

    @Override
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return bookService.findById(id);
    }

    @Override
    public int create(Book book) {
        return bookService.create(book);
    }

    @Override
    public int update(Integer id, Book book) {
        return bookService.update(id, book);
    }

    @Override
    public int delete(Integer id) {
        return bookService.delete(id);
    }

    @Override
    public Optional<Book> findByBookName(String bookName) {
        return bookService.findByBookName(bookName);
    }

    @Override
    public Optional<Book> findByAuthor(String author) {
        return bookService.findByAuthor(author);
    }
}
