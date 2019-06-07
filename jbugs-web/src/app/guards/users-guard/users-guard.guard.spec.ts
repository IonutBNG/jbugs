import { TestBed, async, inject } from '@angular/core/testing';

import { UsersGuardGuard } from './users-guard.guard';

describe('UsersGuardGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UsersGuardGuard]
    });
  });

  it('should ...', inject([UsersGuardGuard], (guard: UsersGuardGuard) => {
    expect(guard).toBeTruthy();
  }));
});
