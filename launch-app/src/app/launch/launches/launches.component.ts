import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {Launch} from '../launch';
import {Observable} from 'rxjs';
import {LaunchReactiveService} from '../launch-reactive.service';
import {LaunchBlockingService} from '../launch-blocking.service';

@Component({
  selector: 'app-launches',
  templateUrl: './launches.component.html'
})
export class LaunchesComponent {
  launchArray: Launch[] = [];
  selectedLaunch: Launch;
  mode: string;
  pagination: boolean;
  page: number;
  size: number;

  constructor(private launchReactiveService: LaunchReactiveService, private launchBlockingService: LaunchBlockingService, private cdr: ChangeDetectorRef) {
    this.mode = 'reactive';
    this.pagination = true;
    this.page = 0;
    this.size = 50;
  }

  resetData(): void {
    this.launchArray = [];
    this.selectedLaunch = null;
  }

  requestLaunchStream(): void {
    this.resetData();
    let quoteObservable: Observable<Launch>;
    if (this.pagination === true) {
      quoteObservable = this.launchReactiveService.getQuoteStream(this.page, this.size);
    } else {
      quoteObservable = this.launchReactiveService.getQuoteStream();
    }
    quoteObservable.subscribe(quote => {
      this.launchArray.push(quote);
      this.cdr.detectChanges();
    });
  }

  requestLaunchBlocking(): void {
    this.resetData();
    if (this.pagination === true) {
      this.launchBlockingService.getQuotes(this.page, this.size)
        .subscribe(q => this.launchArray = q);
    } else {
      this.launchBlockingService.getQuotes()
        .subscribe(q => this.launchArray = q);
    }
  }

  onSelect(launch: Launch): void {
    this.selectedLaunch = launch;
    this.cdr.detectChanges();
  }

}
