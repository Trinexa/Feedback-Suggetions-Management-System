import { Component, OnInit } from '@angular/core';
import { FeedbackRequestBean } from '../models/feedbackRequestBean';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { SearchBean } from '../models/searchBean';
import { FeedbackResponseBean } from '../models/feedbackResponseBean';
import { Router } from '@angular/router';
import {FeedbackService} from "../services/feedback.service";

@Component({
  selector: 'app-feedbackreply',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
  ],
  templateUrl: './feedbackreply.component.html',
  styleUrl: './feedbackreply.component.css'
})
export class FeedbackreplyComponent implements OnInit{
  public chartType = 'pie';
  public searchModel: SearchBean = new SearchBean;
  public feedbackResponseBean: FeedbackResponseBean[] | undefined;
  searchParamMap: Map<string, any> = new Map();

  public chartOptions = {

    responsive: true

  };


  public chartLabels = ['Requests', 'Suggestions'];


  public chartData = [

    { data: [10, 5], label: 'Feedback Types' }

  ];
  constructor(
    public feedbackService: FeedbackService,
    public router: Router
  ){}

  ngOnInit(): void {
    this.getQueue(false);
  }

  getQueue(event: boolean) {
    if(event === true){
      this.searchParamMap = this.getSearchString(this.searchParamMap, this.searchModel);
    }

    this.feedbackService.getList(this.searchParamMap)
      .subscribe({
        next: (response) => {
          this.feedbackResponseBean = response;
        }, error: err => {
          console.log(err.error['message']);
        }
      });
  }

  getSearchString(searchParamMap: Map<string, any>, searchModelMyQueue: SearchBean): Map<string, string> {
    if (searchModelMyQueue.userId) {
      searchParamMap.set('userId', searchModelMyQueue.userId);
    }
    if (searchModelMyQueue.suggestionType) {
      searchParamMap.set('suggestionType', searchModelMyQueue.suggestionType);
    }
    return searchParamMap;
  }

  view(id: any): void {

    this.router.navigate(
      [
        '/view',
        id,
      ],

    );
  }

  reply(id: any): void {

    this.router.navigate(
      [
        '/reply',
        id,
      ],

    );
  }

  delete(id: any): void {

    this.feedbackService.deleteFeedback(id).subscribe(
      (response) => {
        this.getQueue(false);
      }
    );
  }

}
