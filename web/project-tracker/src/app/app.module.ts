import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ProfileComponent} from './component/profile/profile.component';
import {FeedComponent} from './component/feed/feed.component';
import {ProjectComponent} from './component/project/project.component';
import {AuthComponent} from './component/auth/auth.component';
import {RegisterComponent} from './component/register/register.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {HeaderComponent} from './component/header/header.component';
import {FeedProjectComponent} from './component/feed-project/feed-project.component';
import {NewProjectComponent} from './component/new-project/new-project.component';
import { ProfileOverviewComponent } from './component/profile-overview/profile-overview.component';
import { ProfileActivityComponent } from './component/profile-activity/profile-activity.component';
import {RouterModule} from '@angular/router';
import { ProfileFollowersComponent } from './component/profile-followers/profile-followers.component';
import { ProfileFollowingComponent } from './component/profile-following/profile-following.component';
import { NewTaskComponent } from './component/new-task/new-task.component';
import { TaskComponent } from './component/task/task.component';
import { TasksTableComponent } from './component/tasks-table/tasks-table.component';
import { TaskTypeIconComponent } from './component/task-type-icon/task-type-icon.component';
import { ActivityComponent } from './component/activity/activity.component';
import { ActivityListComponent } from './component/activity-list/activity-list.component';
import { TaskStatusIconComponent } from './component/task-status-icon/task-status-icon.component';
import { NewActivityComponent } from './component/new-activity/new-activity.component';
import { ActivityItemComponent } from './component/activity-item/activity-item.component';

@NgModule({
	declarations: [
		AppComponent,
		ProfileComponent,
		FeedComponent,
		ProjectComponent,
		AuthComponent,
		RegisterComponent,
		HeaderComponent,
		FeedProjectComponent,
		NewProjectComponent,
		ProfileOverviewComponent,
		ProfileActivityComponent,
		ProfileFollowersComponent,
		ProfileFollowingComponent,
		NewTaskComponent,
		TaskComponent,
		TasksTableComponent,
		TaskTypeIconComponent,
		ActivityComponent,
		ActivityListComponent,
		TaskStatusIconComponent,
		NewActivityComponent,
		ActivityItemComponent
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		HttpClientModule,
		FormsModule
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule {
}
