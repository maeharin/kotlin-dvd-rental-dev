class Ajax::FilmsController < ApplicationController
  def index
    films = film_api.staff_get_films
    render json: films
  end

  def show
    film = film_api.staff_get_film(params[:id])
    render json: film
  end

  def create
    film_api.staff_create_film(_film_create_params)
  end

  private

  def _film_create_params
    params.require(:film).permit!
  end
end