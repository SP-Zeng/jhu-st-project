import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Component, ElementRef } from '@angular/core';
import { NgxCsvParser } from 'ngx-csv-parser';
import { BulkUploadComponent } from './bulk-upload.component';
import { of } from 'rxjs';

class NgxCsvParserStub {
  parse() {
    // Return a mock Observable with an empty array
    return of([]);
  }
}

describe('BulkUploadComponent', () => {
  let component: BulkUploadComponent;
  let fixture: ComponentFixture<BulkUploadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BulkUploadComponent],
      providers: [{ provide: NgxCsvParser, useClass: NgxCsvParserStub }],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BulkUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have empty csvRecords initially', () => {
    expect(component.csvRecords.length).toBe(0);
  });

});

