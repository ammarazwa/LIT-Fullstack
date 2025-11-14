package org.example.trading.repository;

import org.example.trading.model.Trade;
import org.example.trading.model.TradeStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {

    // Find all trades by portfolio ID with pagination
    Page<Trade> findByPortfolioId(Long portfolioId, Pageable pageable);

    // Find trades by stock symbol, ordered by creation date in descending order
    List<Trade> findBySymbolOrderByCreatedAtDesc(String symbol);

    // Find recent trades by status and creation date
    @Query("SELECT t FROM Trade t WHERE t.status = :status AND t.createdAt >= :since")
    List<Trade> findRecentByStatus(TradeStatus status, LocalDateTime since);

    // Find a trade by order ID
    Optional<Trade> findByOrderId(String orderId);
}
