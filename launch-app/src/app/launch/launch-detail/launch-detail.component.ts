import {Component, Input, OnInit} from '@angular/core';
import {Launch} from '../launch';

@Component({
  selector: 'app-launch-detail',
  templateUrl: './launch-detail.component.html'
})
export class LaunchDetailComponent implements OnInit {
  @Input() launch: Launch;

}
