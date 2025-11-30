select *
from t_user_info
where description like '%strong%'
  and name like 'Ama%'
order by birthday desc limit 10
