class Ajax::FilmsController < ApplicationController
  def index
    films = film_api.staff_get_films
    render json: films
  end

  def show
    film = film_api.staff_get_film(params[:id])
    render json: film
  end
end