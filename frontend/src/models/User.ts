import Role from "./Role";
import Authority from "./Authority";

export default interface User {
  id: string;
  firstName: string;
  lastName: string;
  email: string;
  role: Role;
}