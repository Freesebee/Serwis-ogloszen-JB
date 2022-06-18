import IDatabaseObject from "./databaseObject";

export default interface IAccount extends IDatabaseObject {
    nickname: string,
    email: string
} 