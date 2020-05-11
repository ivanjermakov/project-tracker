create or replace function find_all_by_task(id bigint) returns refcursor
    language plpgsql
as
$$
DECLARE
    tasks REFCURSOR;
BEGIN
    OPEN tasks FOR
        select *
        from task t
        where t.id = $1;
    RETURN tasks;
END;
$$;

create or replace function find_all_by_task(id bigint) returns setof activity as
$$
select a
from activity a
         join task t on t.id = a.task_id
where t.id = $1;
$$ language sql;

create or replace function find_all_by_project(id bigint) returns setof activity as
$$
select a
from activity a
         join task t on t.id = a.task_id
         join project p on p.id = t.project_id
where p.id = $1;
$$ language sql;

select find_all_by_project(18);
select find_all_by_task(17);
