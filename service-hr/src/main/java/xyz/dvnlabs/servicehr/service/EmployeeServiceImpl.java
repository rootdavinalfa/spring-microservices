package xyz.dvnlabs.servicehr.service;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import xyz.dvnlabs.corelibrary.core.CommonHelper;
import xyz.dvnlabs.corelibrary.exception.ResourceExistException;
import xyz.dvnlabs.servicehr.dto.EmployeeUser;
import xyz.dvnlabs.servicehr.dto.User;
import xyz.dvnlabs.servicehr.entity.Employee;
import xyz.dvnlabs.servicehr.repository.EmployeeRepository;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeServiceImpl implements EmployeeService {

    private final RestTemplate restTemplate;

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(RestTemplate restTemplate, EmployeeRepository employeeRepository) {
        this.restTemplate = restTemplate;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findById(String s) {
        return employeeRepository.findById(s)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found!"));
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee create(Employee entity) throws ResourceExistException {

        String maxId = employeeRepository
                .maxId_WithQuery(CommonHelper.getYear(),CommonHelper.getMonth())
                .orElse("YYMM0000");

        maxId = CommonHelper
                .getStringSeq(maxId, CommonHelper.getCurrentDate(), "", "", 4, false, "YYMM");

        entity.setCreatedOn(CommonHelper.getCurrentDate());
        entity.setId(maxId);

        if (employeeRepository.existsById(entity.getId())) {
            throw new ResourceExistException("Employee exist");
        }

        return employeeRepository.save(entity);
    }

    @Override
    public Employee update(Employee entity) {
        return null;
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public EmployeeUser getEmployeeUser(String id) {
        Employee employee = findById(id);
        User user = restTemplate
                .getForObject("http://SERVICE-USER/user/" + employee.getUserName(), User.class);

        return new EmployeeUser(
                employee.getId(),
                employee.getUserName(),
                employee.getDivision(),
                employee.getCreatedOn(),
                user
        );
    }
}
