import {Permission} from "./permission-model";

export interface Role{
  type: string,
  permissionDtoList: Permission[];
}
