import {Injectable} from "@angular/core";
import {BackendService} from "../backend-service/backend.service";
import {Observable} from "rxjs";
import {Bug} from "../../bug-model/bug-table";
import {NewBugModel} from "../../bug-model/new-bug";
<<<<<<< HEAD
import {ViewBugComponent} from "../../view-bug/view-bug.component";
import {BugTableComponent} from "../../bug-table/bug-table.component";
=======
>>>>>>> 1020c8349eb8d14ececc6f5cac233fb196363d12
import {BugSublist} from "../../bug-model/bug-sublist";

@Injectable({
  providedIn: 'root'
})
export class BugService {

  constructor(private backendService: BackendService) { }

  public getAllBugs(): Observable<Bug[]>{
    return this.backendService.get("/jbugs/jbugs-api/bug/bugs");
  }

  public addNewBug(newBug: NewBugModel) : Observable<NewBugModel> {
   return this.backendService.post("/jbugs/jbugs-api/bug/add-new-bug", newBug);
  }

  public getPossibleTransitions(status : String) : Observable<String[]> {
    return this.backendService.get("/jbugs/jbugs-api/bug/status-transition/"+status);
  }

  public setStatus(statusBug : Bug) : Observable<Bug> {
    return this.backendService.put("jbugs/jbugs-api/bug/set-status", statusBug);
  }

  public getSeverityValues() : Observable<String[]> {
    return this.backendService.get("jbugs/jbugs-api/bug/get-severity");
  }

  public getSublist(bugsublist: BugSublist): Observable<Bug[]> {
    return this.backendService.put("jbugs/jbugs-api/bug/filtered", bugsublist);
  }

<<<<<<< HEAD
=======

>>>>>>> 1020c8349eb8d14ececc6f5cac233fb196363d12
}
