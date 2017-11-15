select
  /*%expand*/*
from
  film_category
where
  film_id = /* filmId */1
  and
  category_id = /* categoryId */1
