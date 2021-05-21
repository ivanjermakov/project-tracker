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
import {ProfileProjectsComponent} from './component/routed/profile-projects/profile-projects.component';
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
import {ProjectTaskTypeChartComponent} from './component/embedded/data-visualization/project-task-type-chart/project-task-type-chart.component';
import {ChartComponent} from './component/embedded/data-visualization/chart/chart.component';
import {TasksComponent} from './component/routed/tasks/tasks.component';
import {ErrorComponent} from './component/routed/error/error.component';
import { ProfileProjectComponent } from './component/embedded/profile-project/profile-project.component';
import { NotFoundComponent } from './component/embedded/not-found/not-found.component';
import { ProfilePreviewComponent } from './component/embedded/profile-preview/profile-preview.component';
import { TeamComponent } from './component/embedded/team/team.component';
import { ProjectTasksComponent } from './component/routed/project-tasks/project-tasks.component';
import { TaskPriorityIconComponent } from './component/embedded/task-priority-icon/task-priority-icon.component';

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
		ProfileProjectsComponent,
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
		ProjectActivityChartComponent,
		ProjectTaskTypeChartComponent,
		ChartComponent,
		TasksComponent,
		ErrorComponent,
		ProfileProjectComponent,
		NotFoundComponent,
		ProfilePreviewComponent,
		TeamComponent,
		ProjectTasksComponent,
		TaskPriorityIconComponent
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
