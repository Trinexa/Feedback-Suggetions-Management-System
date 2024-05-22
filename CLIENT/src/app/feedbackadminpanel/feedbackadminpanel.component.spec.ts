import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedbackadminpanelComponent } from './feedbackadminpanel.component';

describe('FeedbackadminpanelComponent', () => {
  let component: FeedbackadminpanelComponent;
  let fixture: ComponentFixture<FeedbackadminpanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FeedbackadminpanelComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(FeedbackadminpanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
