import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProductsComponent } from './products.component';
import { FormBuilder } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ApiService } from '../api.service';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

class ApiServiceStub {
  listcategories() {
    return of([]);
  }

  listproducts() {
    return of([]);
  }
}

class ToastrServiceStub {
  success() {}
  error() {}
}

describe('ProductsComponent', () => {
  let component: ProductsComponent;
  let fixture: ComponentFixture<ProductsComponent>;


  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [ProductsComponent],
      providers: [
        { provide: ApiService, useClass: ApiServiceStub },
        { provide: ToastrService, useClass: ToastrServiceStub },
        FormBuilder,
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have an empty list and cats initially', () => {
    expect(component.list.length).toBe(0);
    expect(component.cats.length).toBe(0);
  });



});

