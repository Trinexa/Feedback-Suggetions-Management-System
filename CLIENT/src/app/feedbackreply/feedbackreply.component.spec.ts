import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackreplyComponent } from './feedbackreply.component';

describe('FeedbackreplyComponent', () => {
  let component: FeedbackreplyComponent;
  let fixture: ComponentFixture<FeedbackreplyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FeedbackreplyComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FeedbackreplyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
