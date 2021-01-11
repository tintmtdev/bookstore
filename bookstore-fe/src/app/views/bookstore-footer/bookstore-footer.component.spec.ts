import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookstoreFooterComponent } from './bookstore-footer.component';

describe('BookstoreFooterComponent', () => {
  let component: BookstoreFooterComponent;
  let fixture: ComponentFixture<BookstoreFooterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookstoreFooterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BookstoreFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
