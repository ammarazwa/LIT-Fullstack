import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PortfolioExampleComponent } from './portfolio-example.component';

describe('PortfolioExampleComponent', () => {
  let component: PortfolioExampleComponent;
  let fixture: ComponentFixture<PortfolioExampleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PortfolioExampleComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PortfolioExampleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
