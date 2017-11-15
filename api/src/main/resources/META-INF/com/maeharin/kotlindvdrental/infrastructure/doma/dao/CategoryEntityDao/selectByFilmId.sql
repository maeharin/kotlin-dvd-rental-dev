select
  /*%expand*/*
from
  category
where
  category_id in (
    select
      category_id
    from
      film_category
    where
      film_id = /* filmId */1
  )
