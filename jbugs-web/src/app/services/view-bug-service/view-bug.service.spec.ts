import { TestBed } from '@angular/core/testing';

import { ViewBugService } from './view-bug.service';

describe('ViewBugService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ViewBugService = TestBed.get(ViewBugService);
    expect(service).toBeTruthy();
  });
});
