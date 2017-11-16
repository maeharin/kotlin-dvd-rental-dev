Rails.application.routes.draw do
  root to: "home#show"
  
  get 'login', to: "sessions#new"
  post 'login', to: "sessions#create"
  get 'logout', to: "sessions#destroy"

  get 'ajax/films', to: "ajax/films#index"
  post 'ajax/films', to: "ajax/films#create"
  get 'ajax/films/:id', to: "ajax/films#show"
  get 'ajax/actors/search', to: "ajax/actors#search"
  get 'ajax/masterdatas/languages', to: "ajax/masterdatas#languages"
  get 'ajax/masterdatas/categories', to: "ajax/masterdatas#categories"
end
