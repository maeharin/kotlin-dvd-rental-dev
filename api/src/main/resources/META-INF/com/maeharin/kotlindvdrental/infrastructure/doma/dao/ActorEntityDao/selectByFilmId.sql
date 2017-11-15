select
  /*%expand*/*
from
  actor
where
  actor_id in (
    select
      actor_id
    from
      film_actor
    where
      film_id = /* filmId */1
  )
