package com.payflow.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class TransactionRepository {

    private final JdbcTemplate jdbcTemplate;

    public TransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveTransaction(int fromUserId, int toUserId,
                                BigDecimal amount, String status) {

        String sql = "INSERT INTO transactions(from_user_id, to_user_id, amount, status) VALUES (?, ?, ?, ?)";

        jdbcTemplate.update(sql, fromUserId, toUserId, amount, status);
    }
}