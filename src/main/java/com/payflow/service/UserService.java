package com.payflow.service;

import com.payflow.dto.AddBalanceRequest;
import com.payflow.dto.CreateUserRequest;
import com.payflow.dto.TransferRequest;
import com.payflow.model.Transaction;
import com.payflow.model.User;
import com.payflow.model.Wallet;
import com.payflow.repository.TransactionRepository;
import com.payflow.repository.UserRepository;
import com.payflow.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

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

    // 🔐 Get balance
    public BigDecimal getMyBalance(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        return wallet.getBalance();
    }

    // ✅ Create user + wallet
    public String createUser(CreateUserRequest request) {

        if (request.getEmail() == null) return "Invalid input";

        try {
            User user = new User();
            user.setEmail(request.getEmail());
            user.setPassword("default");

            User savedUser = userRepository.save(user);

            Wallet wallet = new Wallet(savedUser);
            walletRepository.save(wallet);

            return "User + Wallet created successfully";

        } catch (Exception e) {
            return "Email already exists or error occurred";
        }
    }

    // 💰 Add balance
    public String addBalance(AddBalanceRequest request) {

        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return "Invalid amount";
        }

        try {
            User user = userRepository.findById((long) request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Wallet wallet = walletRepository.findByUser(user)
                    .orElseThrow(() -> new RuntimeException("Wallet not found"));

            wallet.setBalance(wallet.getBalance().add(request.getAmount()));
            walletRepository.save(wallet);

            return "Balance updated successfully";

        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // 🔥 Transfer
    @Transactional
    public String transfer(TransferRequest request, String senderEmail) {

        if (request.getAmount() == null || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return "Invalid amount";
        }

        try {
            User sender = userRepository.findByEmail(senderEmail)
                    .orElseThrow(() -> new RuntimeException("Sender not found"));

            Wallet senderWallet = walletRepository.findByUser(sender)
                    .orElseThrow(() -> new RuntimeException("Sender wallet not found"));

            User receiver = userRepository.findById((long) request.getToUserId())
                    .orElseThrow(() -> new RuntimeException("Receiver not found"));

            Wallet receiverWallet = walletRepository.findByUser(receiver)
                    .orElseThrow(() -> new RuntimeException("Receiver wallet not found"));

            if (senderWallet.getBalance().compareTo(request.getAmount()) < 0) {

                transactionRepository.save(
                        new Transaction(sender.getId(), receiver.getId(), request.getAmount(), "FAILED")
                );

                return "Insufficient balance";
            }

            senderWallet.setBalance(senderWallet.getBalance().subtract(request.getAmount()));
            receiverWallet.setBalance(receiverWallet.getBalance().add(request.getAmount()));

            walletRepository.save(senderWallet);
            walletRepository.save(receiverWallet);

            transactionRepository.save(
                    new Transaction(sender.getId(), receiver.getId(), request.getAmount(), "SUCCESS")
            );

            return "Transfer successful";

        } catch (Exception e) {
            throw new RuntimeException("System error: " + e.getMessage());
        }
    }

    // 📜 Transaction history
    public List<Transaction> getMyTransactions(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return transactionRepository
                .findByFromUserIdOrToUserIdOrderByCreatedAtDesc(
                        user.getId(),
                        user.getId()
                );
    }
}