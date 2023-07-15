import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddStaffComponent } from './edit-staff.component';

describe('AddStaffComponent', () => {
  let component: AddStaffComponent;
  let fixture: ComponentFixture<AddStaffComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddStaffComponent]
    });
    fixture = TestBed.createComponent(AddStaffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
