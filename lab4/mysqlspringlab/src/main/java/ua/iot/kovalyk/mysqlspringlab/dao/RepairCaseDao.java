package ua.iot.kovalyk.mysqlspringlab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairCase;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairCase;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class RepairCaseDao implements GeneralDao<RepairCase, Integer> {
    private static final String FIND_ALL = "SELECT * FROM kovalyk.repair_cases";
    private static final String FIND_BY_ID = "SELECT * FROM kovalyk.repair_cases WHERE id=?";
    private static final String CREATE = "INSERT kovalyk.repair_cases(repair_option, repairman, opened, closed, failed) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE kovalyk.repair_cases SET repair_option=?, repairman=?, opened=?, closed=?, failed=? WHERE id=?";
    private static final String DELETE = "DELETE FROM kovalyk.repair_cases WHERE id=?";
    private static final String GET_CASES_OF_REPAIRMAN = "SELECT * FROM kovalyk.repair_cases WHERE repairman=?";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<RepairCase> getAllCasesForRepairman(Integer repairmanId) {
        return jdbcTemplate.query(GET_CASES_OF_REPAIRMAN, BeanPropertyRowMapper.newInstance(RepairCase.class), repairmanId);
    }

    @Override
    public List<RepairCase> getAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(RepairCase.class));
    }

    @Override
    public Optional<RepairCase> getById(Integer id) {
        Optional<RepairCase> repairCase;
        try {
            repairCase = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(RepairCase.class), id));
        } catch (EmptyResultDataAccessException e) {
            repairCase = Optional.empty();
        }
        return repairCase;
    }

    @Override
    public int create(RepairCase repairCase) {
        return jdbcTemplate.update(CREATE, repairCase.getRepairOption(), repairCase.getRepairman(), repairCase.getOpened(), repairCase.getClosed(), repairCase.getFailed());
    }

    @Override
    public int update(Integer id, RepairCase repairCase) {
        return jdbcTemplate.update(UPDATE, repairCase.getRepairOption(), repairCase.getRepairman(), repairCase.getOpened(), repairCase.getClosed(), repairCase.getFailed(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
