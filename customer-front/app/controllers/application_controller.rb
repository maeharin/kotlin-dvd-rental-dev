class ApplicationController < ActionController::Base
  protect_from_forgery with: :exception

  def film_api
    @film_api ||= DvdRentalClient::FilmApi.new(_api_client)
  end

  private def _api_client
    @_api_client ||= begin
      config = DvdRentalClient::Configuration.new.tap do |c|
        c.scheme= 'http'
      end

      DvdRentalClient::ApiClient.new(config)
    end
  end
end
