// Requirements:
// 1. Add stocks to portfolio
// 2. Remove stocks from portfolio
// 3. Calculate total portfolio value
// 4. Calculate profit/loss for each stock
// 5. Calculate overall portfolio performance
// 6. Generate portfolio report

class PortfolioManager {
  constructor() {
    this.stocks = []; // [{ symbol, shares, buyPrice (avg), currentPrice }]
  }

  // Cari index saham
  findIdx(symbol) {
    return this.stocks.findIndex(s => s.symbol === symbol);
  }

  // 1. Add stocks to portfolio
  // gabungkan & average buy price
  addStock(symbol, shares, buyPrice, currentPrice) {
    if (shares <= 0 || buyPrice <= 0 || currentPrice <= 0) {
      console.log("Invalid input: shares and prices must be positive numbers.");
      return;
    }
    const idx = this.findIdx(symbol);
    if (idx === -1) {
      this.stocks.push({ symbol, shares, buyPrice, currentPrice });
    } else {
      const s = this.stocks[idx];
      const newShares = s.shares + shares;
      const newAvgBuy =
        (s.shares * s.buyPrice + shares * buyPrice) / newShares;
      this.stocks[idx] = {
        symbol,
        shares: newShares,
        buyPrice: newAvgBuy,
        currentPrice
      };
      console.log(`Averaging ${symbol}: shares=${newShares}, avgBuy=$${newAvgBuy.toFixed(2)}`);
    }
  }

  // 2. Remove stocks from portfolio
  // jual sebagian atau semua
  removeStock(symbol, qty = null) {
    const idx = this.findIdx(symbol);
    if (idx === -1) {
      console.log(`Stock ${symbol} not found in portfolio.`);
      return;
    }
    if (qty === null || qty >= this.stocks[idx].shares) {
      console.log(`Removing ${symbol} (all) from portfolio...`);
      this.stocks.splice(idx, 1);
    } else if (qty > 0) {
      this.stocks[idx].shares -= qty;
      console.log(`Removing ${qty} shares of ${symbol} (remaining: ${this.stocks[idx].shares})`);
      if (this.stocks[idx].shares === 0) this.stocks.splice(idx, 1);
    } else {
      console.log("Invalid qty for removeStock.");
    }
  }

  // Update harga 1 simbol
  setCurrentPrice(symbol, newPrice) {
    const idx = this.findIdx(symbol);
    if (idx === -1) {
      console.log(`Stock ${symbol} not found.`);
      return;
    }
    if (newPrice <= 0) {
      console.log("Invalid price.");
      return;
    }
    this.stocks[idx].currentPrice = newPrice;
  }

  // Bulk update harga
  bulkUpdatePrices(priceMap) {
    for (const [sym, p] of Object.entries(priceMap)) {
      this.setCurrentPrice(sym, p);
    }
  }

  // 3. Calculate total portfolio value
  // Nilai total sekarang
  calculateTotalValue() {
    return this.stocks.reduce((sum, s) => sum + s.shares * s.currentPrice, 0);
  }

  // Investasi awal (berdasar average buy terkini x shares)
  calculateTotalInvested() {
    return this.stocks.reduce((sum, s) => sum + s.shares * s.buyPrice, 0);
  }

  // 4. Calculate profit/loss for each stock
  // Profit n Loss per saham
  calculatePnL(stock) {
    const invested = stock.shares * stock.buyPrice;
    const valueNow = stock.shares * stock.currentPrice;
    const pnl = valueNow - invested;
    const ret = invested > 0 ? (pnl / invested) * 100 : 0;
    return { invested, valueNow, pnl, ret };
  }

  // 5. Calculate overall portfolio performance
  calculatePerformance() {
    let totalInvested = 0;
    let totalValue = 0;
    for (const s of this.stocks) {
      const { invested, valueNow } = this.calculatePnL(s);
      totalInvested += invested;
      totalValue += valueNow;
    }
    const totalPnL = totalValue - totalInvested;
    const totalReturn = totalInvested > 0 ? (totalPnL / totalInvested) * 100 : 0;
    return { totalInvested, totalValue, totalPnL, totalReturn };
  }

