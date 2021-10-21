package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import com.techelevator.projects.model.Department;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.projects.model.Employee;
import com.techelevator.projects.model.EmployeeDAO;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCEmployeeDAO implements EmployeeDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCEmployeeDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Employee> getAllEmployees() {
		String sqlGetAllEmployees = "SELECT * FROM employee";
		List<Employee> employees = new ArrayList<Employee>();

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllEmployees);

		while (results.next()) {
			employees.add(mapRowToEmployee(results));

		}

		return employees;
	}

	private Employee mapRowToEmployee(SqlRowSet row) {
		Employee employee = new Employee();

		employee.setId(row.getLong("employee_id"));
		employee.setDepartmentId(row.getLong("department_id"));
		employee.setFirstName(row.getString("first_name"));
		employee.setLastName(row.getString("last_name"));
		employee.setBirthDay(row.getDate("birth_date").toLocalDate());
		employee.setHireDate(row.getDate("hire_date").toLocalDate());

		return employee;
	}

	@Override
	public List<Employee> searchEmployeesByName(String firstNameSearch, String lastNameSearch) {
		List<Employee> employees = new ArrayList<Employee>();
		String sqlEmployeeSearch = "SELECT employee_id, department_id, first_name, last_name, birth_date, hire_date " +
				"FROM employee WHERE first_name ILIKE ? AND last_name ILIKE ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlEmployeeSearch, "%" + firstNameSearch.toLowerCase(Locale.ROOT) + "%", "%" + lastNameSearch.toLowerCase(Locale.ROOT) + "%");

		while (rows.next()) {
			employees.add(mapRowToEmployee(rows));
		}

		return employees;

	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(long id) {

	List<Employee> employeeByDepartmentId = new ArrayList<Employee>();
	String sqlGetEmployeeByDepartmentId = "SELECT employee_id, department_id, first_name, last_name, birth_date, hire_date " +
			"FROM employee WHERE employee_id = ? AND department_id = ?";
	SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlGetEmployeeByDepartmentId, id);

		while (rows.next()) {

			employeeByDepartmentId.add(mapRowToEmployee(rows));
		}

		return employeeByDepartmentId;
}
	@Override
	public List<Employee> getEmployeesWithoutProjects() {

		List<Employee> getEmployeesWithoutProject = new ArrayList<Employee>();
		String sqlGetEmployeesWithoutProject = "SELECT project_id, employee_id FROM project_employee JOIN employee ON first_name = employee.first_name";
		SqlRowSet results = jdbcTemplate.queryForRowSet((sqlGetEmployeesWithoutProject));

		while (results.next()) {
			getEmployeesWithoutProject.add(mapRowToEmployee(results));

		}

		return getEmployeesWithoutProject;
	}

	@Override
	public List<Employee> getEmployeesByProjectId(Long projectId) {
		return new ArrayList<>();
	}

	@Override
	public void changeEmployeeDepartment(Long employeeId, Long departmentId) {
		
	}

}
