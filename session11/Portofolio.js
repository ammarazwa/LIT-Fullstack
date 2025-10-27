function simulateTradingAlgorithm() {
  const initialCapital = 10000;
  let capital = initialCapital;
  let shares = 0;
  let avgEntryPrice = 0; // rata-rata harga masuk posisi saat ini
  const prices = generateDailyPrices(30);
  const portfolio = [];
  const trades = [];

  console.log("=== Trading Simulation ===");

  for (let day = 0; day < prices.length; day++) {
    const price = prices[day];

    // running average dari hari-hari sebelumnya
    let averagePrice = 0;
    if (day > 0) {
      let sum = 0;
      for (let i = 0; i < day; i++) {
        sum += prices[i];
      }
      averagePrice = sum / day;
    }

    // --- SELL ---
    const takeProfit = averagePrice > 0 && price >= averagePrice * 1.03;
    const stopLoss   = shares > 0 && avgEntryPrice > 0 && price <= avgEntryPrice * 0.94; // -6%

    if (shares > 0 && (takeProfit || stopLoss)) {
      const revenue = shares * price;
      capital += revenue;
      trades.push({ day: day + 1, type: stopLoss ? "SELL(SL)" : "SELL(TP)", qty: shares, price });
      shares = 0;
      avgEntryPrice = 0;
    }
    // --- BUY ---
    else if (shouldBuy(price, averagePrice)) {
      const budget = capital * 0.5;
      const qty = Math.floor(budget / price);
      if (qty > 0) {
        capital -= qty * price;
        avgEntryPrice = (avgEntryPrice * shares + qty * price) / (shares + qty || 1);
        shares += qty;
        trades.push({ day: day + 1, type: "BUY", qty, price });
      }
    }

    // track nilai portofolio harian
    const totalValue = calculatePortfolioValue(capital, shares, price);
    portfolio.push({
      day: day + 1,
      price,
      avg: averagePrice ? averagePrice.toFixed(2) : "-",
      shares,
      capital: capital.toFixed(2),
      totalValue: totalValue.toFixed(2)
    });
  }

  // likuidasi sisa saham di akhir
  const lastPrice = prices[prices.length - 1];
  if (shares > 0) {
    capital += shares * lastPrice;
    trades.push({ day: 30, type: "SELL(Final)", qty: shares, price: lastPrice });
    shares = 0;
    avgEntryPrice = 0;
  }

  const finalValue = capital;
  const profit = finalValue - initialCapital;

  console.log("\n=== DAILY REPORT ===");
  console.table(portfolio);

  console.log("\n=== TRADE HISTORY ===");
  trades.length ? console.table(trades) : console.log("No trades.");

  console.log("\n=== SUMMARY ===");
  console.log(`Initial Capital : $${initialCapital.toFixed(2)}`);
  console.log(`Final Value     : $${finalValue.toFixed(2)}`);
  console.log(`Net Profit/Loss : $${profit.toFixed(2)} (${(profit/initialCapital*100).toFixed(2)}%)`);
  console.log("=============================");
}

// ==== Helper functions (tetap sederhana, mirip punyamu) ====
function generateDailyPrices(days) {
  const prices = [100];
  for (let i = 1; i < days; i++) {
    const prev = prices[i - 1];
    const changePct = (Math.random() * 6 - 3) / 100; // -3%..+3%
    const next = Math.max(1, prev * (1 + changePct));
    prices.push(Number(next.toFixed(2)));
  }
  return prices;
}
function shouldBuy(price, averagePrice) {
  return averagePrice > 0 && price <= averagePrice * 0.98;
}
function calculatePortfolioValue(capital, shares, price) {
  return capital + shares * price;
}

// jalankan
simulateTradingAlgorithm();
