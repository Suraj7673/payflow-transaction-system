package com.payflow.dto;

import java.math.BigDecimal;

public class TransferRequest {

    private int toUserId;
    private BigDecimal amount;

    // Getters and Setters
    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}