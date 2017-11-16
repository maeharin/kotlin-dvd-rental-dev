class ApplicationController < ActionController::Base
  protect_from_forgery with: :exception
  before_action :require_login
  
  def require_login
    redirect_to login_path unless logged_in?
  end

  def logged_in?
    session[:staff_api_access_token].present?
  end

  def do_login(username, password)
      data, _, _ = _staff_api_client.call_api(
      'POST',
      '/oauth/token',
      {
        header_params: {
          'Authorization'=> _staff_api_client.config.basic_auth_token,
          'Accept' => 'application/json'
        },
        query_params: {
          grant_type: "password",
          user_type: "staff",
          username: username,
          password: password
        },
        return_type: 'Object'
      }
    )

    session[:staff_api_access_token] = data[:access_token]
  end

  def do_logout
    session[:staff_api_access_token] = nil
  end

  def film_api
    @film_api ||= DvdRentalStaffClient::FilmApi.new(_staff_api_client)
  end

  def actor_api
    @actor_api ||= DvdRentalStaffClient::ActorApi.new(_staff_api_client)
  end

  def masterdata_api
    @masterdata_api ||= DvdRentalStaffClient::MasterdataApi.new(_staff_api_client)
  end

  private

  def _staff_api_client
    @_staff_api_client ||= begin
      access_token = session[:staff_api_access_token]

      config = DvdRentalStaffClient::Configuration.new.tap do |c|
        c.scheme = Rails.env.production? ? 'https' : 'http'
        c.username = 'staff-api-client'
        c.password = 'fuge'
        c.debugging = Rails.env.production? ? false : true
      end

      client = DvdRentalStaffClient::ApiClient.new(config)

      if access_token
        client.default_headers.merge!({
          'Authorization' => "Bearer #{access_token}"
        })
      end

      client
    end
  end
end
