import IDatabaseObject from "./databaseObject";
import IPersonalData from "./personal-data";

export default interface IAccount extends IDatabaseObject, IPersonalData {
    login: string ,
    password: string,
    nickname: string,
    email: string,
    role: string,
} 