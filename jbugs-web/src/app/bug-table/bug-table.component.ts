import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Bug} from "../bug-model/bug-table";
import {MatDialog, MatDialogConfig, MatPaginator, MatTableDataSource} from "@angular/material";
import {ViewBugComponent} from "../view-bug/view-bug.component";
import {BugService} from "../services/bug-service/bug.service";
import {ViewBugService} from "../services/view-bug-service/view-bug.service";

@Component({
  selector: 'app-bug-table',
  templateUrl: './bug-table.component.html',
  styleUrls: ['./bug-table.component.scss']
})
export class BugTableComponent implements OnInit {

  constructor( private dialog: MatDialog,
               private bugService: BugService,
               private viewBugServ: ViewBugService) { }

  public displayedColumns: string[] = ['title', 'target_date','status',
    'severity', 'createdByUser','assignedTo', 'actions'];

  private bugSetter: Bug;
  public bugs : Bug[];

  loadComponent = false;
  private dialogConfig;

  public dataSource : any;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.setAllBugs();
    this.dialogConfig = new MatDialogConfig();

    // this.viewBugServ.currentBug.subscribe(newBug => this.bugSetter = newBug)
  }

  private setAllBugs(){
    this.bugService.getAllBugs().subscribe(
      bugs => {
        this.bugs = bugs;
        this.dataSource =  new MatTableDataSource<Bug>(this.bugs);
        this.dataSource.paginator = this.paginator;
      }
    );
  }

  addBug(){
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


}
