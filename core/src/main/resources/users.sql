revoke all privileges on database project_tracker from project_tracker_dba;
revoke all privileges on database project_tracker from project_tracker_spring;
drop user project_tracker_dba;
drop user project_tracker_spring;
drop group project_tracker_users;

create user project_tracker_dba with encrypted password 'password1';
grant all privileges on database project_tracker to project_tracker_dba;

create user project_tracker_spring with encrypted password 'password1';
grant select on all tables in schema public to project_tracker_spring;
grant insert on all tables in schema public to project_tracker_spring;
grant delete on all tables in schema public to project_tracker_spring;
grant update on all tables in schema public to project_tracker_spring;
grant usage, select on all sequences in schema public to project_tracker_spring;

create group project_tracker_users;
alter group project_tracker_users add user project_tracker_dba, project_tracker_spring;

