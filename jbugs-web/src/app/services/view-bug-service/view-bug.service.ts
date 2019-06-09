import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Bug} from "../../bug-model/bug-table";

@Injectable({
  providedIn: 'root'
})
export class ViewBugService {

  private bug: Bug;
  private bugSource = new BehaviorSubject(this.bug); //holds the value that needs to be shared between components
  currentBug = this.bugSource.asObservable();

  constructor() { }

  viewBug(newBug: Bug) {
    this.bugSource.next(newBug);
  }
}
