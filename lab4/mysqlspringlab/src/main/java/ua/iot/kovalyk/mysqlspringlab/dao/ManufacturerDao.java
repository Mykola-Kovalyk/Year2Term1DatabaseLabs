package ua.iot.kovalyk.mysqlspringlab.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.domain.Manufacturer;
import ua.iot.kovalyk.mysqlspringlab.domain.Manufacturer;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class ManufacturerDao implements GeneralDao<Manufacturer, Integer> {
    private static final String FIND_ALL = "SELECT * FROM kovalyk.manufacturers";
    private static final String FIND_BY_ID = "SELECT * FROM kovalyk.manufacturers WHERE id=?";
    private static final String CREATE = "INSERT kovalyk.manufacturers(name) VALUES (?)";
    private static final String UPDATE = "UPDATE kovalyk.manufacturers SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM kovalyk.manufacturers WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Manufacturer> getAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Manufacturer.class));
    }

    @Override
    public Optional<Manufacturer> getById(Integer id) {
        Optional<Manufacturer> manufacturer;
        try {
            manufacturer = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Manufacturer.class), id));
        } catch (EmptyResultDataAccessException e) {
            manufacturer = Optional.empty();
        }
        return manufacturer;
    }

    @Override
    public int create(Manufacturer manufacturer) {
        return jdbcTemplate.update(CREATE, manufacturer.getName());
    }

    @Override
    public int update(Integer id, Manufacturer manufacturer) {
        return jdbcTemplate.update(UPDATE, manufacturer.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
