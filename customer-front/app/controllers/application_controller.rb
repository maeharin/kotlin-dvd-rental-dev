class ApplicationController < ActionController::Base
  protect_from_forgery with: :exception

  before_action :require_login

  def require_login
    redirect_to login_path unless logged_in?
  end

  def logged_in?
    session[:customer_api_access_token].present?
  end

  def do_login(username, password)
     data, _, _ = _customer_api_client.call_api(
      'POST',
      '/oauth/token',
      {
        header_params: {
          'Authorization'=> _customer_api_client.config.basic_auth_token,
          'Accept' => 'application/json'
        },
        query_params: {
          grant_type: "password",
          user_type: "customer",
          username: username,
          password: password
        },
        return_type: 'Object'
      }
    )

    session[:customer_api_access_token] = data[:access_token]
  end

  def do_logout
    session[:customer_api_access_token] = nil
  end

  def film_api
    @film_api ||= DvdRentalCustomerClient::FilmApi.new(_customer_api_client)
  end

  private

  def _customer_api_client
    @_customer_api_client ||= begin
      access_token = session[:customer_api_access_token]

      config = DvdRentalCustomerClient::Configuration.new.tap do |c|
        c.scheme = Rails.env.production? ? 'https' : 'http'
        c.username = 'customer-api-client'
        c.password = 'hoge'
        c.debugging = Rails.env.production? ? false : true
      end

      client = DvdRentalCustomerClient::ApiClient.new(config)

      if access_token
        client.default_headers.merge!({
          'Authorization' => "Bearer #{access_token}"
        })
      end

      client
    end
  end

end
