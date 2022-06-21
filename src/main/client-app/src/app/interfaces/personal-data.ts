import IDatabaseObject from "./databaseObject";

export default interface IPersonalData extends IDatabaseObject {
    name: string,
    surname: string,
    city: string,
    street: string,
}