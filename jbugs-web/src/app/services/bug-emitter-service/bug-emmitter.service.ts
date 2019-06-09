import {EventEmitter, Injectable, Output} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BugEmmitterService {

  constructor() { }

  @Output()
  changeEmitter: EventEmitter<boolean> = new EventEmitter<boolean>();

  changeEmmiter(){
    this.changeEmitter.next(true);
  }
}
