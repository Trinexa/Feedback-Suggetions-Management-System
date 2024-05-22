import { Routes } from '@angular/router';
import {FeedbackComponent} from "./feedback/feedback.component";
import { FeedbackadminpanelComponent } from './feedbackadminpanel/feedbackadminpanel.component';
import { FeedbackreplyComponent } from './feedbackreply/feedbackreply.component';
import {LoginComponent} from "./login/login.component";
import {FeedbackviewComponent} from "./feedbackview/feedbackview.component";

export const routes: Routes = [
  {
    path:'',
    component: FeedbackComponent
  },
  {
    path:'admin/:id',
    component: FeedbackadminpanelComponent
  },
  {
    path:'reply',
    component: FeedbackreplyComponent
  },
  {
    path:'login',
    component: LoginComponent
  },
  {
    path:'reply/:id',
    component: FeedbackadminpanelComponent
  },
  {
    path:'view/:id',
    component: FeedbackviewComponent
  },
];
