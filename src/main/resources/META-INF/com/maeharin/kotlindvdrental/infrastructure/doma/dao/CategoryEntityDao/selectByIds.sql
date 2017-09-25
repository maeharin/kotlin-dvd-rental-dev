select
  /*%expand*/*
from
  category
where
  category_id in /* categoryIds */(1,2,3)
