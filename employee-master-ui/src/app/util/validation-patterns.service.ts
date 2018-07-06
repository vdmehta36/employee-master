import { Injectable } from '@angular/core';

@Injectable()
export class ValidationPatternsService {

  constructor() { }

  incrementalMatcher(text: string): string {
    const matchers = [
      {regex: '^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$', message: 'Minimum 8 Characters, 1 letter, 1 number'},
      {regex: '^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$', message: 'Alteast one special character'},
      {regex: '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$', message: 'Atleast 1 uppercase, 1 lowercase letters'},
      // {regex: '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,}', message: ''},
      // {regex: '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{8,10}', message: ''}
    ];
    const result = matchers.map((item) => {
      const regex: RegExp = new RegExp(item.regex);
      return text.match(regex.compile());
    });
    console.log(result);
    return '';
  }

}
