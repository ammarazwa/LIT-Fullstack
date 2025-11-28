"use strict";
// ---------- Custom Errors ----------
class InvalidStockError extends Error {
    constructor(message) {
        super(message);
        this.name = "InvalidStockError";
    }
}
class InsufficientFundsError extends Error {
    constructor(message) {
        super(message);
        this.name = "InsufficientFundsError";
    }
}
class InsufficientHoldingsError extends Error {
    constructor(message) {
        super(message);
        this.name = "InsufficientHoldingsError";
    }
}
function Ok(value) {
    return { ok: true, data: value };
}
function Err(error) {
    return { ok: false, error };
}
function isPending(t) {
    return t.state === "pending";
}
function isCompleted(t) {
    return t.state === "completed";
}
function isFailed(t) {
    return t.state === "failed";
}
// ---------- Portfolio Class ----------
class Portfolio {
    owner;
    stocks = [];
    constructor(owner, stocks = []) {
        this.owner = owner;
        this.stocks = stocks;
    }
    getTotalValue() {
        try {
            const value = this.stocks.reduce((sum, stock) => sum + stock.quantity * stock.purchasePrice, 0);
            return { ok: true, data: value };
        }
        catch (err) {
            return { ok: false, error: err };
        }
    }
    addStock(stock) {
        this.stocks.push(stock);
        return { ok: true, data: stock };
    }
    removeStock(symbol) {
        const exists = this.stocks.some((s) => s.symbol === symbol);
        if (!exists) {
            return {
                ok: false,
                error: new InvalidStockError(`Stock ${symbol} not found in portfolio`),
            };
        }
        this.stocks = this.stocks.filter((s) => s.symbol !== symbol);
        return { ok: true, data: symbol };
    }
    updateStock(symbol, updates) {
        const stock = this.stocks.find((s) => s.symbol === symbol);
        if (!stock) {
            return {
                ok: false,
                error: new InvalidStockError(`Cannot update: ${symbol} not found`),
            };
        }
        if (updates.quantity !== undefined && updates.quantity < 0) {
            return {
                ok: false,
                error: new InsufficientFundsError("Quantity cannot be negative"),
            };
        }
        Object.assign(stock, updates);
        return { ok: true, data: stock };
    }
    getStockSummary() {
        return this.stocks.map(({ symbol, quantity }) => ({ symbol, quantity }));
    }
    async updatePrice(symbol) {
        const priceResult = await fetchStockPrice(symbol);
        if (!priceResult.ok)
            return priceResult;
        const stock = this.stocks.find((s) => s.symbol === symbol);
        if (!stock) {
            return {
                ok: false,
                error: new InvalidStockError(`Stock not found: ${symbol}`),
            };
        }
        stock.purchasePrice = priceResult.data;
        return { ok: true, data: stock.purchasePrice };
    }
}
// ---------- Async fetchStockPrice ----------
async function fetchStockPrice(symbol) {
    return new Promise((resolve) => {
        setTimeout(() => {
            const mockPrices = {
                AAPL: 155,
                GOOG: 2810,
                TSLA: 720,
                NFLX: 505,
                AMZN: 3310,
                NVDA: 920,
            };
            const price = mockPrices[symbol];
            if (!price) {
                resolve({
                    ok: false,
                    error: new InvalidStockError(`Price not found for: ${symbol}`),
                });
            }
            else {
                resolve({ ok: true, data: price });
            }
        }, 500);
    });
}
// ---------- TransactionHandler Class ----------
class TransactionHandler {
    portfolio;
    transactions = [];
    constructor(portfolio) {
        this.portfolio = portfolio;
    }
    createTransaction(stockSymbol, quantity, price) {
        const transaction = {
            id: cryptoRandomId(),
            stockSymbol,
            quantity,
            price,
            state: "pending",
            timestamp: new Date(),
        };
        this.transactions.push(transaction);
        return transaction;
    }
    completeTransaction(transactionId) {
        const tx = this.transactions.find((t) => t.id === transactionId);
        if (!tx)
            return Err(new InvalidStockError("Transaction not found"));
        if (tx.state !== "pending")
            return Err(new InvalidStockError("Transaction already processed"));
        tx.state = "completed";
        return Ok(tx);
    }
    failTransaction(transactionId, reason) {
        const tx = this.transactions.find((t) => t.id === transactionId);
        if (!tx)
            return Err(new InvalidStockError("Transaction not found"));
        if (tx.state !== "pending")
            return Err(new InvalidStockError("Transaction already processed"));
        tx.state = "failed";
        tx.reason = reason;
        return Ok(tx);
    }
    getTransactions() {
        return this.transactions;
    }
}
// ---------- Utility Function ----------
function cryptoRandomId(len = 12) {
    if (typeof crypto !== "undefined" && "getRandomValues" in crypto) {
        const arr = new Uint8Array(len);
        crypto.getRandomValues(arr);
        return Array.from(arr, (b) => b.toString(16).padStart(2, "0")).join("").slice(0, len);
    }
    return Math.random().toString(36).slice(2, 2 + len);
}
// ---------- Demo ----------
(async () => {
    const sampleStocks = [
        { symbol: "AAPL", name: "Apple", quantity: 10, purchasePrice: 150 },
        { symbol: "GOOG", name: "Google", quantity: 5, purchasePrice: 2800 },
        { symbol: "TSLA", name: "Tesla", quantity: 3, purchasePrice: 700 },
        { symbol: "NFLX", name: "Netflix", quantity: 4, purchasePrice: 500 },
        { symbol: "AMZN", name: "Amazon", quantity: 2, purchasePrice: 3300 },
    ];
    const testPortfolio = new Portfolio("Test User", sampleStocks);
    const transactionHandler = new TransactionHandler(testPortfolio);
    // Create new transaction (buy)
    const newTransaction = transactionHandler.createTransaction("NVDA", 1, 900);
    console.log("Created transaction:", newTransaction);
    // Complete the transaction
    const completeResult = transactionHandler.completeTransaction(newTransaction.id);
    console.log("Complete transaction:", completeResult);
    // Fail a transaction
    const failResult = transactionHandler.failTransaction(newTransaction.id, "Insufficient funds");
    console.log("Fail transaction:", failResult);
    // Show all transactions
    console.log("\nAll transactions:", transactionHandler.getTransactions());
    // Get portfolio value
    const total = testPortfolio.getTotalValue();
    console.log("Total portfolio value:", total);
    // Update stock price
    const priceUpdate = await testPortfolio.updatePrice("AAPL");
    console.log("Updated AAPL price:", priceUpdate);
    // Final stock summary
    console.log("Stock Summary:", testPortfolio.getStockSummary());
})();
//# sourceMappingURL=tugas-kelompok.js.map