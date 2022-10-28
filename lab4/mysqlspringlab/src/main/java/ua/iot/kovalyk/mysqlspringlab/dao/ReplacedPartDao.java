package ua.iot.kovalyk.mysqlspringlab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.domain.ReplacedPart;
import ua.iot.kovalyk.mysqlspringlab.domain.ReplacedPart;
import ua.iot.kovalyk.mysqlspringlab.domain.ReplacedPartsQuery;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class ReplacedPartDao implements GeneralDao<ReplacedPart, Integer> {
    private static final String FIND_ALL = "SELECT * FROM kovalyk.replaced_parts";
    private static final String FIND_BY_ID = "SELECT * FROM kovalyk.replaced_parts WHERE id=?";
    private static final String CREATE = "INSERT kovalyk.replaced_parts(repair_case, replaced) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE kovalyk.replaced_parts SET repair_case=?, replaced=? WHERE id=?";
    private static final String DELETE = "DELETE FROM kovalyk.replaced_parts WHERE id=?";
    private static final String GET_REPLACED_PARTS = "CALL total_replaced_parts_throughout_period(?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ReplacedPartsQuery> getReplacedPartsThroughoutPeriod(Timestamp start, Timestamp end) {
        return jdbcTemplate.query(GET_REPLACED_PARTS, BeanPropertyRowMapper.newInstance(ReplacedPartsQuery.class), start, end);
    }

    @Override
    public List<ReplacedPart> getAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(ReplacedPart.class));
    }

    @Override
    public Optional<ReplacedPart> getById(Integer id) {
        Optional<ReplacedPart> replacedPart;
        try {
            replacedPart = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(ReplacedPart.class), id));
        } catch (EmptyResultDataAccessException e) {
            replacedPart = Optional.empty();
        }
        return replacedPart;
    }

    @Override
    public int create(ReplacedPart replacedPart) {
        return jdbcTemplate.update(CREATE, replacedPart.getRepairCase(), replacedPart.getReplacedPart());
    }

    @Override
    public int update(Integer id, ReplacedPart replacedPart) {
        return jdbcTemplate.update(UPDATE, replacedPart.getRepairCase(), replacedPart.getReplacedPart(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
