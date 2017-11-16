class HomeController < ApplicationController
  def show
    @films = film_api.customer_get_films
  end
end
