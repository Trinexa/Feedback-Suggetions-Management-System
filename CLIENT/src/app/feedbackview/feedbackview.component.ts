import {Component, OnInit} from '@angular/core';
import {FormsModule} from "@angular/forms";
import {FeedbackResponseBean} from "../models/feedbackResponseBean";
import {ActivatedRoute, Router} from "@angular/router";
import {FeedbackService} from "../services/feedback.service";

@Component({
  selector: 'app-feedbackview',
  standalone: true,
    imports: [
        FormsModule
    ],
  templateUrl: 'feedbackview.component.html',
  styleUrl: './feedbackview.component.css'
})
export class FeedbackviewComponent implements OnInit{

  feedbackResponse: FeedbackResponseBean = new FeedbackResponseBean();
  id: any;

  constructor(
    private feedbackService: FeedbackService,
    private router: Router,
    private route: ActivatedRoute,
  ) {
    this.route.params.subscribe(params => this.id = params['id']);
  }

  collapsed = false;

  toggleCollapsed(): void {
    this.collapsed = !this.collapsed;
  }

  ngOnInit(): void {
    this.getFeedback(this.id);
  }

  getFeedback(id: number) {
    debugger;
    this.feedbackService.getFeedback(id).subscribe(
      (response: FeedbackResponseBean) => {
        debugger;
        this.feedbackResponse = response;
      }
    );
  }


}
