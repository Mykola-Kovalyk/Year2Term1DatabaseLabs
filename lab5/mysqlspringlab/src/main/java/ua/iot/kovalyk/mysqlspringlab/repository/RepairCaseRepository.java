package ua.iot.kovalyk.mysqlspringlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.iot.kovalyk.mysqlspringlab.domain.RepairCase;

public interface RepairCaseRepository extends JpaRepository<RepairCase, Integer> {
}
