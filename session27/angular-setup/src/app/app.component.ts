import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PortfolioExampleComponent } from './portfolio-example/portfolio-example.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, PortfolioExampleComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})

export class AppComponent {
  title = 'angular-setup';

  handleTitleOutput(event: string): void {
    this.title = event;
    console.log('Title updated to:', event);
  }
}