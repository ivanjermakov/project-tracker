import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthComponent} from './component/auth/auth.component';
import {RegisterComponent} from './component/register/register.component';
import {FeedComponent} from './component/feed/feed.component';
import {ProfileComponent} from './component/profile/profile.component';
import {NewProjectComponent} from './component/new-project/new-project.component';
import {ProfileOverviewComponent} from './component/profile-overview/profile-overview.component';
import {ProfileActivityComponent} from './component/profile-activity/profile-activity.component';
import {ProjectComponent} from './component/project/project.component';
import {ProfileFollowersComponent} from './component/profile-followers/profile-followers.component';
import {ProfileFollowingComponent} from './component/profile-following/profile-following.component';
import {NewTaskComponent} from './component/new-task/new-task.component';
import {TaskComponent} from './component/task/task.component';
import {ActivityComponent} from './component/activity/activity.component';
import {NewActivityComponent} from './component/new-activity/new-activity.component';

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
		data: {title: 'Profile'},
		children: [
			{
				path: 'overview',
				redirectTo: '',
			},
			{
				path: 'activity',
				component: ProfileActivityComponent,
			},
			{
				path: 'followers',
				component: ProfileFollowersComponent,
			},
			{
				path: 'following',
				component: ProfileFollowingComponent,
			},
			{
				path: '',
				component: ProfileOverviewComponent,
			},
		]
	},
	{
		path: ':login/:name',
		component: ProjectComponent,
		data: {title: 'Project'}
	},
	{
		path: ':login/:name/task/create',
		component: NewTaskComponent,
		data: {title: 'New task'}
	},
	{
		path: ':login/:name/task/:id',
		component: TaskComponent,
		data: {title: 'Project'}
	},
	{
		path: ':login/:name/task/:taskId/activity/create',
		component: NewActivityComponent,
		data: {title: 'New activity'}
	},
	{
		path: ':login/:name/task/:taskId/activity/:activityId',
		component: ActivityComponent,
		data: {title: 'Activity'}
	},
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {
}
