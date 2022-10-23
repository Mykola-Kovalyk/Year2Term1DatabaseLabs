package com.pavelchak.dao.impl;

import com.pavelchak.dao.BookDao;
import com.pavelchak.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class BookDaoImpl implements BookDao {
    private static final String FIND_ALL = "SELECT * FROM book";
    private static final String CREATE = "INSERT book(book_name, author, amount) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE book SET book_name=?, author=?, amount=? WHERE id=?";
    private static final String DELETE = "DELETE FROM book WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM book WHERE id=?";
    private static final String FIND_BY_BOOK_NAME = "SELECT * FROM book WHERE book_name=?";
    private static final String FIND_BY_AUTHOR = "SELECT * FROM book WHERE author=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Book.class));
    }

    @Override
    public Optional<Book> findById(Integer id) {
        Optional<Book> book;
        try {
            book = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Book.class), id));
        } catch (EmptyResultDataAccessException e) {
            book = Optional.empty();
        }
        return book;
    }

    @Override
    public int create(Book book) {
        return jdbcTemplate.update(CREATE, book.getBookName(), book.getAuthor(), book.getAmount());
    }

    @Override
    public int update(Integer id, Book book) {
        return jdbcTemplate.update(UPDATE, book.getBookName(), book.getAuthor(), book.getAmount(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public Optional<Book> findByBookName(String bookName) {
        Optional<Book> book;
        try {
            book = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_BOOK_NAME,
                    BeanPropertyRowMapper.newInstance(Book.class), bookName));
        } catch (EmptyResultDataAccessException e) {
            book = Optional.empty();
        }
        return book;
    }

    @Override
    public Optional<Book> findByAuthor(String author) {
        Optional<Book> book;
        try {
            book = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_AUTHOR,
                    BeanPropertyRowMapper.newInstance(Book.class), author));
        } catch (EmptyResultDataAccessException e) {
            book = Optional.empty();
        }
        return book;
    }
}
