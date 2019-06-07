import {Component, EventEmitter, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {Bug} from "../bug-model/bug-table";
import {MatDialog, MatDialogConfig, MatPaginator, MatSort, MatTableDataSource, PageEvent} from "@angular/material";
import {ViewBugComponent} from "../view-bug/view-bug.component";
import {BugService} from "../services/bug-service/bug.service";
import {AddBugComponent} from "../add-bug/add-bug.component";


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

  public displayedColumns: string[] = ['title', 'target_date','status','severity', 'createdByUser','assignedTo', 'actions'];

  public bugs : Bug[];

  loadComponent = false;

  length: any;
  pageSize: any;

  transitions : String[];

  @Output()
  page: EventEmitter<PageEvent>

  public dataSource : any;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit() {

    this.bugService.getAllBugs().subscribe(
      bugs => {
        this.bugs = bugs;
        this.dataSource =  new MatTableDataSource<Bug>(bugs);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;

      }
    );

    console.log(this.page);
    this.dialogConfig = new MatDialogConfig();
  }

  addBug(){
    this.dialogConfigSettup();
    this.dialog.open(AddBugComponent, this.dialogConfig);
    this.ngOnInit();
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


  applyChanges(sortvalue : string, filtervalue : string, pageIndex : number, pageSize : number){
      console.log(sortvalue, filtervalue, pageIndex, pageSize );
  }


  openMenu(status: string){
    console.log("BUG STATUS"+status);

    this.bugService.getPossibleTransitions(status).subscribe(
      res => {
        console.log(res);
        this.transitions = res;
        console.log(this.transitions);
      }
    );
  }


  setTransition(id : number, title: string, description: string, version: string, targetDate: string, status: string,
                fixedVersion: string, severity: string, createdByUser: string, assignedTo: string){

    var viewBug : Bug = { id, title, description, version , targetDate, status, fixedVersion, severity, createdByUser, assignedTo};

    this.bugService.setStatus(viewBug).subscribe(
      res => {
        console.log(res);
        console.log(viewBug);
      }
    );

    this.ngOnInit();
  }

}
