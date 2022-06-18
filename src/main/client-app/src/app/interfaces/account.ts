import IDatabaseObject from "./databaseObject";

export default interface IAccount extends IDatabaseObject {
    nickName: string,
    email: string
} 