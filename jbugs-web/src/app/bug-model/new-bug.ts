export interface NewBugModel {
  title : string;
  description : string;
  version : string;
  fixedVersion : string;
  targetDate : Date;
  severity : string;
  createdByUser: string;
  assignedTo : string;
}
