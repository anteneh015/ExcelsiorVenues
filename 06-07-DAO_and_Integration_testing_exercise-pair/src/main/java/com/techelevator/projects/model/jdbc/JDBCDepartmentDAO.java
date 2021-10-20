package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

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

		while(results.next()){
			departments.add(mapRowToDepartment(results));
		}
		return departments;
	}

	private Department mapRowToDepartment(SqlRowSet row){
		Department department = new Department();

		department.setId(row.getLong("department_id"));
		department.setName(row.getString("name"));
	return department;
	}

	@Override
	public List<Department> searchDepartmentsByName(String nameSearch) {
		return new ArrayList<>();
	}

	@Override
	public void saveDepartment(Department updatedDepartment) {
		
	}

	@Override
	public Department createDepartment(Department newDepartment) {
		return null;
	}

	@Override
	public Department getDepartmentById(Long id) {
		return null;
	}

}
