create index activity_task_id_index on activity (task_id);
create index activity_status_index on activity (status);

create index project_user_id_index on project (user_id);

create index project_info_name_description_index on project_info (name, description);

create index task_project_id_index on task (project_id);
create index task_creator_id_index on task (creator_id);
create index task_type_index on task (type);

create index task_info_name_description_index on task_info (name, description);

create index token_token_index on token (token);

create index user_credentials_login_index on user_credentials (login);

create index user_info_name_index on user_info (name);

create index user_role_user_id_index on user_role (user_id);
create index user_role_project_id_index on user_role (project_id);
