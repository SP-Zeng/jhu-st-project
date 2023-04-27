import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { LoginComponent } from './login.component';
import { ApiService } from '../api.service';
import { of } from 'rxjs';

class ApiServiceStub {
  validate() {
    return of({});
  }
}

class ToastrServiceStub {
  success() {}
  error() {}
}

class RouterStub {
  navigate() {}
}

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [ReactiveFormsModule],
      providers: [
        FormBuilder,
        { provide: ApiService, useClass: ApiServiceStub },
        { provide: ToastrService, useClass: ToastrServiceStub },
        { provide: Router, useClass: RouterStub },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


});

