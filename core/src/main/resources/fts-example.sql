select t.id,
       ti.name,
       ti.description,
       'bug'                                                                                as tsquery,
       ts_rank_cd(to_tsvector(concat(ti.name || ' ' || ti.description)), to_tsquery('bug')) as score
from task t
         join task_info ti on ti.task_id = t.id
order by score desc;

