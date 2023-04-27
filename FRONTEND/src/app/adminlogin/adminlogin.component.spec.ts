import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { ToastrService } from 'ngx-toastr';
import { ApiService } from '../api.service';

import { AdminloginComponent } from './adminlogin.component';

class ApiServiceStub {}
class ToastrServiceStub {
  success() {}
  error() {}
}

describe('AdminloginComponent', () => {
  let component: AdminloginComponent;
  let fixture: ComponentFixture<AdminloginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminloginComponent],
      imports: [ReactiveFormsModule, RouterTestingModule],
      providers: [
        FormBuilder,
        { provide: ApiService, useClass: ApiServiceStub },
        { provide: ToastrService, useClass: ToastrServiceStub },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminloginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have invalid form when empty', () => {
    expect(component.fg.valid).toBeFalsy();
  });

  it('should have valid form when filled', () => {
    component.fg.controls['userid'].setValue('admin');
    component.fg.controls['pwd'].setValue('password');
    expect(component.fg.valid).toBeTruthy();
  });

  it('should not call validateadmin() when form is invalid', () => {
    const validateadminSpy = spyOn(component, 'validateadmin');
    component.fg.controls['userid'].setValue('');
    component.fg.controls['pwd'].setValue('');
    component.validateadmin(component.fg.value);
    expect(validateadminSpy).not.toHaveBeenCalled();
  });


});
