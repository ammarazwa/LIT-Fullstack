package org.example;

import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.math.BigDecimal;
import java.util.List;
import org.example.trading.model.Stock;

public class Main {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            Stock aapl = new Stock("APPL", "APPLE INC", "Technology", "Consumer Electronics");
            aapl.setCurrentPrice(new BigDecimal("155.00"));
            aapl.setMarketCap(new BigDecimal("300000000000000000"));
            aapl.setPeRatio(28.3);
            aapl.setDividendYield(0.6f);

            session.persist(aapl);
            tx.commit();
            System.out.println("Stock entity persisted successfully!");

            // Tambahan: tampilkan semua data stocks
            System.out.println("--------------------------------------------");
            System.out.println("Current records in 'stocks' table:");
            List<Object[]> stocks = session.createNativeQuery("SELECT symbol, name, sector, industry, current_price, market_cap FROM stocks").getResultList();
            for (Object[] s : stocks) {
                System.out.println("Symbol: " + s[0]);
                System.out.println("Name: " + s[1]);
                System.out.println("Sector: " + s[2]);
                System.out.println("Industry: " + s[3]);
                System.out.println("Current Price: " + s[4]);
                System.out.println("Market Cap: " + s[5]);
                System.out.println();
            }
            System.out.println("--------------------------------------------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
