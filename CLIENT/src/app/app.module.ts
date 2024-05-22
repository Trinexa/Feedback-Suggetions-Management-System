import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { ChartModule } from '@progress/kendo-angular-charts';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [],
  imports: [
    BrowserModule,
    ChartModule,
    AppComponent
  ],
  providers: []
})
export class AppModule { }
