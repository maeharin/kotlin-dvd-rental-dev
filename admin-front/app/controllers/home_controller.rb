class HomeController < ApplicationController
  def show
    @films = film_api.staff_get_films
  end
end
  