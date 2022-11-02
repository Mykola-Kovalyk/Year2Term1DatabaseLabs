package ua.iot.kovalyk.mysqlspringlab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.domain.Repairman;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairmanByCompetenceQuery;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairmanStatsQuery;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class RepairmanDao implements GeneralDao<Repairman, Integer> {
    private static final String FIND_ALL = "SELECT * FROM kovalyk.repairmen";
    private static final String FIND_BY_ID = "SELECT * FROM kovalyk.repairmen WHERE id=?";
    private static final String CREATE = "INSERT kovalyk.repairmen(name) VALUES (?)";
    private static final String UPDATE = "UPDATE kovalyk.repairmen SET name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM kovalyk.repairmen WHERE id=?";
    private static final String GET_FREE_REPAIRMEN_AT_TIME = "CALL select_free_repairmen(?, ?)";
    private static final String GET_REPAIRMEN_BY_COMPETENCE= "CALL select_repairman_by_competence(?, ?)";

    private static final String GET_REPAIRMAN_STATS = "CALL select_repairman_stats()";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Integer> getAllFreeRepairmenAsOfTime(String day, Time time) {
        return jdbcTemplate.query(GET_FREE_REPAIRMEN_AT_TIME, BeanPropertyRowMapper.newInstance(Integer.class), time, day);
    }

    public List<RepairmanByCompetenceQuery> getRepairmenSortedByCompetence(Integer deviceId, Integer minimumRepairedAmount) {
        return jdbcTemplate.query(GET_REPAIRMEN_BY_COMPETENCE, BeanPropertyRowMapper.newInstance(RepairmanByCompetenceQuery.class), deviceId, minimumRepairedAmount);
    }

    public List<RepairmanStatsQuery> getRepairmanStats() {
        return jdbcTemplate.query(GET_REPAIRMAN_STATS, BeanPropertyRowMapper.newInstance(RepairmanStatsQuery.class));
    }

    @Override
    public List<Repairman> getAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Repairman.class));
    }

    @Override
    public Optional<Repairman> getById(Integer id) {
        Optional<Repairman> repairman;
        try {
            repairman = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Repairman.class), id));
        } catch (EmptyResultDataAccessException e) {
            repairman = Optional.empty();
        }
        return repairman;
    }

    @Override
    public int create(Repairman repairman) {
        return jdbcTemplate.update(CREATE, repairman.getName());
    }

    @Override
    public int update(Integer id, Repairman repairman) {
        return jdbcTemplate.update(UPDATE, repairman.getName(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
