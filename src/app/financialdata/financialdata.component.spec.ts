import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinancialdataComponent } from './financialdata.component';

describe('FinancialdataComponent', () => {
  let component: FinancialdataComponent;
  let fixture: ComponentFixture<FinancialdataComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FinancialdataComponent]
    });
    fixture = TestBed.createComponent(FinancialdataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
