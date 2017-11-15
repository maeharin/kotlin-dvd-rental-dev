select
  /*%expand*/*
from
  actor
where
  actor_id in /* actorIds */(1,2,3)