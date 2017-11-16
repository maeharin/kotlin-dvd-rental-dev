class SessionsController < ApplicationController
  skip_before_action :require_login

  def new
  end

  def create
    username = params[:username]
    password = params[:password]

    begin
      do_login(username, password)
    rescue DvdRentalStaffClient::ApiError => ex
      flash[:alert] = "認証エラー"
    end

    redirect_to root_path
  end

  def destroy
    do_logout
    redirect_to login_path
  end
end
  