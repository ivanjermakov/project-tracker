-- activity
drop function find_all_activities_by_task(id bigint);
create or replace function find_all_activities_by_task(id bigint) returns setof activity as
$$
select a
from activity a
         join task t on t.id = a.task_id
where t.id = $1;
$$ language sql;

drop function find_all_activities_by_project(id bigint);
create or replace function find_all_activities_by_project(id bigint) returns setof activity as
$$
select a
from activity a
         join task t on t.id = a.task_id
         join project p on p.id = t.project_id
where p.id = $1;
$$ language sql;

drop function find_all_activities_by_user(id bigint);
create or replace function find_all_activities_by_user(id bigint) returns setof activity as
$$
select a
from activity a
         join task t on t.id = a.task_id
         join project p on p.id = t.project_id
         join "user" u on u.id = p.user_id
where u.id = $1;
$$ language sql;

drop function find_all_activities_by_project(id bigint);
create or replace function find_all_activities_by_project(id bigint) returns setof record as
$$
select date(a.timestamp) as day, count(*) as activityAmount
from project p
         join task t on p.id = t.project_id
         join activity a on t.id = a.task_id
where p.id = $1
group by day
order by day;
$$ language sql;

drop function find_all_assignee_activities(id bigint);
create or replace function find_all_assignee_activities(id bigint) returns setof record as
$$
select *
from task t
         join (select *
               from activity
               where assignee_id = $1
               order by timestamp desc
               limit 1) as aa
              on t.id = aa.task_id
$$ language sql;

-- project
drop function find_project_by_name_and_user(name varchar, id bigint);
create or replace function find_project_by_name_and_user(name varchar, id bigint) returns project as
$$
select p
from project p
         join project_info pi on p.id = pi.project_id
         join "user" u on p.user_id = u.id
where p.user_id = $2
  and pi.name = $1
$$ language sql;

drop function find_all_projects_by_user(id bigint);
create or replace function find_all_projects_by_user(id bigint) returns project as
$$
select p
from project p
         join "user" u on p.user_id = u.id
where p.user_id = $1
$$ language sql;

drop function find_projects_by_login_and_name("login" varchar, "name" varchar);
create or replace function find_projects_by_login_and_name("login" varchar, "name" varchar) returns project as
$$
select p
from project p
         join "user" u on u.id = p.user_id
         join project_info pi on p.id = pi.project_id
         join user_credentials uc on u.id = uc.user_id
where uc.login = $1
  and pi.name = $2
$$ language sql;

drop function find_projects_containing(id bigint, search varchar);
create or replace function find_projects_containing(id bigint, "search" varchar) returns project as
$$
select p
from project p
         join "user" u on u.id = p.user_id
         join project_info pi on p.id = pi.project_id
where u.id = $1
  and pi.name like $2
$$ language sql;

-- role
drop function find_role_by_user_and_project(userId bigint, projectId bigint);
create or replace function find_role_by_user_and_project(userId bigint, projectId bigint) returns user_role as
$$
select r
from user_role r
where r.user_id = $1
  and r.project_id = $2
$$ language sql;

-- task
drop function find_all_tasks_by_project(id bigint);
create or replace function find_all_tasks_by_project(id bigint) returns setof task as
$$
select t
from task t
where t.project_id = $1
$$ language sql;

drop function find_task_types_by_project(id bigint);
create or replace function find_task_types_by_project(id bigint) returns setof record as
$$
select t.type, count(*)
from project p
         join task t on p.id = t.project_id
where p.id = $1
group by t.type
order by t.type
$$ language sql;

drop function find_all_tasks_by_creator(id bigint);
create or replace function find_all_tasks_by_creator(id bigint) returns setof record as
$$
select t
from task t
where t.creator_id = $1
$$ language sql;

-- token
drop function find_token_by_token(token varchar);
create or replace function find_token_by_token(token varchar) returns token as
$$
select t
from token t
where t.token = $1
$$ language sql;

-- user credentials
drop function find_user_credentials_by_login("login" varchar);
create or replace function find_user_credentials_by_login("login" varchar) returns user_credentials as
$$
select uc
from user_credentials uc
where uc.login = $1
$$ language sql;
