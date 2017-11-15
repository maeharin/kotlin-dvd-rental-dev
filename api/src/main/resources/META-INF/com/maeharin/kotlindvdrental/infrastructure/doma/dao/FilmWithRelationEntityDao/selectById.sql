select
  /*%expand*/*
from
  (
    select
      F.*
      , L.name as language_name
      , L.last_update as language_last_update
      , A.actor_id as actor_id
      , A.first_name as actor_first_name
      , A.last_name as actor_last_name
      , A.last_update as actor_last_update
      , C.category_id as category_id
      , C.name as category_name
      , C.last_update as category_last_update
    from
      (
        select
          *
        from
          film
        where
          film_id = /* filmId */1
      ) as F
        -- language
        left outer join language as L
          on F.language_id = L.language_id
        -- actors
        left outer join film_actor as FA
          on F.film_id = FA.film_id
        left outer join actor as A
          on FA.actor_id = A.actor_id
        -- categories
        left outer join film_category as FC
          on F.film_id = FC.film_id
        left outer join category as C
          on FC.category_id = C.category_id
  ) as JOINED
