import { TestBed } from '@angular/core/testing';

import { PrecioFormatService } from './precio-format.service';

describe('PrecioFormatService', () => {
  let service: PrecioFormatService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrecioFormatService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
