import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthComponent} from './component/auth/auth.component';
import {RegisterComponent} from './component/register/register.component';
import {FeedComponent} from './component/feed/feed.component';
import {ProfileComponent} from './component/profile/profile.component';
import {ProjectComponent} from './component/project/project.component';
import {NewProjectComponent} from './component/new-project/new-project.component';

const routes: Routes = [
	{
		path: 'auth',
		component: AuthComponent,
		data: {title: 'Auth'}
	},
	{
		path: 'register',
		component: RegisterComponent,
		data: {title: 'Register'}
	},
	{
		path: 'feed',
		component: FeedComponent,
		data: {title: 'Feed'}
	},
	{
		path: 'project/create',
		component: NewProjectComponent,
		data: {title: 'New project'}
	},
	{
		path: ':login',
		component: ProfileComponent,
		// TODO: change page title to user name
		data: {title: 'Profile'}
	},
	{
		path: ':login/:name',
		component: ProjectComponent,
		data: {title: 'Project'}
	},
	{
		path: ':login/:name/overview',
		redirectTo: ':login/:name',
		data: {title: 'Project overview'}
	}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {
}
