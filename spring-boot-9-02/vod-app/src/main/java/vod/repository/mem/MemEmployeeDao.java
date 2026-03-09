package vod.repository.mem;

import org.springframework.stereotype.Repository;
import vod.model.Employee;
import vod.repository.EmployeeDao;

import java.util.List;

@Repository("employeeDao")
public class MemEmployeeDao implements EmployeeDao {
    @Override
    public List<Employee> findAll() {
        return SampleData.employees;
    }

    @Override
    public Employee findById(int id) {
        return SampleData.employees.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Employee add(Employee employee) {
        int max = SampleData.employees.stream()
                .max((e1, e2) -> e1.getId() - e2.getId())
                .get()
                .getId();
        employee.setId(++max);
        SampleData.employees.add(employee);
        return employee;
    }
}
