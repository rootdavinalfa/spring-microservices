package xyz.dvnlabs.servicehr.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.dvnlabs.corelibrary.base.BaseRepository;
import xyz.dvnlabs.servicehr.entity.Employee;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, String> {

    @Query(value = "SELECT MAX(a.id) FROM Employee a " +
            "WHERE YEAR(a.createdOn) = :year " +
            "AND MONTH(a.createdOn) = :month ")
    Optional<String> maxId_WithQuery(
            @Param("year") Integer year,
            @Param("month") Integer month
    );

}
