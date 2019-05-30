import { TestBed } from '@angular/core/testing';

import { UserEmitterService } from './user-emitter.service';

describe('UserEmitterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UserEmitterService = TestBed.get(UserEmitterService);
    expect(service).toBeTruthy();
  });
});
