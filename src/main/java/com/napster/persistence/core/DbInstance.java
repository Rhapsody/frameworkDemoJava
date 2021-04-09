package com.napster.persistence.core;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DbInstance {
	JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public DbInstance(String connectionString, String userName, String password, String driver) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource (connectionString,userName,password);;
		dataSource.setDriverClassName(driver);
		jdbcTemplate.setDataSource(dataSource);
	}

	public void runStoredProcedure(String procedureName, String ... parameters) {
		List<String> joinedParameters = new ArrayList<>();
		for (String parameter : parameters) {
			joinedParameters.add(parameter);
		}
		jdbcTemplate.update(String.format("call %s(%s)", procedureName, String.join(",", joinedParameters)));
	}

	public void runStoredProcedure(String schemaName, String packageName, String procedureName, Map<String, Object> parameters) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName(procedureName)
				.withSchemaName(schemaName)
				.withCatalogName(packageName);
		SqlParameterSource parameterSource = new MapSqlParameterSource(parameters);
		simpleJdbcCall.execute(parameterSource);
	}

	public void execute(String executionString) {
		jdbcTemplate.execute(executionString);
	}

	/*
	Use these for INSERT, UPDATE and DELETE queries.
	 */
	public void update(String query) {
		jdbcTemplate.update(query);
	}

	public void update(String query, Object[] arguments) {
		jdbcTemplate.update(query, arguments);
	}

	/*
	Use these for SELECT queries
	 */
	public <T> T getRecordForSingleValue(Class<T> cls, String query, Object ... parameters) {
		query = String.format(query, parameters);
		return jdbcTemplate.queryForObject(query, cls);
	}

	public <T> List <T> getRecordsForSingleColumn(Class<T> cls, String query, Object ... parameters) {
		query = String.format(query, parameters);
		return jdbcTemplate.queryForList(query, cls);
	}

	public Map<String, Object> getRecordsAsColumnValueMap(String query, Object ... parameters) {
		query = String.format(query, parameters);
		return jdbcTemplate.queryForMap(query);
	}

	public  <T> T getRecordAsObject(Class<T> objectType, String query, Object ... parameters){
		query = String.format(query, parameters);
		return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<T>(objectType));
	}

	public <T> List<T> getRecordsAsObjects(Class<T> objectType, String query, Object ... parameters){
		query = String.format(query, parameters);
		List<T> results = jdbcTemplate.query(query, new BeanPropertyRowMapper<T>(objectType));
		return results == null ? new ArrayList<>() : results;
	}

}
