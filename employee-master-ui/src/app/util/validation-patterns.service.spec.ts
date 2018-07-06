import { TestBed, inject } from '@angular/core/testing';

import { ValidationPatternsService } from './validation-patterns.service';

describe('ValidationPatternsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ValidationPatternsService]
    });
  });

  it('should be created', inject([ValidationPatternsService], (service: ValidationPatternsService) => {
    expect(service).toBeTruthy();
  }));
});
