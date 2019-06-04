import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {MatDialogRef} from "@angular/material";
import {MatInputModule} from '@angular/material/input';
import {BugTableComponent} from "../bug-table/bug-table.component";
import {Bug} from "../bug-model/bug-table";
import {BugService} from "../services/bug-service/bug.service";

@Component({
  selector: 'app-view-bug',
  templateUrl: './view-bug.component.html',
  styleUrls: ['./view-bug.component.scss']
})
export class ViewBugComponent implements OnInit {

  public bugs : Bug[];

  constructor( ) {

  }

  ngOnInit() {

  }

  private closeDialogSuccess(message){
    this.close();
  }

  private close(){
    this.close();
  }


}
