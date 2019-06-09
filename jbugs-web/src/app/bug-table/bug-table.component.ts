import {Component, EventEmitter, Input, OnChanges, OnInit, Output, TemplateRef, ViewChild} from '@angular/core';
import {Bug} from "../bug-model/bug-table";
import {
  MatButton,
  MatDialog,
  MatDialogConfig,
  MatPaginator,
  MatSort,
  MatTableDataSource,
  PageEvent
} from "@angular/material";
import {ViewBugComponent} from "../view-bug/view-bug.component";
import {BugService} from "../services/bug-service/bug.service";
import {ViewBugService} from "../services/view-bug-service/view-bug.service";
import {AddBugComponent} from "../add-bug/add-bug.component";
import {BugSublist} from "../bug-model/bug-sublist";
import {version} from "punycode";



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


  public displayedColumns: string[] = ['title', 'target_date','status','severity', 'createdByUser','assignedTo', 'actions'];

  private bugSetter: Bug;
  public bugs : Bug[];
  public sortedBugs : boolean = false;

  changedBug : Bug;

  loadComponent = false;

  transitions : String[];

  sortBy : string;
  filterBy: string;
  pageNumber : number;
  pageSize: number;

  @Output()
  page: EventEmitter<PageEvent>;

  @Input()
  pageSizeOptions: number[];

  public dataSource : any;
  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {

    this.pageSizeOptions = [5,10, 25];

    this.getAllBugs();

    console.log(this.sortedBugs);

    this.dialogConfig = new MatDialogConfig();
  }

  getAllBugs(){
    this.bugService.getAllBugs().subscribe(
      bugs => {
        this.bugs = bugs;
        this.dataSource =  new MatTableDataSource<Bug>(this.bugs);
        this.dataSource.paginator = this.paginator;
      }
    );

  }


  getSortedBugsList(newBugSubList : BugSublist){
    this.bugService.getSublist(newBugSubList).subscribe(
      newBugList => {
        this.bugs = newBugList;
        console.log(this.bugs);

        this.dataSource =  new MatTableDataSource<Bug>(newBugList);
        this.dataSource.paginator = this.paginator;
      }
    );

  }


  addBug(){
    this.dialogConfigSetup();
    this.dialog.open(AddBugComponent, this.dialogConfig);
    this.getAllBugs();
  }

  viewBugPopUp(component: TemplateRef<ViewBugComponent>, title: string, descr: string, version: string, targetDate: string,
               status: string, fixedVersion: string, severity: string, createdByUser: string,
               assignedTo: string){
    this.loadComponent = true;
    this.dialogConfigSetup();
    this.dialog.open(component, this.dialogConfig);
    this.setBug(title, descr, version, targetDate, status, fixedVersion, severity, createdByUser, assignedTo);
    this.viewBugServ.viewBug(this.bugSetter);
  }

  private setBug(title: string, descr: string, version: string, targetDate: string, status: string,
                    fixedVersion: string, severity: string, createdByUser: string, assignedTo: string){
    this.bugSetter = {
      id: 0,
      title: title,
      description: descr,
      version: version,
      targetDate: targetDate,
      status: status,
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


  applyChanges(field : string, value : string, pageIndex : number, pageSize : number){
    var newBugSublist : BugSublist = {field, value, pageNumber: pageIndex, pageSize};

    this.sortBy = field;
    this.filterBy = value;
    this.pageNumber = pageIndex;
    this.pageSize = pageSize;

    this.getSortedBugsList(newBugSublist);
    this.sortedBugs = true;
  }



  applyChangesNext(){
    this.paginator.nextPage();

    var nextPageIndex = this.pageNumber + 1;
    console.log(this.pageNumber+ " "+ nextPageIndex);

    (<HTMLInputElement> document.getElementById("prevbtn")).disabled = false;

    if(this.bugs.length == this.pageSize){
      this.applyChanges(this.sortBy, this.filterBy, nextPageIndex, this.pageSize);
    }else {
      (<HTMLInputElement> document.getElementById("nextbtn")).disabled = true;
    }

  }

  applyChangesPrev(){
    this.paginator.previousPage();

    var prevPageIndex = this.pageNumber - 1;

    (<HTMLInputElement> document.getElementById("nextbtn")).disabled = false;

    var prevPageIndex = this.pageNumber - 1;

    (<HTMLInputElement> document.getElementById("nextbtn")).disabled = false;

    if(prevPageIndex >= 0){
      this.applyChanges(this.sortBy, this.filterBy, prevPageIndex, this.pageSize);
    }else {
      (<HTMLInputElement> document.getElementById("prevbtn")).disabled = true;
    }

  }

  //get possible transitions
  openMenu(status: string){
    console.log("BUG STATUS"+status);
    this.transitions = [];

    this.bugService.getPossibleTransitions(status).subscribe(
      res => {
        console.log(res);
        this.transitions = res;
        console.log(this.transitions);
      }
    );
  }



  // setTransition(id : number, title: string, description: string, version: string, targetDate: string, status: string,
  //               fixedVersion: string, severity: string, createdByUser: string, assignedTo: string){
  //
  //   var viewBug : Bug = { id, title, description, version , targetDate, status, fixedVersion, severity, createdByUser, assignedTo};
  //
  //   this.bugService.setStatus(viewBug).subscribe(
  //     res => {
  //       this.changedBug = res;
  //       console.log(viewBug);
  //       this.transitions = null;
  //     }
  //   );

  //   if(this.sortedBugs == false){
  //     this.getAllBugs();
  //   }else {
  //     this.applyChanges(this.sortBy, this.filterBy, this.pageNumber, this.pageSize);
  //   }
  //
  // }


  // keyDownFunction(event, string : string) {
  //   if(event.keyCode == 13) {
  //     console.log('you just clicked enter'+string);
  //     this.applyChanges('title', string, this.pageNumber, this.pageSize);
  //   }
  // }


  // onPaginateChange(event){
  //   console.log(JSON.stringify("Current page index: " + event.pageSize));
  //   if (this.sortedBugs == true) {
  //     this.applyChanges(this.sortBy, this.filterBy, this.pageNumber, event.pageSize);
  //   } else {
  //     this.getAllBugs();
  //   }
  // }


}
