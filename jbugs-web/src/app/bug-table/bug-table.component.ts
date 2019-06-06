import {Component, EventEmitter, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {Bug} from "../bug-model/bug-table";
import {MatDialog, MatDialogConfig, MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {ViewBugComponent} from "../view-bug/view-bug.component";
import {BugService} from "../services/bug-service/bug.service";
import {ViewBugService} from "../services/view-bug-service/view-bug.service";
import {AddBugComponent} from "../add-bug/add-bug.component";


export interface Dates  {
  data : Date
}

@Component({
  selector: 'app-bug-table',
  templateUrl: './bug-table.component.html',
  styleUrls: ['./bug-table.component.scss']
})
export class BugTableComponent implements OnInit {

  @Output() bugEmitter : EventEmitter<Bug>= new EventEmitter<Bug>();

  private dialogConfig;


  constructor( private dialog: MatDialog,
               private bugService: BugService,
               private viewBugServ: ViewBugService) { }

  public displayedColumns: string[] = ['title', 'target_date','status',
    'severity', 'createdByUser','assignedTo', 'actions'];

  private bugSetter: Bug;
  public bugs : Bug[];

  loadComponent = false;

  length: any;
  pageSize: any;


  public dataSource : any;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit() {
    this.setAllBugs();
    this.dialogConfig = new MatDialogConfig();

  }

  private setAllBugs(){
    this.bugService.getAllBugs().subscribe(
      bugs => {
        this.bugs = bugs;
        this.dataSource =  new MatTableDataSource<Bug>(this.bugs);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
    );
  }

  addBug(){
    this.dialogConfigSetup();
    this.dialog.open(AddBugComponent, this.dialogConfig);
  }

  viewBugPopUp(component: TemplateRef<ViewBugComponent>, title: string, descr: string, version: string, targetDate: string,
               status: string, changedStatus: string, fixedVersion: string, severity: string, createdByUser: string,
               assignedTo: string){
    this.loadComponent = true;
    this.dialogConfigSetup();
    this.dialog.open(component, this.dialogConfig);
    this.setBug(title, descr, version, targetDate, status, changedStatus, fixedVersion, severity, createdByUser, assignedTo);
    this.viewBugServ.viewBug(this.bugSetter);
  }

  private setBug(title: string, descr: string, version: string, targetDate: string, status: string, changedStatus: string,
                    fixedVersion: string, severity: string, createdByUser: string, assignedTo: string){
    this.bugSetter = {
      title: title,
      description: descr,
      version: version,
      targetDate: targetDate,
      status: status,
      changed_status: changedStatus,
      fixedVersion: fixedVersion,
      severity: severity,
      createdByUser: createdByUser,
      assignedTo: assignedTo
    };
  }

  private dialogConfigSetup(){
    this.dialogConfig.disableClose= false;
    this.dialogConfig.autoFocus = true;
    this.dialogConfig.width = "50%";
  }


  onPaginateChange(event){
    alert(JSON.stringify("Current page index: " + event.pageIndex + " Page Size "+event.pageSize + " Length "+event.length));
  }

}
