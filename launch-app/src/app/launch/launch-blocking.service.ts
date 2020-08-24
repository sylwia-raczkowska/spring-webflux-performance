import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Launch, LaunchResponse} from './launch';

@Injectable({
  providedIn: 'root'
})
export class LaunchBlockingService {

  url: string = 'http://localhost:8080/launches-blocking';

  constructor(private http: HttpClient) {}

  getBlockingLaunches(page?: number): Observable<LaunchResponse> {
    let url = this.url;
    if (page != null) {
      url = this.url + '?page=' + page + '&size=' + 50;
    }
    return this.http.get<LaunchResponse>(url);
  }
}
