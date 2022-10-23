package com.pavelchak.service.impl;

import com.pavelchak.dao.BookDao;
import com.pavelchak.domain.Book;
import com.pavelchak.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Optional<Book> findById(Integer id) {
        return bookDao.findById(id);
    }

    @Override
    public int create(Book book) {
        return bookDao.create(book);
    }

    @Override
    public int update(Integer id, Book book) {
        return bookDao.update(id, book);
    }

    @Override
    public int delete(Integer id) {
        return bookDao.delete(id);
    }

    @Override
    public Optional<Book> findByBookName(String bookName) {
        return bookDao.findByBookName(bookName);
    }

    @Override
    public Optional<Book> findByAuthor(String author) {
        return bookDao.findByAuthor(author);
    }
}
