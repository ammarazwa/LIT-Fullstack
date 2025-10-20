const portfolio = [
  { symbol: "AAPL", shares: 25, buyPrice: 140.00, currentPrice: 176.35 },
  { symbol: "MSFT", shares: 10, buyPrice: 310.50, currentPrice: 405.12 },
  { symbol: "NVDA", shares: 5,  buyPrice: 450.00, currentPrice: 870.42 },
  { symbol: "GOOGL", shares: 12, buyPrice: 120.30, currentPrice: 165.10 },
];

const fmtMoney = v =>
  new Intl.NumberFormat("en-US", { style: "currency", currency: "USD" }).format(v);

const pad = (s, n) => String(s).padEnd(n);

function summarize(positions) {
  const rows = positions.map(p => {
    const invested = p.shares * p.buyPrice;
    const valueNow = p.shares * p.currentPrice;
    const pnl = valueNow - invested;
    const ret = invested === 0 ? 0 : (pnl / invested) * 100;
    return { ...p, invested, valueNow, pnl, ret };
  });

  const totals = rows.reduce(
    (acc, r) => {
      acc.invested += r.invested;
      acc.valueNow += r.valueNow;
      acc.pnl += r.pnl;
      return acc;
    },
    { invested: 0, valueNow: 0, pnl: 0 }
  );
  totals.ret = totals.invested === 0 ? 0 : (totals.pnl / totals.invested) * 100;

  return { rows, totals };
}

function buildReport(positions) {
  const { rows, totals } = summarize(positions);

let report = `
Portfolio Report:
=================
`.trimStart();

  report += `\n${pad("Stock", 8)} ${pad("Shares", 8)} ${pad("Buy", 12)} ${pad(
    "Current",
    12
  )} ${pad("Invested", 12)} ${pad("Value", 12)} ${pad("P/L", 12)} Return\n`;
  report += `${"-".repeat(90)}\n`;

  rows.forEach(r => {
    report += `${pad(r.symbol, 8)} ${pad(r.shares, 8)} ${pad(fmtMoney(r.buyPrice), 12)} ${pad(
      fmtMoney(r.currentPrice),
      12
    )} ${pad(fmtMoney(r.invested), 12)} ${pad(fmtMoney(r.valueNow), 12)} ${pad(
      fmtMoney(r.pnl),
      12
    )} ${r.ret.toFixed(2)}%\n`;
  });

  report += `${"-".repeat(90)}\n`;
  report += `Total Invested : ${fmtMoney(totals.invested)}\n`;
  report += `Total Value    : ${fmtMoney(totals.valueNow)}\n`;
  report += `Total P/L      : ${fmtMoney(totals.pnl)}\n`;
  report += `Performance    : ${totals.ret.toFixed(2)}%\n`;

  return report;
}

console.log(buildReport(portfolio));
