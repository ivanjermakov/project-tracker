drop view view_today_activities;
create view view_today_activities as
select *
from activity a
where date(a.timestamp) = now()::date;

drop view view_public_projects;
create view view_public_projects as
select p.id, ui.name as owner, p.created
from project p
         join "user" u on p.user_id = u.id
         join user_info ui on u.id = ui.user_id
where p.public;

drop view view_open_tasks;
create view view_open_tasks as
select t.id, pi.name as project_name, t.estimate, t.opened, t.due
from task t
         join project p on t.project_id = p.id
         join project_info pi on p.id = pi.project_id
where t.type = '0';

drop view view_users_joined_today;
create view view_users_joined_today as
select u.id, ui.name, u.joined
from "user" u
         join user_info ui on u.id = ui.user_id
where date(u.joined) = now()::date;
