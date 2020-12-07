SELECT setval('activity_id_seq', max(id))
FROM activity;
SELECT setval('project_id_seq', max(id))
FROM project;
SELECT setval('project_info_id_seq', max(id))
FROM project_info;
SELECT setval('task_id_seq', max(id))
FROM task;
SELECT setval('task_info_id_seq', max(id))
FROM task_info;
SELECT setval('token_id_seq', max(id))
FROM token;
SELECT setval('user_credentials_id_seq', max(id))
FROM user_credentials;
SELECT setval('user_id_seq', max(id))
FROM "user";
SELECT setval('user_info_id_seq', max(id))
FROM user_info;
SELECT setval('user_role_id_seq', max(id))
FROM user_role;
