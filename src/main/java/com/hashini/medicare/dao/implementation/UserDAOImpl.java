package com.hashini.medicare.dao.implementation;

import com.hashini.medicare.dao.UserDAO;
import com.hashini.medicare.mapper.UserMapper;
import com.hashini.medicare.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ? ";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), username);
    }

    @Override
    public void save(User user) {
        String sql = "UPDATE users SET password = ? WHERE username = ?";
        jdbcTemplate.update(sql, user.getPassword(), user.getUsername());
    }
}