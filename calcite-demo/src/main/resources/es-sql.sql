select a.name as name, a.job as job, b.country as country
from t_user_info a
         join t_user_info_detail b on a.user_id = b.user_id
where a.description like '%strong%'
  and a.name like 'Ama%'
order by a.birthday desc limit 10
