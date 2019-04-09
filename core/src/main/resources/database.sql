create table "user"
(
  id     bigserial not null
    constraint user_pk
      primary key,
  joined date      not null
);

alter table "user"
  owner to postgres;

create table user_credentials
(
  id      bigserial not null
    constraint user_credentials_pk
      primary key,
  user_id bigint    not null
    constraint user_credentials_user_id_fk
      references "user",
  login   varchar   not null,
  hash    varchar   not null
);

alter table user_credentials
  owner to postgres;

create unique index user_credentials_login_uindex
  on user_credentials (login);

create table project
(
  id      bigserial not null
    constraint project_pk
      primary key,
  user_id bigint    not null
    constraint project_user_id_fk
      references "user",
  public  boolean   not null,
  created timestamp not null
);

alter table project
  owner to postgres;

create table project_info
(
  id          bigserial not null
    constraint project_info_pk
      primary key,
  project_id  bigint    not null
    constraint project_info_project_id_fk
      references project,
  name        varchar   not null,
  description varchar,
  about       varchar
);

alter table project_info
  owner to postgres;

create table task
(
  id         bigserial not null
    constraint task_pk
      primary key,
  project_id bigint    not null
    constraint task_project_id_fk
      references project,
  type       varchar   not null,
  estimate   double precision,
  elapsed    double precision,
  opened     timestamp,
  due        timestamp
);

alter table task
  owner to postgres;

create table task_info
(
  id          bigserial not null
    constraint task_info_pk
      primary key,
  task_id     bigint    not null
    constraint task_info_task_id_fk
      references task,
  name        varchar   not null,
  description varchar
);

alter table task_info
  owner to postgres;

create table user_role
(
  id         bigserial not null
    constraint user_role_pk
      primary key,
  user_id    bigint    not null
    constraint user_role_user_id_fk
      references "user",
  project_id bigint    not null
    constraint user_role_project_id_fk
      references project,
  role       varchar   not null
);

alter table user_role
  owner to postgres;

create table activity
(
  id                    bigint    not null
    constraint activity_pk
      primary key,
  task_id               bigint    not null
    constraint activity_task_id_fk
      references task,
  user_id               bigint    not null
    constraint activity_user_id_fk
      references "user",
  status                varchar,
  completion_difference integer,
  timestamp             timestamp not null,
  description           varchar
);

alter table activity
  owner to postgres;

create table user_follower
(
  user_id     bigint not null
    constraint user_follower_user_id_fk
      references "user",
  follower_id bigint not null
    constraint user_follower_user_id_fk_2
      references "user"
);

alter table user_follower
  owner to postgres;

create table token
(
  id      bigserial not null
    constraint token_pk
      primary key,
  user_id bigint
    constraint token_user_id_fk
      references "user",
  token   varchar   not null
);

alter table token
  owner to postgres;

create unique index token_token_uindex
  on token (token);

create table user_info
(
  id       bigserial not null
    constraint user_info_pk
      primary key,
  user_id  bigint    not null
    constraint user_info_user_id_fk
      references "user",
  name     varchar,
  bio      varchar,
  url      varchar,
  company  varchar,
  location varchar
);

alter table user_info
  owner to postgres;

create table task_subtask
(
  task_id    bigint not null
    constraint task_subtask_task_id_fk
      references task,
  subtask_id bigint not null
    constraint task_subtask_task_id_fk_2
      references task
);

alter table task_subtask
  owner to postgres;
