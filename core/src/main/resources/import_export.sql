--export procedure
drop procedure export(in table_name varchar, in path varchar);
create procedure export(in table_name varchar, in path varchar) as
$$
begin
    copy (
        select concat(
                       '<set>',
                       table_to_xml($1, true, true, ''),
                       '</set>')
        ) to $2;
end;
$$
    language plpgsql;

--export example
call export('project', '/tmp/project.xml');

--import procedure
drop procedure import(in path text);
create procedure import(in path text) as
$$
declare
    myxml xml;
begin
    myxml := xmlparse(document convert_from(pg_read_binary_file(path), 'utf8'));
    drop table if exists temp_project;
    create temp table temp_project as
    select (xpath('//id/text()', x))[1]::text      as id
         , (xpath('//user_id/text()', x))[1]::text as user_id
         , (xpath('//public/text()', x))[1]::text  as public
         , (xpath('//created/text()', x))[1]::text as created
    from unnest(xpath('//project', myxml)) x;
end;
$$
    language plpgsql;

--import example
call import('/tmp/project.xml');
select *
from temp_project;

create extension pg_cron;
--run every day at 10am
SELECT cron.schedule('0 10 * * *', $$call export('project', '/tmp/project.xml')$$);
-- SELECT cron.unschedule(2);
--list all jobs
select *
from cron.job
