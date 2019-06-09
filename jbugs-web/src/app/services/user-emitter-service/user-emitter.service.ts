import {EventEmitter, Injectable, Input, Output} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserEmitterService {

  constructor() { }

  @Output()
  changeEmitter: EventEmitter<boolean> = new EventEmitter<boolean>();

  doSomething(){
    this.changeEmitter.next(true);
  }
}
