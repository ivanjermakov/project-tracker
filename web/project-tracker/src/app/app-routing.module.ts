import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthComponent} from './component/routed/auth/auth.component';
import {RegisterComponent} from './component/routed/register/register.component';
import {FeedComponent} from './component/routed/feed/feed.component';
import {NewProjectComponent} from './component/routed/new-project/new-project.component';
import {ProfileComponent} from './component/routed/profile/profile.component';
import {ProfileActivityComponent} from './component/routed/profile-activity/profile-activity.component';
import {ProfileFollowersComponent} from './component/routed/profile-followers/profile-followers.component';
import {ProfileFollowingComponent} from './component/routed/profile-following/profile-following.component';
import {ProfileOverviewComponent} from './component/routed/profile-overview/profile-overview.component';
import {ProjectComponent} from './component/routed/project/project.component';
import {NewTaskComponent} from './component/routed/new-task/new-task.component';
import {TaskComponent} from './component/routed/task/task.component';
import {NewActivityComponent} from './component/routed/new-activity/new-activity.component';
import {ActivityComponent} from './component/routed/activity/activity.component';
import {TasksComponent} from './component/routed/tasks/tasks.component';


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
		path: 'tasks',
		component: TasksComponent,
		data: {title: 'Tasks'}
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
