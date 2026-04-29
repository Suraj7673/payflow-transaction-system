package com.payflow.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class WalletRepository {

    private final JdbcTemplate jdbcTemplate;

    public WalletRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createWallet(int userId) {
        String sql = "INSERT INTO wallets(user_id, balance) VALUES (?, 0)";
        jdbcTemplate.update(sql, userId);
    }

    public BigDecimal getBalance(int userId) {
        String sql = "SELECT balance FROM wallets WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);
    }

    public void updateBalance(int userId, BigDecimal newBalance) {
        String sql = "UPDATE wallets SET balance = ? WHERE user_id = ?";
        jdbcTemplate.update(sql, newBalance, userId);
    }
}