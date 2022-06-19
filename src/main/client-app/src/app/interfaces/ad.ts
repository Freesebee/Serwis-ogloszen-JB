import IAccount from "./account";
import ICategory from "./category";
import IDatabaseObject from "./databaseObject";

export default interface IAd extends IDatabaseObject {
  title: string | undefined;
  content: string | undefined;
  city: string | undefined;
  street: string | undefined;
  approval: boolean | undefined;
  accountByIdAccount: IAccount | undefined;
  categoryByIdCategory: ICategory | undefined;
}
