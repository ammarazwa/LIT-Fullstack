"use strict";
function calculatePortfolioValue(stocks) {
    let total = 0;
    for (const s of stocks) {
        total += s.quantity * s.purchasePrice;
    }
    return total;
}
function addStock(portfolio, stock) {
    portfolio.stocks.push(stock);
}
function removeStock(portfolio, symbol) {
    portfolio.stocks = portfolio.stocks.filter(s => s.symbol !== symbol);
}
const portfolio = {
    owner: "Clarisya",
    stocks: [],
    totalValue() {
        return calculatePortfolioValue(this.stocks);
    }
};
const AAPL = {
    symbol: "AAPL",
    name: "Apple",
    quantity: 10,
    purchasePrice: 150
};
const GOOGL = {
    symbol: "GOOGL",
    name: "Alphabet",
    quantity: 5,
    purchasePrice: 2800
};
const MSFT = {
    symbol: "MSFT",
    name: "Microsoft",
    quantity: 8,
    purchasePrice: 300
};
// TEST
addStock(portfolio, AAPL);
addStock(portfolio, GOOGL);
addStock(portfolio, MSFT);
console.log("Stocks:", portfolio.stocks);
console.log("Total Portfolio Value:", portfolio.totalValue());
removeStock(portfolio, "AAPL");
console.log("After removing AAPL:", portfolio.stocks);
console.log("Value After Removal:", portfolio.totalValue());
