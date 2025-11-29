SELECT *
FROM es.t_user_info
WHERE _MAP['name'] LIKE '%ha%'
   or  _MAP['job'] like '%ha%'
   or _MAP['description']  like '%hospital%'
limit 10