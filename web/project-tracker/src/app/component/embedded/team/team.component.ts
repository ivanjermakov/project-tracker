import {Component, Input, OnInit} from '@angular/core';
import {Project} from '../../../dto/Project';
import {Role} from '../../../dto/Role';
import {RoleService} from '../../../service/role.service';
import {TokenProvider} from '../../../provider/token.provider';
import {UserRole} from '../../../dto/UserRole';
import {SetRole} from '../../../dto/SetRole';
import {AuthService} from '../../../service/auth.service';
import {User} from '../../../dto/User';
import {ErrorService} from '../../../service/error.service';

@Component({
	selector: 'app-team',
	templateUrl: './team.component.html',
	styleUrls: ['./team.component.scss']
})
export class TeamComponent implements OnInit {

	@Input()
	project: Project;

	members: Role[];

	userRole = UserRole;
	roles: string[];
	newMember = new SetRole();
	me: User;

	constructor(
		private roleService: RoleService,
		private tokenProvider: TokenProvider,
		private authService: AuthService,
		private errorService: ErrorService
	) {
		this.roles = Object.keys(this.userRole).filter(k => !isNaN(Number(k)));
	}

	ngOnInit(): void {
		this.newMember.projectId = this.project.id;
		this.tokenProvider.token.subscribe(token => {
			this.authService.validate(token).subscribe(me => this.me = me);
			this.roleService.getProjectRoles(token, this.project.id).subscribe(
				roles => this.members = roles
			);
		});
	}

	updateMember(member: Role) {
		console.log('update', member);
		this.tokenProvider.token.subscribe(token => {
			this.roleService.setUserRole(token, this.memberToSetRole(member)).subscribe(
				() => {
				},
				e => {
					this.errorService.raise(e);
					// refresh roles
					this.roleService.getProjectRoles(token, this.project.id).subscribe(
						roles => this.members = roles
					);
				}
			);
		});
	}

	addMember() {
		this.tokenProvider.token.subscribe(token => {
			this.roleService.setUserRole(token, this.newMember).subscribe(newRole => {
					this.members.push(newRole);
					this.newMember.role = undefined;
					this.newMember.login = '';
				},
				e => {
					this.errorService.raise(e);
					this.newMember.role = undefined;
					this.newMember.login = '';
				}
			);
		});
	}

	removeMember(member: Role) {
		this.tokenProvider.token.subscribe(token => {
			this.roleService.removeUserRole(token, member.user.login, this.project.id).subscribe(
				() => this.members = this.members.filter(m => m.id != member.id)
			);
		});
	}

	memberToSetRole(member: Role): SetRole {
		let setRole = new SetRole();
		setRole.login = member.user.login;
		setRole.projectId = member.project.id;
		setRole.role = member.role;
		return setRole;
	}
}
