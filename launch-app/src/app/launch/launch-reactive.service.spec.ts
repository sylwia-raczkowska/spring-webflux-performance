import { TestBed } from '@angular/core/testing';

import { LaunchReactiveService } from './launch-reactive.service';

describe('LaunchReactiveService', () => {
  let service: LaunchReactiveService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LaunchReactiveService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
