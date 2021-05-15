import {Project} from './Project';
import {User} from './User';
import {UserRole} from './UserRole';

export class Role {
	id: number;
	project: Project;
	user: User;
	role: UserRole;
}
