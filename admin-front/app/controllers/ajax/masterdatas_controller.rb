class Ajax::MasterdatasController < ApplicationController
  def languages
    languages = masterdata_api.staff_get_languages
    render json: languages
  end

  def categories
    categories = masterdata_api.staff_get_categories
    render json: categories
  end
end