  // Bobot tiap saham terhadap total nilai saat ini
  getWeights() {
    const total = this.calculateTotalValue();
    return this.stocks.map(s => {
      const value = s.shares * s.currentPrice;
      const weight = total > 0 ? (value / total) * 100 : 0;
      return { symbol: s.symbol, weight };
    });
  }

  // Top movers berdasarkan return (%)
  topMovers(n = 3) {
    const sorted = [...this.stocks].sort((a, b) => {
      const ra = this.calculatePnL(a).ret;
      const rb = this.calculatePnL(b).ret;
      return rb - ra;
    });
    return sorted.slice(0, n).map(s => {
      const { pnl, ret } = this.calculatePnL(s);
      return { symbol: s.symbol, ret: Number(ret.toFixed(2)), pnl: Number(pnl.toFixed(2)) };
    });
  }

// 6. Generate portfolio report  
generateReport({ sortBy = "symbol" } = {}) {
    const summary = this.calculatePerformance();
    const weights = Object.fromEntries(this.getWeights().map(w => [w.symbol, w.weight]));

    let rows = this.stocks.map(s => {
      const { pnl, ret } = this.calculatePnL(s);
      return {
        symbol: s.symbol,
        shares: s.shares,
        buy: s.buyPrice,
        current: s.currentPrice,
        pnl,
        ret,
        weight: weights[s.symbol] || 0
      };
    });

    if (sortBy === "pnl") rows.sort((a, b) => b.pnl - a.pnl);
    else if (sortBy === "return") rows.sort((a, b) => b.ret - a.ret);
    else rows.sort((a, b) => a.symbol.localeCompare(b.symbol));

    let report = "\nPORTFOLIO REPORT\n";
    report += "=======================================================================\n";
    report += "Symbol | Shares |    Buy | Current |   P/L ($) | Return (%) | Weight (%)\n";
    report += "-----------------------------------------------------------------------\n";

    for (const r of rows) {
      report += `${r.symbol.padEnd(6)} | ${String(r.shares).padEnd(6)} | $${r.buy.toFixed(2).padStart(7)} | $${r.current.toFixed(2).padStart(7)} | $${r.pnl.toFixed(2).padStart(9)} | ${r.ret.toFixed(2).padStart(9)} | ${r.weight.toFixed(2).padStart(9)}\n`;
    }

    report += "-----------------------------------------------------------------------\n";
    report += `Total Invested : $${summary.totalInvested.toFixed(2)}\n`;
    report += `Current Value  : $${summary.totalValue.toFixed(2)}\n`;
    report += `Total P/L      : $${summary.totalPnL.toFixed(2)}\n`;
    report += `Performance    : ${summary.totalReturn.toFixed(2)}%\n`;
    report += "=======================================================================\n";

    // Tambahan insight singkat
    const movers = this.topMovers(3);
    if (movers.length) {
      report += "Top Movers (by Return %):\n";
      movers.forEach((m, i) => {
        report += `${i + 1}. ${m.symbol} — ${m.ret}% (P/L $${m.pnl})\n`;
      });
      report += "=======================================================================\n";
    }

    return report;
  }

}

// ====== Demo ======
let portfolio = new PortfolioManager();

portfolio.addStock("AAPL", 25, 140, 176.35);
portfolio.addStock("GOOGL", 12, 120.30, 165.10);
portfolio.addStock("MSFT", 10, 310.50, 405.12);
portfolio.addStock("TSLA", 8, 220, 255.75);
portfolio.addStock("BBCA", 100, 9800, 10400);

// Tambah AAPL lagi (akan di-average)
portfolio.addStock("AAPL", 5, 170, 176.35);

console.log(portfolio.generateReport({ sortBy: "pnl" }));

// Partial remove: jual 3 lembar TSLA
portfolio.removeStock("TSLA", 3);

// Update harga terbaru
portfolio.bulkUpdatePrices({
  AAPL: 180.12,
  GOOGL: 162.05,
  MSFT: 410.00,
  TSLA: 248.30,
  BBCA: 10550
});

console.log(portfolio.generateReport({ sortBy: "return" }));

// Full remove: hapus GOOGL
portfolio.removeStock("GOOGL");

console.log(portfolio.generateReport({ sortBy: "symbol" }));
