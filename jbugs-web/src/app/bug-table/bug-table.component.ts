import {Component, EventEmitter, Input, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {Bug} from "../bug-model/bug-table";
import {MatDialog, MatDialogConfig, MatPaginator, MatTableDataSource} from "@angular/material";
import {ViewBugComponent} from "../view-bug/view-bug.component";
import {BugService} from "../services/bug-service/bug.service";
import {User} from "../user-model/user-table";
import {ComponentType} from "@angular/cdk/portal";

@Component({
  selector: 'app-bug-table',
  templateUrl: './bug-table.component.html',
  styleUrls: ['./bug-table.component.scss']
})
export class BugTableComponent implements OnInit {

  @Output() bugEmitter : EventEmitter<Bug>= new EventEmitter<Bug>();

  private dialogConfig;


  constructor( private dialog: MatDialog,
               private bugService: BugService) { }

  public displayedColumns: string[] = ['title', 'target_date','status',
    'severity', 'createdByUser','assignedTo', 'actions'];

  public bugs : Bug[];

  loadComponent = false;

  public dataSource : any;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {


    this.bugService.getAllBugs().subscribe(
      bugs => {
        this.bugs = bugs;
        this.dataSource =  new MatTableDataSource<Bug>(this.bugs);
        this.dataSource.paginator = this.paginator;
      }
    )

    this.dialogConfig = new MatDialogConfig();
  }

  addBug(){
  }

  viewBugPopUp(component: TemplateRef<ViewBugComponent>){
    this.loadComponent = true;
    this.dialogConfigSettup();
    this.dialog.open(component, this.dialogConfig)
  }

  private dialogConfigSettup(){
    this.dialogConfig.disableClose= true;
    this.dialogConfig.autoFocus = true;
    this.dialogConfig.width = "50%";
  }


}
