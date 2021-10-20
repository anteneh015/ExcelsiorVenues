package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.projects.model.Department;
import com.techelevator.projects.model.DepartmentDAO;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCDepartmentDAO implements DepartmentDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCDepartmentDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Department> getAllDepartments() {
		String sqlGetAllDept = "SELECT * FROM department";
		List<Department> departments = new ArrayList<Department>();


		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllDept);

		while (results.next()) {
			departments.add(mapRowToDepartment(results));
		}
		return departments;
	}

	private Department mapRowToDepartment(SqlRowSet row) {
		Department department = new Department();

		department.setId(row.getLong("department_id"));
		department.setName(row.getString("name"));
		return department;
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) {

		List<Department> departments = new ArrayList<Department>();
		String sqlDepartmentSearch = "SELECT department_id, name FROM department WHERE name ILIKE ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlDepartmentSearch, nameSearch.toLowerCase(Locale.ROOT) + "%");

		while (rows.next()) {
			departments.add(mapRowToDepartment(rows));
		}

		return departments;
	}

	@Override
	public Department createDepartment(Department newDepartment) {
		String sqlCreateDepartment = "INSERT INTO department (department_id, name) " + "VALUES (DEFAULT, ?) RETURNING department_id";

		Long department_id = jdbcTemplate.queryForObject(sqlCreateDepartment, Long.class, newDepartment.getName());

		newDepartment.setId(department_id);
		return newDepartment;
	}
	@Override
	public void saveDepartment(Department updatedDepartment) {
		String sqlUpdateDepartment = "UPDATE department SET name = ? WHERE department_id = ?";
		jdbcTemplate.update(sqlUpdateDepartment, updatedDepartment.getName(), updatedDepartment.getId());
	}
	@Override
	public Department getDepartmentById(Long id) {
		Department department = null;
		String sqlGetDepartmentId = "SELECT department_id, name FROM department WHERE department_id = ?";
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlGetDepartmentId, id);

		if(rows.next()) {
			department = mapRowToDepartment(rows);
		}

		return department;
	}

}
