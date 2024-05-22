import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule} from "@angular/forms";
import { HttpClientModule } from '@angular/common/http';
import { FeedbackreplyComponent } from './feedbackreply.component';

@NgModule({
  declarations: [FeedbackreplyComponent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule
  ]
})
export class FeedbackReplyModule { }
