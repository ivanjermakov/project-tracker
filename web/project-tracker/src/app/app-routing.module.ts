import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthComponent} from './component/auth/auth.component';
import {RegisterComponent} from './component/register/register.component';
import {FeedComponent} from './component/feed/feed.component';
import {ProfileComponent} from './component/profile/profile.component';
import {ProjectComponent} from './component/project/project.component';

const routes: Routes = [
	{
		path: 'auth',
		component: AuthComponent
	},
	{
		path: 'register',
		component: RegisterComponent
	},
	{
		path: 'feed',
		component: FeedComponent
	},
	{
		path: 'profile',
		component: ProfileComponent
	},
	{
		path: ':login/:name',
		component: ProjectComponent
	}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {
}
