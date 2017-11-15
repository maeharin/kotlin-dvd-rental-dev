class HomeController < ApplicationController
  def show
    @films = film_api.get_films
  end
end
