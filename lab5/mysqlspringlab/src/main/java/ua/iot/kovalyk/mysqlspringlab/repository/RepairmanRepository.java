package ua.iot.kovalyk.mysqlspringlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.iot.kovalyk.mysqlspringlab.domain.Repairman;

public interface RepairmanRepository extends JpaRepository<Repairman, Integer> {
}
