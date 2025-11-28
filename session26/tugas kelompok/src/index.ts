// ---------- Custom Errors ----------
class InvalidStockError extends Error {
  constructor(message: string) {
    super(message);
    this.name = "InvalidStockError";
  }
}

class InsufficientFundsError extends Error {
  constructor(message: string) {
    super(message);
    this.name = "InsufficientFundsError";
  }
}

class InsufficientHoldingsError extends Error {
  constructor(message: string) {
    super(message);
    this.name = "InsufficientHoldingsError";
  }
}

// ---------- Result Type ----------
type Result<T, E extends Error = Error> =
  | { ok: true; data: T }
  | { ok: false; error: E };

function Ok<T>(value: T): Result<T> {
  return { ok: true, data: value };
}

function Err<E extends Error>(error: E): Result<never, E> {
  return { ok: false, error };
}

// ---------- Stock and Portfolio Management ----------

type StockKind = "equity" | "crypto" | "etf";

interface StockBase {
  symbol: string;
  name: string;
  quantity: number;
  purchasePrice: number;
}

type EquityStock = StockBase & { kind: "equity"; exchange: string };
type CryptoStock = StockBase & { kind: "crypto"; chain: string };
type ETFStock = StockBase & { kind: "etf"; provider: string };
type Stock = EquityStock | CryptoStock | ETFStock;

type PositionSummary = Pick<Stock, "symbol" | "name" | "quantity"> & {
  marketValue: number;
};

type UpdatePositionInput = Partial<
  Pick<Stock, "name" | "quantity" | "purchasePrice">
> & { symbol: string };

// ---------- Discriminated Unions (Transactions) ----------
type TransactionState = "pending" | "completed" | "failed";

type Transaction = {
  id: string;
  stockSymbol: string;
  quantity: number;
  price: number;
  state: TransactionState;
  reason?: string;
  timestamp: Date;
};

function isPending(t: Transaction): t is Transaction & { state: "pending" } {
  return t.state === "pending";
}

function isCompleted(t: Transaction): t is Transaction & { state: "completed" } {
  return t.state === "completed";
}

function isFailed(t: Transaction): t is Transaction & { state: "failed" } {
  return t.state === "failed";
}

// ---------- Portfolio Class ----------
class Portfolio {
  owner: string;
  stocks: Stock[] = [];

  constructor(owner: string, stocks: Stock[] = []) {
    this.owner = owner;
    this.stocks = stocks;
  }

  getTotalValue(): Result<number> {
    try {
      const value = this.stocks.reduce(
        (sum, stock) => sum + stock.quantity * stock.purchasePrice,
        0
      );
      return { ok: true, data: value };
    } catch (err: any) {
      return { ok: false, error: err };
    }
  }

  addStock(stock: Stock): Result<Stock> {
    this.stocks.push(stock);
    return { ok: true, data: stock };
  }

  removeStock(symbol: string): Result<string> {
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

  updateStock(symbol: string, updates: Partial<Stock>): Result<Stock> {
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

  getStockSummary(): Pick<Stock, "symbol" | "quantity">[] {
    return this.stocks.map(({ symbol, quantity }) => ({ symbol, quantity }));
  }

  async updatePrice(symbol: string): Promise<Result<number>> {
    const priceResult = await fetchStockPrice(symbol);
    if (!priceResult.ok) return priceResult;

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
async function fetchStockPrice(symbol: string): Promise<Result<number>> {
  return new Promise((resolve) => {
    setTimeout(() => {
      const mockPrices: Record<string, number> = {
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
      } else {
        resolve({ ok: true, data: price });
      }
    }, 500);
  });
}

// ---------- TransactionHandler Class ----------
class TransactionHandler {
  private transactions: Transaction[] = [];

  constructor(private portfolio: Portfolio) {}

  createTransaction(stockSymbol: string, quantity: number, price: number): Transaction {
    const transaction: Transaction = {
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

  completeTransaction(transactionId: string): Result<Transaction> {
    const tx = this.transactions.find((t) => t.id === transactionId);
    if (!tx) return Err(new InvalidStockError("Transaction not found"));

    if (tx.state !== "pending") return Err(new InvalidStockError("Transaction already processed"));

    tx.state = "completed";
    return Ok(tx);
  }

  failTransaction(transactionId: string, reason: string): Result<Transaction> {
    const tx = this.transactions.find((t) => t.id === transactionId);
    if (!tx) return Err(new InvalidStockError("Transaction not found"));

    if (tx.state !== "pending") return Err(new InvalidStockError("Transaction already processed"));

    tx.state = "failed";
    tx.reason = reason;
    return Ok(tx);
  }

  getTransactions(): Transaction[] {
    return this.transactions;
  }
}

// ---------- Utility Function ----------
function cryptoRandomId(len = 12): string {
  if (typeof crypto !== "undefined" && "getRandomValues" in crypto) {
    const arr = new Uint8Array(len);
    crypto.getRandomValues(arr);
    return Array.from(arr, (b) => b.toString(16).padStart(2, "0")).join("").slice(0, len);
  }
  return Math.random().toString(36).slice(2, 2 + len);
}

// ---------- Demo ----------
(async () => {
const sampleStocks: Stock[] = [
  { symbol: "AAPL", name: "Apple", quantity: 10, purchasePrice: 150, kind: "equity", exchange: "NASDAQ" },
  { symbol: "GOOG", name: "Google", quantity: 5, purchasePrice: 2800, kind: "equity", exchange: "NASDAQ" },
  { symbol: "TSLA", name: "Tesla", quantity: 3, purchasePrice: 700, kind: "equity", exchange: "NASDAQ" },
  { symbol: "NFLX", name: "Netflix", quantity: 4, purchasePrice: 500, kind: "equity", exchange: "NASDAQ" },
  { symbol: "AMZN", name: "Amazon", quantity: 2, purchasePrice: 3300, kind: "equity", exchange: "NASDAQ" },
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
