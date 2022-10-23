package com.pavelchak.dao.impl;

import com.pavelchak.dao.PersonDao;
import com.pavelchak.domain.Book;
import com.pavelchak.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Service
public class PersonDaoImpl implements PersonDao {
    private static final String FIND_ALL = "SELECT * FROM person";
    private static final String CREATE = "INSERT person(surname, name, city, email) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE person SET surname=?, name=?, city=?, email=? WHERE id=?";
    private static final String DELETE = "DELETE FROM person WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM person WHERE id=?";
    private static final String FIND_ALL_BOOKS = "SELECT * FROM book " +
            "WHERE EXISTS(SELECT * FROM person_book WHERE book_id=id and person_id=?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Person.class));
    }

    @Override
    public Optional<Person> findById(Integer id) {
        Optional<Person> person;
        try {
            person = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Person.class), id));
        } catch (EmptyResultDataAccessException e) {
            person = Optional.empty();
        }
        return person;
    }

    @Override
    public int create(Person person) {
        return jdbcTemplate.update(CREATE, person.getSurname(), person.getName(), person.getCity(), person.getEmail());
    }

    @Override
    public int update(Integer id, Person person) {
        return jdbcTemplate.update(UPDATE, person.getSurname(), person.getName(), person.getCity(), person.getEmail(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }

    @Override
    public List<Book> findAllBooksBy(Integer id) {
        return jdbcTemplate.query(FIND_ALL_BOOKS, BeanPropertyRowMapper.newInstance(Book.class), id);
    }

    @Override
    public String addBookByNameToPersonBySurname(String bookName, String personSurname) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("InsertPersonBook2");
        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("SurnamePersonIn", personSurname);
        inParamMap.put("BookNameIN", bookName);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);

        return simpleJdbcCall.executeObject(String.class, in);
    }
}
