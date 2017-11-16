class Ajax::ActorsController < ApplicationController
  def search
    actors = actor_api.staff_search_actors(params[:query])
    render json: actors
  end
end