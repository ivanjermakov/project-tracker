import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {ProfileComponent} from './component/routed/profile/profile.component';
import {FeedComponent} from './component/routed/feed/feed.component';
import {ProjectComponent} from './component/routed/project/project.component';
import {AuthComponent} from './component/routed/auth/auth.component';
import {RegisterComponent} from './component/routed/register/register.component';
import {HeaderComponent} from './component/embedded/header/header.component';
import {FeedProjectComponent} from './component/embedded/feed-project/feed-project.component';
import {NewProjectComponent} from './component/routed/new-project/new-project.component';
import {ProfileOverviewComponent} from './component/routed/profile-overview/profile-overview.component';
import {ProfileActivityComponent} from './component/routed/profile-activity/profile-activity.component';
import {ProfileFollowersComponent} from './component/routed/profile-followers/profile-followers.component';
import {ProfileFollowingComponent} from './component/routed/profile-following/profile-following.component';
import {NewTaskComponent} from './component/routed/new-task/new-task.component';
import {TaskComponent} from './component/routed/task/task.component';
import {TasksTableComponent} from './component/embedded/tasks-table/tasks-table.component';
import {TaskTypeIconComponent} from './component/embedded/task-type-icon/task-type-icon.component';
import {ActivityComponent} from './component/routed/activity/activity.component';
import {ActivityListComponent} from './component/embedded/activity-list/activity-list.component';
import {TaskStatusIconComponent} from './component/embedded/task-status-icon/task-status-icon.component';
import {NewActivityComponent} from './component/routed/new-activity/new-activity.component';
import {ActivityItemComponent} from './component/embedded/activity-item/activity-item.component';
import {ProjectActivityChartComponent} from './component/embedded/data-visualization/project-activity-chart/project-activity-chart.component';
import {ChartsModule} from 'ng2-charts';

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
		ActivityItemComponent,
		ProjectActivityChartComponent
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		HttpClientModule,
		FormsModule,
		ChartsModule
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule {
}
