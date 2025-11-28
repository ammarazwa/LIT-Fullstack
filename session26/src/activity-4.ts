// ---------- 1. Singleton Pattern - PortfolioManager ----------

class PortfolioManager {
  private static instance: PortfolioManager;
  private portfolios: Map<string, Portfolio> = new Map();

  private constructor() {}

  static getInstance(): PortfolioManager {
    if (!PortfolioManager.instance) {
      PortfolioManager.instance = new PortfolioManager();
    }
    return PortfolioManager.instance;
  }

  createPortfolio(owner: string): Portfolio {
    const portfolio = new Portfolio(owner);
    this.portfolios.set(owner, portfolio);
    return portfolio;
  }

  getPortfolio(owner: string): Portfolio | undefined {
    return this.portfolios.get(owner);
  }
}

// ---------- 2. Factory Pattern - StockFactory ----------

type StockType = "equity" | "crypto" | "etf";

class StockFactory {
  static createStock(type: StockType, symbol: string, name: string, quantity: number, price: number): StockBase {
    switch (type) {
      case "equity":
        return new EquityStockClass(symbol, name, quantity, price);
      case "crypto":
        return new CryptoStockClass(symbol, name, quantity, price);
      case "etf":
        return new ETFStockClass(symbol, name, quantity, price);
      default:
        throw new Error("Invalid stock type");
    }
  }
}

// ---------- 3. Stock Base Class and Extended Stock Types ----------

class StockBase {
  constructor(public symbol: string, public name: string, public quantity: number, public price: number) {}
}

class EquityStockClass extends StockBase {
  constructor(symbol: string, name: string, quantity: number, price: number) {
    super(symbol, name, quantity, price);
  }
}

class CryptoStockClass extends StockBase {
  constructor(symbol: string, name: string, quantity: number, price: number) {
    super(symbol, name, quantity, price);
  }
}

class ETFStockClass extends StockBase {
  constructor(symbol: string, name: string, quantity: number, price: number) {
    super(symbol, name, quantity, price);
  }
}

// ---------- 4. Observer Pattern - Price Change Notification ----------

interface PriceObserver {
  update(symbol: string, price: number): void;
}

class StockPriceNotifier {
  private observers: PriceObserver[] = [];

  addObserver(observer: PriceObserver): void {
    this.observers.push(observer);
  }

  removeObserver(observer: PriceObserver): void {
    this.observers = this.observers.filter((obs) => obs !== observer);
  }

  notify(symbol: string, price: number): void {
    for (const observer of this.observers) {
      observer.update(symbol, price);
    }
  }

  setNewPrice(symbol: string, price: number): void {
    this.notify(symbol, price);
  }
}

class StockPriceDisplay implements PriceObserver {
  update(symbol: string, price: number): void {
    console.log(`Stock ${symbol} price updated to: ${price}`);
  }
}

// ---------- 5. Builder Pattern - PortfolioBuilder ----------

class Portfolio {
  private stocks: StockBase[] = [];
  private cash: number = 0;
  private owner: string;

  constructor(owner: string) {
    this.owner = owner;
  }

  addStock(stock: StockBase): void {
    this.stocks.push(stock);
  }

  setCash(amount: number): void {
    this.cash = amount;
  }

  getPortfolioDetails(): string {
    return `Portfolio for ${this.owner} has ${this.stocks.length} stocks and $${this.cash} in cash.`;
  }
}

class PortfolioBuilder {
  private portfolio: Portfolio;

  constructor(owner: string) {
    this.portfolio = new Portfolio(owner);
  }

  addStock(stock: StockBase): PortfolioBuilder {
    this.portfolio.addStock(stock);
    return this;
  }

  setCash(amount: number): PortfolioBuilder {
    this.portfolio.setCash(amount);
    return this;
  }

  build(): Portfolio {
    return this.portfolio;
  }
}

// ---------- Demonstrating the Patterns ----------

(() => {
  // 1. Singleton Pattern
  const portfolioManager1 = PortfolioManager.getInstance();
  const portfolioManager2 = PortfolioManager.getInstance();
  console.log("Same instance:", portfolioManager1 === portfolioManager2);

  const myPortfolio = portfolioManager1.createPortfolio("John Doe");

  // 2. Factory Pattern
  const appleStock = StockFactory.createStock("equity", "AAPL", "Apple", 10, 150);
  const bitcoinStock = StockFactory.createStock("crypto", "BTC", "Bitcoin", 5, 45000);
  myPortfolio.addStock(appleStock);
  myPortfolio.addStock(bitcoinStock);

  // 3. Observer Pattern
  const priceNotifier = new StockPriceNotifier();
  const display = new StockPriceDisplay();
  priceNotifier.addObserver(display);

  priceNotifier.setNewPrice("AAPL", 160);
  priceNotifier.setNewPrice("BTC", 46000);

  // 4. Builder Pattern
  const portfolioBuilder = new PortfolioBuilder("Jane Smith");
  const newPortfolio = portfolioBuilder
    .addStock(new EquityStockClass("GOOG", "Google", 5, 2800))
    .addStock(new CryptoStockClass("ETH", "Ethereum", 3, 3000))
    .setCash(50000)
    .build();

  console.log(newPortfolio.getPortfolioDetails());
})();
