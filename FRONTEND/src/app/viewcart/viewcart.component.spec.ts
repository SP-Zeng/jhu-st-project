import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ViewcartComponent } from './viewcart.component';
import { ApiService } from '../api.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { of } from 'rxjs';

class ApiServiceStub {
  getcart(_customerId: string) {
    return of([]);
  }
}

class ToastrServiceStub {
  success(_message: string) {}
  error(_message: string) {}
}

class RouterStub {
  navigate(_path: string[]) {}
}

describe('ViewcartComponent', () => {
  let component: ViewcartComponent;
  let fixture: ComponentFixture<ViewcartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ViewcartComponent],
      providers: [
        { provide: ApiService, useClass: ApiServiceStub },
        { provide: ToastrService, useClass: ToastrServiceStub },
        { provide: Router, useClass: RouterStub },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewcartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have an initial total of 0', () => {
    expect(component.total).toBe(0);
  });

  it('should have an empty list initially', () => {
    expect(component.list).toEqual([]);
  });


});

