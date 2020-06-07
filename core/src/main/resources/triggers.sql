drop trigger update_estimate_trigger on activity;
drop function update_estimate();

create function update_estimate() returns trigger as
$$
begin
    update p
    set p.estimate = sum(p.estimate, t.estimate)
    from task t
             left join task p on p.parent_id = t.id
    where t.id = $1;
    return null;
end
$$ language plpgsql;

create trigger update_estimate_trigger
    after insert
    on activity
    for each row
execute procedure update_estimate()
