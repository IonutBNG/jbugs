import {Component, OnInit} from '@angular/core';
import {Bug} from "../bug-model/bug-table";
import {ViewBugService} from "../services/view-bug-service/view-bug.service";

@Component({
  selector: 'app-view-bug',
  templateUrl: './view-bug.component.html',
  styleUrls: ['./view-bug.component.scss']
})
export class ViewBugComponent implements OnInit {

  constructor(private viewBugServ: ViewBugService) {

  }

  private bug: Bug;
  private title: string;
  private description: string;
  private version: string;
  private targetDate: string;
  private status: string;
  private fixedVersion: string;
  private severity: string;
  private createdBy: string;
  private assignedTo: string;

  ngOnInit() {
    this.setBug();
  }

  private setBug(){
    this.viewBugServ.currentBug.subscribe(newBug => this.bug = newBug);
    this.title = this.bug.title;
    this.description = this.bug.description;
    this.version = this.bug.version;
    this.targetDate = this.bug.targetDate;
    this.status = this.bug.status;
    this.fixedVersion = this.bug.fixedVersion;
    this.severity = this.bug.severity;
    this.createdBy = this.bug.createdByUser;
    this.assignedTo = this.bug.assignedTo;
  }


  private closeDialogSuccess(message){
    this.close();
  }

  private close(){
    this.close();
  }


}
