package ua.iot.kovalyk.mysqlspringlab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairOption;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class RepairOptionDao implements GeneralDao<RepairOption, Integer> {
    private static final String FIND_ALL = "SELECT * FROM kovalyk.repair_options";
    private static final String FIND_BY_ID = "SELECT * FROM kovalyk.repair_options WHERE id=?";
    private static final String CREATE = "INSERT kovalyk.repair_options(name) VALUES (?)";
    private static final String UPDATE = "UPDATE kovalyk.repair_options SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM kovalyk.repair_options WHERE id=?";
    private static final String GET_AVAILABLE_OPTIONS = "CALL select_device_repair_options(?, ?)";
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<RepairOption> getAvailableOptions(Float maxPrice, Integer deviceId) {
        return jdbcTemplate.query(GET_AVAILABLE_OPTIONS, BeanPropertyRowMapper.newInstance(RepairOption.class), maxPrice, deviceId);
    }


    @Override
    public List<RepairOption> getAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(RepairOption.class));
    }

    @Override
    public Optional<RepairOption> getById(Integer id) {
        Optional<RepairOption> repairOptions;
        try {
            repairOptions = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(RepairOption.class), id));
        } catch (EmptyResultDataAccessException e) {
            repairOptions = Optional.empty();
        }
        return repairOptions;
    }

    @Override
    public int create(RepairOption repairOptions) {
        return jdbcTemplate.update(CREATE, repairOptions.getDevice(), repairOptions.getOptionName(), repairOptions.getDevice(), repairOptions.getPrice());
    }

    @Override
    public int update(Integer id, RepairOption repairOptions) {
        return jdbcTemplate.update(UPDATE, repairOptions.getDevice(), repairOptions.getOptionName(), repairOptions.getDevice(), repairOptions.getPrice(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
