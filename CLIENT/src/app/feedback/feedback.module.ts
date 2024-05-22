import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FeedbackComponent } from './feedback.component';
import {FormsModule} from "@angular/forms";
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [FeedbackComponent],
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule
  ]
})
export class FeedbackModule { }
