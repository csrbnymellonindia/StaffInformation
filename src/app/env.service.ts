import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EnvService {
  get baseUrl():string{
    return this.baseUrl
  }
  baseurl:string = 'http://localhost:8080';

  constructor() { }
}
