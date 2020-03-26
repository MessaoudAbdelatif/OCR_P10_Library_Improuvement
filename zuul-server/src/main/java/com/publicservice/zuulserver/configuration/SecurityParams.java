package com.publicservice.zuulserver.configuration;

public interface SecurityParams {

  String JWT_HEADER_NAME = "Authorization";
  String SECRET = "OpenClassRooms";
  long EXPIRATION = 86400000; // 24H
  String HEADER_PREFIX = "Bearer ";

}
