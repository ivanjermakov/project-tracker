create table "user"
(
    id     bigserial
        constraint user_pk
            primary key,
    joined date not null
);

create table user_credentials
(
    id      bigserial
        constraint user_credentials_pk
            primary key,
    user_id bigint  not null
        constraint user_credentials_user_id_fk
            references "user",
    login   varchar not null,
    hash    varchar not null
);

create unique index user_credentials_login_uindex
    on user_credentials (login);

create table project
(
    id bigserial
        constraint project_pk
            primary key,
    user_id bigint    not null
        constraint project_user_id_fk
            references "user",
    public  boolean   not null,
    created timestamp not null
);

create table project_info
(
    id          bigserial
        constraint project_info_pk
            primary key,
    project_id  bigint  not null
        constraint project_info_project_id_fk
            references project,
    name        varchar not null,
    description varchar,
    about       varchar
);

create table task
(
    id          bigserial
        constraint task_pk
            primary key,
    project_id  bigint  not null
        constraint task_project_id_fk
            references project,
    parent_id   bigint
        constraint task_task_id_fk
            references task,
    creator_id  bigint  not null
        constraint task_user_id_fk
            references "user",
    type        varchar not null,
    estimate    double precision,
    opened      timestamp,
    due         date,
    assignee_id bigint
        constraint task_user_id_fk_2
            references "user",
    priority    varchar,
    started     timestamp,
    status      varchar,
    tag         varchar
);

create table task_info
(
    id          bigserial
        constraint task_info_pk
            primary key,
    task_id     bigint  not null
        constraint task_info_task_id_fk
            references task,
    name        varchar not null,
    description varchar
);

create table user_role
(
    id         bigserial
        constraint user_role_pk
            primary key,
    user_id    bigint  not null
        constraint user_role_user_id_fk
            references "user",
    project_id bigint  not null
        constraint user_role_project_id_fk
            references project,
    role       varchar not null
);

create table user_follower
(
    user_id     bigint not null
        constraint user_follower_user_id_fk
            references "user",
    follower_id bigint not null
        constraint user_follower_user_id_fk_2
            references "user"
);

create table token
(
    id    bigserial
        constraint token_pk
            primary key,
    user_id bigint
        constraint token_user_id_fk
            references "user",
    token varchar not null
);

create unique index token_token_uindex
    on token (token);

create table user_info
(
    id       bigserial
        constraint user_info_pk
            primary key,
    user_id  bigint not null
        constraint user_info_user_id_fk
            references "user",
    name     varchar,
    bio      varchar,
    url      varchar,
    company  varchar,
    location varchar,
    skills   varchar
);

create table activity
(
    id bigserial
        constraint activity_pk
            primary key,
    task_id     bigint    not null
        constraint activity_task_id_fk
            references task,
    creator_id  bigint    not null
        constraint activity_user_id_fk_2
            references "user",
    assignee_id bigint
        constraint activity_user_id_fk
            references "user",
    status      varchar,
    elapsed     double precision,
    timestamp   timestamp not null,
    description varchar
);

