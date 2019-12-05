package com.publicservice.zuulserver.configuration;


//@ConfigurationProperties("my-security-config")

public interface ApplicationPropertiesConfiguration {

  public static final String JWT_HEADER_NAME="Authorization";
  public static final String SECRET="OpenClassRooms";
  public static final int EXPIRATION=864000;
  public static final String HEADER_PREFIX="Bearer ";
}