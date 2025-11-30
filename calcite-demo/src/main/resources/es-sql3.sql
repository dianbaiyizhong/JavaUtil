SELECT *
FROM t_user_info
WHERE name LIKE '%ha%'
   or  job like '%ha%'
   or description  like '%hospital%'
    limit 10