import {Injectable} from "@angular/core";
import {BackendService} from "../backend-service/backend.service";
import {Observable} from "rxjs";
import {Bug} from "../../bug-model/bug-table";

@Injectable({
  providedIn: 'root'
})
export class BugService {

  constructor(private backendService: BackendService) { }

  public getAllBugs(): Observable<Bug[]>{
    return this.backendService.get("/jbugs/jbugs-api/bug/bugs");
  }

}
