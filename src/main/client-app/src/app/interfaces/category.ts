import IDatabaseObject from "./databaseObject";

export default interface ICategory extends IDatabaseObject {
    name: string;
    description: string | undefined;
}