import { Component, OnInit } from '@angular/core';
import { FeedbackResponseBean } from '../models/feedbackResponseBean';
import { FeedbackService } from '../services/feedback.service';
//import { FeedbackService } from '../services/feedback.service';
import { ActivatedRoute, Router } from '@angular/router';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-feedbackadminpanel',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './feedbackadminpanel.component.html',
  styleUrl: './feedbackadminpanel.component.css'
})
export class FeedbackadminpanelComponent implements OnInit {

  public feedbackResponse: FeedbackResponseBean = new FeedbackResponseBean;
  id: any;
  enableSubmit: boolean = true;

  constructor(
    private feedbackService: FeedbackService,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.route.params.subscribe(params => this.id = params['id']);
  }

  ropDownOptions = [
    { label: 'Option1', value: 'option1' },
    { label: 'Option2', value: 'option2' },
    { label: 'Option3', value: 'option3' },
  ];

  collapsed = false;

  toggleCollapsed(): void {
    this.collapsed = !this.collapsed;
  }

  ngOnInit(): void {
    this.getFeedback(this.id);
    // this.enableSubmit = true;
  }

  getFeedback(id: any) {
    this.feedbackService.getFeedback(id).subscribe(
      (response) => {
        this.feedbackResponse = response;
        if(this.feedbackResponse.feedbackReply != null){
          this.enableSubmit = false;
        } else {
          this.enableSubmit = true;
        }
      }
    );
  }

  submitFeedback(){
    this.feedbackResponse.feedbackReply;
    console.log("PRINT BEAN 1 - "+this.feedbackResponse.rating);
    console.log("PRINT BEAN 2 - "+this.feedbackResponse.suggestionType);
    console.log("PRINT BEAN 3 - "+this.feedbackResponse.feedbackText);
    console.log("PRINT BEAN 4 - "+this.feedbackResponse.feedbackReply);
    this.feedbackResponse.adminId = 2;
    this.feedbackService.replyFeedback(this.feedbackResponse).subscribe(
      (response) => {

        if (response && response.status == "00") {
          this.router.navigate(['/reply']);
          alert('Admin Reply Added successfully');
        }
        console.log("PRINT BEAN RESPONSE - "+response);
        if(this.feedbackResponse.feedbackReply === ''){
          this.enableSubmit = false;
        }
      }
    );
  }
}
