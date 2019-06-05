import {Component, EventEmitter, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {Bug} from "../bug-model/bug-table";
import {MatDialog, MatDialogConfig, MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {ViewBugComponent} from "../view-bug/view-bug.component";
import {BugService} from "../services/bug-service/bug.service";
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
               private bugService: BugService) { }

  public displayedColumns: string[] = ['title', 'target_date','status',
    'severity', 'createdByUser','assignedTo', 'actions'];

  public bugs : Bug[];

  loadComponent = false;

  length: any;
  pageSize: any;


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
    this.dialogConfig = new MatDialogConfig();

  }


  addBug(){
    this.dialogConfigSettup();
    this.dialog.open(AddBugComponent, this.dialogConfig);
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


  onPaginateChange(event){
    alert(JSON.stringify("Current page index: " + event.pageIndex + " Page Size "+event.pageSize + " Length "+event.length));
  }

}
