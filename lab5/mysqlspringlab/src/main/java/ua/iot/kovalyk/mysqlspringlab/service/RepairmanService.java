package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.iot.kovalyk.mysqlspringlab.domain.Repairman;
import ua.iot.kovalyk.mysqlspringlab.exception.ResourceNotFoundException;
import ua.iot.kovalyk.mysqlspringlab.repository.RepairmanRepository;

import javax.transaction.Transactional;

@Service
public class RepairmanService extends GeneralService<Repairman, Integer>{

    @Autowired
    public RepairmanService(RepairmanRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public void update(Integer id, Repairman entity) {
        Repairman device = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        //update
        device.setName(entity.getName());

        repository.save(device);
    }

}
