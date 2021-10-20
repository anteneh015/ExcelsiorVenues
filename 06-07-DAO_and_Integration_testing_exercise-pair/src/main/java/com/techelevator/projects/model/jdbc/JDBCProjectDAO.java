package com.techelevator.projects.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.projects.model.Project;
import com.techelevator.projects.model.ProjectDAO;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public class JDBCProjectDAO implements ProjectDAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCProjectDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Project> getAllActiveProjects() {
		String sqlGetAllProjects = "SELECT * FROM project";
		List<Project> projects = new ArrayList<Project>();

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllProjects);

		while (results.next()){
			projects.add(mapRowToProject(results));
		}
		return projects;
	}

	private Project mapRowToProject(SqlRowSet row){
		Project project = new Project();

		project.setId(row.getLong("project_id"));
		project.setName(row.getString("name"));

		if (row.getDate("from_date") != null) {
			project.setStartDate(row.getDate("from_date").toLocalDate());
		}
		if (row.getDate("to_date") != null) {
			project.setStartDate(row.getDate("to_date").toLocalDate());
		}
		return project;
	}

	@Override
	public void removeEmployeeFromProject(Long projectId, Long employeeId) {
		
	}

	@Override
	public void addEmployeeToProject(Long projectId, Long employeeId) {
		
	}

}
