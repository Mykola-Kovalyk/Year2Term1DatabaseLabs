package ua.iot.kovalyk.mysqlspringlab.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.iot.kovalyk.mysqlspringlab.domain.Device;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SqlResolve")
@Component
public class DeviceDao implements GeneralDao<Device, Integer> {
    private static final String FIND_ALL = "SELECT * FROM kovalyk.devices";
    private static final String FIND_BY_ID = "SELECT * FROM kovalyk.devices WHERE id=?";
    private static final String CREATE = "INSERT kovalyk.devices(manufacturer, serial_number) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE kovalyk.devices SET manufacturer=?, serial_number=? WHERE id=?";
    private static final String DELETE = "DELETE FROM kovalyk.devices WHERE id=?";

    private static final String GET_DEVICES_THAT_CAN_BE_REPAIRED = "SELECT * FROM kovalyk.devices WHERE (SELECT COUNT(*) FROM kovalyk.parts WHERE kovalyk.parts.device = kovalyk.devices.id) > 0";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Device> getDevicesThatCanBeRepaired() {
        return jdbcTemplate.query(GET_DEVICES_THAT_CAN_BE_REPAIRED, BeanPropertyRowMapper.newInstance(Device.class));
    }

    @Override
    public List<Device> getAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Device.class));
    }

    @Override
    public Optional<Device> getById(Integer id) {
        Optional<Device> device;
        try {
            device = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Device.class), id));
        } catch (EmptyResultDataAccessException e) {
            device = Optional.empty();
        }
        return device;
    }

    @Override
    public int create(Device device) {
        return jdbcTemplate.update(CREATE, device.getManufacturer(), device.getSerialNumber());
    }

    @Override
    public int update(Integer id, Device device) {
        return jdbcTemplate.update(UPDATE, device.getManufacturer(), device.getSerialNumber(), id);
    }

    @Override
    public int delete(Integer id) {
        return jdbcTemplate.update(DELETE, id);
    }
}
