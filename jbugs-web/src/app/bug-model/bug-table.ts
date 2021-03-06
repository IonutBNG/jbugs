
export interface Bug {
  id : number,
  title : string,
  description : string,
  version : string,
  targetDate: string,
  status : string,
  fixedVersion: string,
  severity: string,
  createdByUser : string,
  assignedTo : string
}
