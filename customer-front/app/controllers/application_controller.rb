class ApplicationController < ActionController::Base
  protect_from_forgery with: :exception

  def film_api
    @film_api ||= DvdRentalCustomerClient::FilmApi.new(_api_client)
  end

  private def _api_client
    @_api_client ||= begin
      config = DvdRentalCustomerClient::Configuration.new.tap do |c|
        c.scheme= 'http'
      end

      DvdRentalCustomerClient::ApiClient.new(config)
    end
  end
end
