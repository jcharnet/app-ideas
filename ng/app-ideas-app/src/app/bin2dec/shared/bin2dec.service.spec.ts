import { TestBed } from '@angular/core/testing';

import { Bin2DecService } from './bin2dec.service';

describe('Bin2DecService', () => {
  let service: Bin2DecService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Bin2DecService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
