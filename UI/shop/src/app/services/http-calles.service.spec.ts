import { TestBed } from '@angular/core/testing';

import { HttpCallesService } from './http-calles.service';

describe('HttpCallesService', () => {
  let service: HttpCallesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpCallesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
