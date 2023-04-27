import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { RegisterComponent } from './register.component';
import { ApiService } from '../api.service';
import { of } from 'rxjs';

class ApiServiceStub {
  register() {
    return of({});
  }
}

class RouterStub {
  navigate() {}
}

class ToastrServiceStub {
  success() {}
  error() {}
}

describe('RegisterComponent', () => {
  let component: RegisterComponent;
  let fixture: ComponentFixture<RegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [RegisterComponent],
      imports: [ReactiveFormsModule],
      providers: [
        { provide: ApiService, useClass: ApiServiceStub },
        { provide: Router, useClass: RouterStub },
        { provide: ToastrService, useClass: ToastrServiceStub },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have an empty FormGroup initially', () => {
    expect(component.fg.valid).toBeFalse();
  });

});

