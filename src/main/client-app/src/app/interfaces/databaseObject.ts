import IAccount from "./account";

export default interface IDatabaseObject {
    id: number | undefined;
    createdBy: IAccount | undefined;
    createdDate: Date | undefined;
    modifiedBy: IAccount | undefined;
    modifiedDate: Date | undefined;
}