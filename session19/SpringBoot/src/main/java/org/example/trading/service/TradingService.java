package org.example.trading.service;

import org.example.trading.dto.CreateAccountRequest;
import org.example.trading.exception.AccountNotFoundException;
import org.example.trading.model.Stock;
import org.example.trading.model.StockPrice;
import org.example.trading.model.TradingAccount;
import org.example.trading.repository.TradingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TradingService {

    @Autowired
    private TradingAccountRepository accountRepository;

    public List<TradingAccount> getAllAccounts() {
        return accountRepository.findAll();
    }

    public TradingAccount getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + id));
    }

    public TradingAccount createAccount(CreateAccountRequest request) {
        TradingAccount account = TradingAccount.builder()
                .accountName(request.getAccountName())
                .accountType(request.getAccountType())
                .balance(request.getInitialDeposit())
                .status("ACTIVE")
                .build();
        return accountRepository.save(account);
    }

    public TradingAccount updateAccount(Long id, CreateAccountRequest request) {
        TradingAccount existing = getAccountById(id);
        existing.setAccountName(request.getAccountName());
        existing.setAccountType(request.getAccountType());
        if (request.getInitialDeposit() != null) {
            existing.setBalance(request.getInitialDeposit());
        }
        return accountRepository.save(existing);
    }

    public void closeAccount(Long id) {
        TradingAccount account = getAccountById(id);
        account.setStatus("CLOSED");
        accountRepository.save(account);
    }

    public StockPrice getCurrentPrice(String symbol) {
        // Mock price for demo
        double price = 100 + new Random().nextDouble() * 50.0;
        return StockPrice.builder().symbol(symbol.toUpperCase()).price(price).currency("USD").build();
    }

    public List<Stock> searchStocks(String query) {
        // Mock search results
        return List.of(
                Stock.builder().symbol(query.toUpperCase()).name(query + " Corp").exchange("NASDAQ").build(),
                Stock.builder().symbol(query.toUpperCase() + "A").name(query + " A Ltd").exchange("NYSE").build()
        );
    }
}
