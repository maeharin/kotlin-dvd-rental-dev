select
  /*%expand*/*
from
  actor
where
  first_name ilike /* @infix(query) */'%foo%'
  or last_name ilike /* @infix(query) */'%foo%'
