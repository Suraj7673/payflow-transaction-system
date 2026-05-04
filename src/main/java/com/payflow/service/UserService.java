package com.payflow.service;

import com.payflow.dto.AddBalanceRequest;
import com.payflow.dto.CreateUserRequest;
import com.payflow.dto.TransferRequest;
import com.payflow.model.User;
import com.payflow.repository.TransactionRepository;
import com.payflow.repository.UserRepository;
import com.payflow.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public UserService(UserRepository userRepository,
                       WalletRepository walletRepository,
                       TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    // ================= GET BALANCE =================
    public BigDecimal getBalance(int userId) {
        return walletRepository.getBalance(userId);
    }

    // ================= CREATE USER =================
    public String createUser(CreateUserRequest request) {

        if (request.getName() == null || request.getEmail() == null) {
            return "Invalid input";
        }

        try {
            // Create User (JPA)
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword("default"); // temporary

            User savedUser = userRepository.save(user);

            int userId = savedUser.getId().intValue();

            // Create wallet (JDBC)
            walletRepository.createWallet(userId);

            return "User + Wallet created successfully";

        } catch (Exception e) {
            return "Email already exists or error occurred";
        }
    }

    // ================= ADD BALANCE =================
    public String addBalance(AddBalanceRequest request) {

        if (request.getAmount() == null ||
                request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return "Invalid amount";
        }

        try {
            BigDecimal currentBalance =
                    walletRepository.getBalance(request.getUserId());

            BigDecimal newBalance =
                    currentBalance.add(request.getAmount());

            walletRepository.updateBalance(
                    request.getUserId(),
                    newBalance
            );

            return "Balance updated successfully";

        } catch (Exception e) {
            return "User not found or error occurred";
        }
    }

    // ================= TRANSFER =================
    @Transactional
    public String transfer(TransferRequest request) {

        if (request.getAmount() == null ||
                request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return "Invalid amount";
        }

        try {
            BigDecimal senderBalance =
                    walletRepository.getBalance(request.getFromUserId());

            // Check balance
            if (senderBalance.compareTo(request.getAmount()) < 0) {

                transactionRepository.saveTransaction(
                        request.getFromUserId(),
                        request.getToUserId(),
                        request.getAmount(),
                        "FAILED"
                );

                return "Insufficient balance";
            }

            // Deduct from sender
            walletRepository.updateBalance(
                    request.getFromUserId(),
                    senderBalance.subtract(request.getAmount())
            );

            // Add to receiver
            BigDecimal receiverBalance =
                    walletRepository.getBalance(request.getToUserId());

            walletRepository.updateBalance(
                    request.getToUserId(),
                    receiverBalance.add(request.getAmount())
            );

            // Save SUCCESS transaction
            transactionRepository.saveTransaction(
                    request.getFromUserId(),
                    request.getToUserId(),
                    request.getAmount(),
                    "SUCCESS"
            );

            return "Transfer successful";

        } catch (Exception e) {

            transactionRepository.saveTransaction(
                    request.getFromUserId(),
                    request.getToUserId(),
                    request.getAmount(),
                    "FAILED"
            );

            throw new RuntimeException("Transfer failed");
        }
    }
}