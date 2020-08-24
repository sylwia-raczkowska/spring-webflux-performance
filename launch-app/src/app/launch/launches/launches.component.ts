import {ChangeDetectorRef, Component} from '@angular/core';
import {Launch} from '../launch';
import {LaunchReactiveService} from '../launch-reactive.service';
import {LaunchBlockingService} from '../launch-blocking.service';

@Component({
  selector: 'app-launches',
  templateUrl: './launches.component.html',
  styleUrls: ['./launches.component.css']
})
export class LaunchesComponent {
  launchArray: Launch[] = [];
  selectedLaunch: Launch;
  mode: string;
  page: number;

  constructor(private launchReactiveService: LaunchReactiveService, private launchBlockingService: LaunchBlockingService, private cdr: ChangeDetectorRef) {
    this.page = 0;
  }

  resetData(): void {
    this.launchArray = [];
    this.selectedLaunch = null;
  }

  requestLaunchStream(): void {
    this.resetData();
    this.launchReactiveService.getReactiveLaunches(this.page).subscribe(launch => {
      this.launchArray.push(launch);
      this.cdr.detectChanges();
    });
  }

  requestLaunchBlocking(): void {
    this.resetData();
    this.launchBlockingService.getBlockingLaunches(this.page)
      .subscribe(launchResponse  => {
        this.launchArray = launchResponse.content;
      });
  }

  onSelect(launch: Launch): void {
    this.selectedLaunch = launch;
    this.cdr.detectChanges();
  }

}
