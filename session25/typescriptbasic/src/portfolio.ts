
interface Stock {
  symbol: string;
  name: string;
  quantity: number;
  purchasePrice: number; // price per share
}

interface Portfolio {
  owner: string;
  stocks: Stock[];

  totalValue(): number; 
}


function calculatePortfolioValue(stocks: Stock[]): number {
  let total = 0;

  for (const s of stocks) {
    total += s.quantity * s.purchasePrice;
  }

  return total;
}


function addStock(portfolio: Portfolio, stock: Stock): void {
  portfolio.stocks.push(stock);
}

function removeStock(portfolio: Portfolio, symbol: string): void {
  portfolio.stocks = portfolio.stocks.filter(s => s.symbol !== symbol);
}


const portfolio: Portfolio = {
  owner: "Clarisya",
  stocks: [],

  totalValue() {
    return calculatePortfolioValue(this.stocks);
  }
};

const AAPL: Stock = {
  symbol: "AAPL",
  name: "Apple",
  quantity: 10,
  purchasePrice: 150
};

const GOOGL: Stock = {
  symbol: "GOOGL",
  name: "Alphabet",
  quantity: 5,
  purchasePrice: 2800
};

const MSFT: Stock = {
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
