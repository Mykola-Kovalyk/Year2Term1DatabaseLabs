package ua.iot.kovalyk.mysqlspringlab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.domain.WorkingHours;
import ua.iot.kovalyk.mysqlspringlab.domain.WorkingHours;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class WorkingHourDao implements GeneralDao<WorkingHours, Integer> {
    private static final String FIND_ALL = "SELECT * FROM kovalyk.working_hours";
    private static final String FIND_BY_ID = "SELECT * FROM kovalyk.working_hours WHERE id=?";
    private static final String CREATE = "INSERT kovalyk.working_hours(name) VALUES (?)";
    private static final String UPDATE = "UPDATE kovalyk.working_hours SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM kovalyk.working_hours WHERE id=?";
    private static final String GET_SCHEDULE = "SELECT * FROM kovalyk.working_hours WHERE repairman=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<WorkingHours> getRepairmanSchedule(Integer repairmanId) {
        return jdbcTemplate.query(GET_SCHEDULE, BeanPropertyRowMapper.newInstance(WorkingHours.class), repairmanId);
    }

    @Override
    public List<WorkingHours> getAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(WorkingHours.class));
    }

    @Override
    public Optional<WorkingHours> getById(Integer id) {
        Optional<WorkingHours> workingHours;
        try {
            workingHours = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(WorkingHours.class), id));
        } catch (EmptyResultDataAccessException e) {
            workingHours = Optional.empty();
        }
        return workingHours;
    }

    @Override
    public int create(WorkingHours workingHours) {
        return jdbcTemplate.update(CREATE, workingHours.getRepairman(), workingHours.getDay(), workingHours.getStart(), workingHours.getEnd());
    }

    @Override
    public int update(Integer id, WorkingHours workingHours) {
        return jdbcTemplate.update(UPDATE, workingHours.getRepairman(), workingHours.getDay(), workingHours.getStart(), workingHours.getEnd(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
