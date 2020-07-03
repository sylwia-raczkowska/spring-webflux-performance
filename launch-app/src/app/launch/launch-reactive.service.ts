import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Launch} from './launch';

@Injectable({
  providedIn: 'root'
})
export class LaunchReactiveService {

  url: string = 'http://localhost:8080/launches-reactive';
  urlPaged: string = 'http://localhost:8080/launches-reactive-paged';

  getQuoteStream(page?: number, size?: number): Observable<Launch> {
    return new Observable<Launch>((observer) => {
      let url = this.url;
      if (page != null) {
        url = this.urlPaged + '?page=' + page + '&size=' + size;
      }
      const eventSource = new EventSource(url);
      eventSource.onmessage = (event) => {
        console.debug('Received event: ', event);
        const json = JSON.parse(event.data);
        observer.next(new Launch(json['id'], json['name'], json['description']));
      };
      eventSource.onerror = (error) => {
        // readyState === 0 (closed) means the remote source closed the connection,
        // so we can safely treat it as a normal situation. Another way
        // of detecting the end of the stream is to insert a special element
        // in the stream of events, which the client can identify as the last one.
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
