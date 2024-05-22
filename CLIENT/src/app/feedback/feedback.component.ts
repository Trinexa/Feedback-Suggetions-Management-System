import { Component } from '@angular/core';
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { FeedbackService } from '../services/feedback.service';
import { FeedbackRequestBean } from '../models/feedbackRequestBean';

@Component({
  selector: 'app-feedback',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
  ],
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent {
  userId: number=0;
  suggestionType: string ='';
  rating: number=0;
  feedbackText: string='';

  public feedbackRequesrBean: FeedbackRequestBean = new FeedbackRequestBean;

  constructor(
    private feedbackService: FeedbackService,
  ) {
  }

  submitFeedback(form: any) {
    this.feedbackRequesrBean.id = 2;
    this.feedbackRequesrBean.userId = 2;
    this.feedbackRequesrBean.feedbackText = this.feedbackText;
    this.feedbackRequesrBean.rating = this.rating;
    this.feedbackRequesrBean.suggestionType = this.suggestionType;
    this.feedbackService.saveFeedback(this.feedbackRequesrBean)
    .subscribe(
        (response) =>  {
          alert('Added successfully');
          this.feedbackText = '';
          this.rating = 0;
          this.suggestionType = '';
          const add = response;
        },
        err => {
          console.log('Error', err);
        },
    );

    if (form.valid) {

      console.log("Feedback submitted: ", this.userId, this.suggestionType, this.rating, this.feedbackText);
    }
  }
}
