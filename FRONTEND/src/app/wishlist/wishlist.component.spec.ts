import { ComponentFixture, TestBed } from '@angular/core/testing';
import { WishlistComponent } from './wishlist.component';
import { ApiService } from '../api.service';
import { ToastrService } from 'ngx-toastr';
import { of } from 'rxjs';

class ApiServiceStub {
  getwishlist(_customerId: string) {
    return of([]);
  }
}

class ToastrServiceStub {
  success(_message: string) {}
}

describe('WishlistComponent', () => {
  let component: WishlistComponent;
  let fixture: ComponentFixture<WishlistComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WishlistComponent],
      providers: [
        { provide: ApiService, useClass: ApiServiceStub },
        { provide: ToastrService, useClass: ToastrServiceStub },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WishlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have an empty list initially', () => {
    expect(component.list).toEqual([]);
  });


});

