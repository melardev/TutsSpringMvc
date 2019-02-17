package com.melardev.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.melardev.models.User;

@Repository
public class UserDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {

		//jdbcTemplate.update(preparedStatement, user.getFirstName(),
		//	user.getLastName(), user.getUsername(),)
	}

	public List<User> getUsers() {
		return null;
		/*
		 * jdbcTemplate.query(sql, new RowMapper<User>() {
		 * 
		 * public User mapRow(ResultSet rs, int rowNum) throws SQLException { User user
		 * = new User(); user.setFirstName(rs.getString("first_name")); return null; }
		 * 
		 * });
		 */
	}

	public User getUserById() {
		/*
		 * return jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
		 * 
		 * public User mapRow(ResultSet rs, int rowNum) throws SQLException { User user
		 * = new User(); user.setFirstName(rs.getString("first_name")); return user; }
		 * 
		 * });
		 */
		return null;
	}

	public void deleteUser(User user) {
		String sql = "DELETE FROM users WHERE id=?";
		jdbcTemplate.update(sql, user.getId());
	}

}
