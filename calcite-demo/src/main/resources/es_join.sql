SELECT
    *
FROM
    es.t_user_info a JOIN es.t_user_info_detail b on cast(a._MAP['user_id'] as varchar) = cast(b._MAP['user_id'] as varchar)
where a._MAP['description'] like '%strong%' and a._MAP['name'] like 'Ama%'
-- order by a._MAP['birthday'] desc
offset 0 fetch next 10 rows only


