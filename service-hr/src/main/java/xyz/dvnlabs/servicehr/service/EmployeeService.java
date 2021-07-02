package xyz.dvnlabs.servicehr.service;

import xyz.dvnlabs.corelibrary.base.BaseService;
import xyz.dvnlabs.servicehr.dto.EmployeeUser;
import xyz.dvnlabs.servicehr.entity.Employee;

public interface EmployeeService extends BaseService<Employee, String> {

    EmployeeUser getEmployeeUser(String id);
}
