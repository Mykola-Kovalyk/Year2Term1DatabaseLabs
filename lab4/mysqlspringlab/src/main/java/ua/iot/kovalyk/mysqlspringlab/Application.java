package ua.iot.kovalyk.mysqlspringlab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ua.iot.kovalyk.mysqlspringlab.dao.DeviceDao;
import ua.iot.kovalyk.mysqlspringlab.dao.RepairmanDao;
import ua.iot.kovalyk.mysqlspringlab.domain.Device;
import ua.iot.kovalyk.mysqlspringlab.domain.Repairman;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairmanByCompetenceQuery;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairmanStatsQuery;
import ua.iot.kovalyk.mysqlspringlab.view.ShellInterface;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ShellInterface shellInterface;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        shellInterface.mainLoop();
    }
}
