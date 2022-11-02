package ua.iot.kovalyk.mysqlspringlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.iot.kovalyk.mysqlspringlab.dao.GeneralDao;

import java.util.List;
import java.util.Optional;

public abstract class GeneralService<Data, ID> {


    protected GeneralDao<Data, ID> generalDao;


    public List<Data> getAll() {
        return generalDao.getAll();
    }

    public Data getById(ID id) {
        Optional<Data> output = generalDao.getById(id);

        if(output.isEmpty())
            return null;
        return output.get();
    }

    public int create(Data repairman) {
        return generalDao.create(repairman);
    }

    public int update(ID id, Data repairman) {
        return generalDao.update(id, repairman);
    }

    public int delete(ID id) {
        return generalDao.delete(id);
    }
}
