const portfolio = [
  { symbol: "AAPL", shares: 25, buyPrice: 140.00, currentPrice: 176.35 },
  { symbol: "MSFT", shares: 10, buyPrice: 310.50, currentPrice: 405.12 },
  { symbol: "NVDA", shares: 5,  buyPrice: 450.00, currentPrice: 870.42 },
  { symbol: "GOOGL", shares: 12, buyPrice: 120.30, currentPrice: 165.10 },
  { symbol: "TSLA", shares: 8, buyPrice: 220.00, currentPrice: 255.75 },
  { symbol: "AMZN", shares: 15, buyPrice: 130.00, currentPrice: 182.35 },
  { symbol: "META", shares: 6, buyPrice: 280.00, currentPrice: 460.12 },
  { symbol: "NFLX", shares: 4, buyPrice: 390.00, currentPrice: 495.50 },
  { symbol: "INTC", shares: 20, buyPrice: 34.50, currentPrice: 42.80 },
  { symbol: "ADBE", shares: 7, buyPrice: 480.00, currentPrice: 550.25 },
]

function summarize(positions){
  const rows = positions.map(p => {
    const invested = p.shares * p.buyPrice
    const valueNow = p.shares * p.currentPrice
    const pnl = valueNow - invested
    const ret = invested === 0 ? 0 : (pnl / invested) * 100
    return {...p, invested, valueNow, pnl, ret}
  })

  let totals = {invested:0, valueNow:0, pnl:0}
  for(let i=0; i<rows.length; i++){
    totals.invested += rows[i].invested
    totals.valueNow += rows[i].valueNow
    totals.pnl += rows[i].pnl
  }
  totals.ret = (totals.pnl / totals.invested) * 100
  return {rows, totals}
}

function buildReport(positions){
  let hasil = summarize(positions)
  let rows = hasil.rows
  let total = hasil.totals

  let text = ""
  text += "Portfolio Report\n"
  text += "=============================\n"
  text += "Stock | Shares | Buy | Current | Invested | Value | P/L | Return\n"
  text += "---------------------------------------------------------------\n"

  for(let i=0; i<rows.length; i++){
    let r = rows[i]
    text += r.symbol + " | " + r.shares + " | $" + r.buyPrice.toFixed(2) + " | $" + r.currentPrice.toFixed(2)
    text += " | $" + r.invested.toFixed(2) + " | $" + r.valueNow.toFixed(2) + " | $" + r.pnl.toFixed(2)
    text += " | " + r.ret.toFixed(2) + "%\n"
  }

  text += "---------------------------------------------------------------\n"
  text += "Total Invested : $" + total.invested.toFixed(2) + "\n"
  text += "Total Value    : $" + total.valueNow.toFixed(2) + "\n"
  text += "Total P/L      : $" + total.pnl.toFixed(2) + "\n"
  text += "Performance    : " + total.ret.toFixed(2) + "%\n"

  return text
}

console.log(buildReport(portfolio))
