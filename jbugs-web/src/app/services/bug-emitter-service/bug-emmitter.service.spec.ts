import { TestBed } from '@angular/core/testing';

import { BugEmmitterService } from './bug-emmitter.service';

describe('BugEmmitterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BugEmmitterService = TestBed.get(BugEmmitterService);
    expect(service).toBeTruthy();
  });
});
