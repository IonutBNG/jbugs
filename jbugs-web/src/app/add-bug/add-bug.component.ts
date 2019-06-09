import {Component, OnInit, Optional} from '@angular/core';
import {MatDialogRef} from "@angular/material";
import {User} from "../user-model/user-table";
import {UserService} from "../services/user-service/user.service";
import {NewBugModel} from "../bug-model/new-bug";
import {BugService} from "../services/bug-service/bug.service";
import {ToastrService} from "ngx-toastr";
import {AuthService} from "../services/auth-service/auth.service";
import {BugEmmitterService} from "../services/bug-emitter-service/bug-emmitter.service";

@Component({
  selector: 'app-add-bug',
  templateUrl: './add-bug.component.html',
  styleUrls: ['./add-bug.component.scss']
})
export class AddBugComponent implements OnInit {

  public users: User[];

  public counter : number = 0;

  public severity : String[];

  constructor(@Optional() private dialogRef: MatDialogRef<AddBugComponent>,
              private userService: UserService,
              private bugService: BugService,
              private toast: ToastrService,
              private authService : AuthService,
              private bugEmitterService : BugEmmitterService) { }

   public bugAddedSucces : boolean;

  token = this.authService.getDecodedToken();

  loggedUser : string;

  ngOnInit() {

    this.userService.getUsersWithBugManagement().subscribe(
      users => {
        this.users = users;
      }
    );


  this.bugService.getSeverityValues().subscribe(
      severity => {
        this.severity = severity;
        console.log(severity);
      }
  );

  this.loggedUser = this.token;
  console.log('User '+ this.loggedUser);

  }

  addNewBug(title: string, description: string, version: string, fixedVersion: string, targetDate, severity: string, createdByUser : string, assignedTo : string){
      var newBug : NewBugModel = { title, description, version, fixedVersion, targetDate, severity, createdByUser, assignedTo};
      console.log(newBug);
      this.bugService.addNewBug(newBug).subscribe(
        res => { console.log(res);
          console.log(newBug);
        this.okResponse(res);},
        err => {console.log(err)}
      );

      this.bugService.getAllBugs().subscribe();
  }

  okResponse(res) {
    if (res.status === undefined) {
      this.bugAddedSucces = false;
      this.closeDialogError(res.message);
    }
    else {
      this.bugAddedSucces = true;
      this.closeDialogSuccess(res.status);
    }
  }

  private closeDialogError(message){
    this.toast.error(message, "Error");
  }


  private closeDialogSuccess(message){
    this.dialogRef.close();
    this.bugEmitterService.changeEmmiter();
    this.toast.success(message, "Success");
  }


  private close(){
    this.dialogRef.close();
  }

  onSearchChange(searchValue : string) {
    this.counter = searchValue.length + 1;
    console.log(searchValue);
  }


}
