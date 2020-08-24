import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Launch} from './launch';

@Injectable({
  providedIn: 'root'
})
export class LaunchReactiveService {

  url: string = 'http://localhost:8081/launches-reactive';

  getReactiveLaunches(page?: number): Observable<Launch> {
    return new Observable<Launch>((observer) => {
      let url = this.url;
      if (page != null) {
        url = this.url + '?page=' + page + '&size=' + 50;
      }
      const eventSource = new EventSource(url);
      eventSource.onmessage = (event) => {
        const json = JSON.parse(event.data);
        observer.next(new Launch(json['id'], json['name'], json['description']));
      };
      eventSource.onerror = (error) => {
        if(eventSource.readyState === 0) {
          console.log('The stream has been closed by the server.');
          eventSource.close();
          observer.complete();
        } else {
          observer.error('EventSource error: ' + error);
        }
      };
    });
  }
}
