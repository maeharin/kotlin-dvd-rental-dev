Rails.application.routes.draw do
  root to: "home#show"
  
  get 'login', to: "sessions#new"
  post 'login', to: "sessions#create"
  get 'logout', to: "sessions#destroy"
end
