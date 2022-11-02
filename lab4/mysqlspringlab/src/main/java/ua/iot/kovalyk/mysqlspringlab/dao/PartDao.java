package ua.iot.kovalyk.mysqlspringlab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.domain.Part;
import ua.iot.kovalyk.mysqlspringlab.domain.Part;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class PartDao implements GeneralDao<Part, Integer> {
    private static final String FIND_ALL = "SELECT * FROM kovalyk.parts";
    private static final String FIND_BY_ID = "SELECT * FROM kovalyk.parts WHERE id=?";
    private static final String CREATE = "INSERT kovalyk.parts(part_number, device, manufacturer, amount) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE kovalyk.parts SET part_number=?, device=?, manufacturer=?, amount=? WHERE id=?";
    private static final String DELETE = "DELETE FROM kovalyk.parts WHERE id=?";
    private static final String GET_PARTS_BY_DEVICE = "DELETE FROM kovalyk.parts WHERE device=?";
    private static final String GET_PARTS_FOR_REPLENISHMENT = "SELECT * FROM kovalyk.parts WHERE amount = 0;";


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Part> getAllPartsOfDevice(Integer deviceId) {
        return jdbcTemplate.query(GET_PARTS_BY_DEVICE, BeanPropertyRowMapper.newInstance(Part.class), deviceId);
    }

    public List<Part> getPartsThatNeedReplenishment() {
        return jdbcTemplate.query(GET_PARTS_FOR_REPLENISHMENT, BeanPropertyRowMapper.newInstance(Part.class));
    }

    @Override
    public List<Part> getAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Part.class));
    }

    @Override
    public Optional<Part> getById(Integer id) {
        Optional<Part> part;
        try {
            part = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Part.class), id));
        } catch (EmptyResultDataAccessException e) {
            part = Optional.empty();
        }
        return part;
    }

    @Override
    public int create(Part part) {
        return jdbcTemplate.update(CREATE, part.getPartNumber(), part.getDevice(), part.getManufacturer(), part.getAmount());
    }

    @Override
    public int update(Integer id, Part part) {
        return jdbcTemplate.update(UPDATE, part.getPartNumber(), part.getDevice(), part.getManufacturer(), part.getAmount(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
