select
  /*%expand*/*
from
  film_actor
where
  actor_id = /* actorId */1
  and
  film_id = /* filmId */1
