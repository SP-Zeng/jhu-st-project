import { ComponentFixture, TestBed } from '@angular/core/testing';
import { OrderhistoryComponent } from './orderhistory.component';
import { ApiService } from '../api.service';
import { of } from 'rxjs';

class ApiServiceStub {

  orderhistory() {
    return of([]);
  }

}

describe('OrderhistoryComponent', () => {
  let component: OrderhistoryComponent;
  let fixture: ComponentFixture<OrderhistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OrderhistoryComponent],
      providers: [
        { provide: ApiService, useClass: ApiServiceStub },
      ],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrderhistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have an empty list initially', () => {
    expect(component.list.length).toBe(0);
  });



});

