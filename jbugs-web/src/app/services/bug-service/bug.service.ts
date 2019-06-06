import {Injectable} from "@angular/core";
import {BackendService} from "../backend-service/backend.service";
import {Observable} from "rxjs";
import {Bug} from "../../bug-model/bug-table";
import {NewBugModel} from "../../bug-model/new-bug";
import {ViewBugComponent} from "../../view-bug/view-bug.component";
import {BugTableComponent} from "../../bug-table/bug-table.component";

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

  public setStatus(statusBug : BugTableComponent) : Observable<BugTableComponent> {
    return this.backendService.put("jbugs/jbugs-api/bug/set-status", statusBug);
  }

  public getSeverityValues() : Observable<String[]> {
    return this.backendService.get("jbugs/jbugs-api/bug/get-severity");
  }
}
