import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { ApiService } from '../api.service';
import { CategoryComponent } from './category.component';
import { of } from 'rxjs';

class ApiServiceStub {
  deletecategory() {
    return of({}); // Return a mock Observable with an empty object
  }
  listcategories() {
    return of([]); // Return a mock Observable with an empty array
  }
  savecategory() {
    return of({}); // Return a mock Observable with an empty object
  }
}

class ToastrServiceStub {
  success() {}
  error() {}
}

describe('CategoryComponent', () => {
  let component: CategoryComponent;
  let fixture: ComponentFixture<CategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CategoryComponent],
      imports: [ReactiveFormsModule],
      providers: [
        FormBuilder,
        { provide: ApiService, useClass: ApiServiceStub },
        { provide: ToastrService, useClass: ToastrServiceStub },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have an invalid form when empty', () => {
    expect(component.fg.valid).toBeFalsy();
  });

  it('should have a valid form when filled', () => {
    component.fg.controls['catname'].setValue('TestCategory');
    expect(component.fg.valid).toBeTruthy();
  });

});

