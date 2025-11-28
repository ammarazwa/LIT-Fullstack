import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-portfolio-example',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './portfolio-example.component.html',
  styleUrls: ['./portfolio-example.component.scss']
})


export class PortfolioExampleComponent implements OnInit { 

  @Input() titleInput: string = '';
  @Output() titleOutput = new EventEmitter<string>();

  portfolioName: string = 'My Portfolio';
  totalValue: number = 100000;
  stocks: { symbol: string, shares: number, price: number }[] = [
    { symbol: 'AAPL', shares: 150, price: 150 },
    { symbol: 'GOOGL', shares: 50, price: 2000 },
    { symbol: 'MSFT', shares: 80, price: 300 }
  ];

  isDisabled: boolean = false;
  searchTerm: string = ''; 
  newStockSymbol: string = '';

  ngOnInit(): void {
    console.log('Portfolio component initialized');
    this.calculateTotalValue();
  }

  calculateTotalValue(): void {
    this.totalValue = this.stocks.reduce((total, stock) => {
      return total + (stock.shares * stock.price);
    }, 0);
  }

  addStock(symbol: string): void {
    if (symbol && symbol.trim() !== '') {
      this.stocks.push({ symbol: symbol.trim().toUpperCase(), shares: 10, price: 100 });
      this.calculateTotalValue();
      this.newStockSymbol = ''; // Clear input after adding
    }
  }

  removeStock(index: number): void {
    this.stocks.splice(index, 1);
    this.calculateTotalValue();
  }

  disableButton(): void {
    this.isDisabled = true;
  }

  enableButton(): void {
    this.isDisabled = false;
  }

  sendOutput(): void {  
    this.titleOutput.emit('Portfolio Updated: ' + this.portfolioName);
    console.log('Output event triggered!');
  }

  get filteredStocks() {
    if (!this.searchTerm) {
      return this.stocks;
    }
    return this.stocks.filter(stock => 
      stock.symbol.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}