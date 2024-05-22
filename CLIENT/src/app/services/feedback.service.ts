import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { FeedbackRequestBean } from '../models/feedbackRequestBean';
import {FeedbackResponseBean} from "../models/feedbackResponseBean";

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  private baseUrl = 'http://localhost:6565/feedback';

  constructor(public httpClient: HttpClient,
  ) { }

  saveFeedback(feedbackRequestBean: FeedbackRequestBean): Observable<any> {
    const url = `${this.baseUrl}/submit`;
    const httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
    return this.httpClient.post<any>(url, feedbackRequestBean, {
      headers: httpHeaders,
      responseType: 'json'
    });
  }

  getList(searchParamMap: Map<string, string>): Observable<any> {
    const url = `${this.baseUrl}/`;
    let httpParams = this.getDataTableHttpParam(searchParamMap);
    const httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.get(url, {
      headers: httpHeaders,
      params: httpParams,
      responseType: 'json'
    });
  }

  getDataTableHttpParam(searchMap1: Map<string, string>): HttpParams {
    let httpParams = new HttpParams();
    searchMap1.forEach((key: string, value: string) => {
      httpParams = httpParams.set(value, key);
    });
    return httpParams;
  }

  getFeedback(id: any): Observable<any> {
    const url = `${this.baseUrl}/${id}`;
    const httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.get(url, {
      headers: httpHeaders,
      responseType: 'json'
    });
  }

  deleteFeedback(id: any): Observable<any> {
    const url = `${this.baseUrl}/${id}`;
    const httpHeaders = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpClient.delete(url, {
      headers: httpHeaders,
      responseType: 'json'
    });
  }

  replyFeedback(feedbackResponseBean: FeedbackResponseBean): Observable<any> {
    const url = `${this.baseUrl}/reply`;
    const httpHeaders = new HttpHeaders({'Content-Type': 'application/json'});
    return this.httpClient.post<any>(url, feedbackResponseBean, {
      headers: httpHeaders,
      responseType: 'json'
    });
  }

}
