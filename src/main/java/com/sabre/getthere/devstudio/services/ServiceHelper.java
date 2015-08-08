package com.sabre.getthere.devstudio.services;

import flexjson.JSONDeserializer;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.util.HashMap;

/**
 * User: Sunitha C
 * Date: 8/8/2015 8:23 PM
 */
public class ServiceHelper {
   private static final String API_ENDPOINT = "https://api.test.sabre.com";
   private static final String AUTHENTICATION_SERVICE_URL = API_ENDPOINT + "/v2/auth/token";
   private static final String LEAD_PRICE_CALENDAR_SERVICE_URL = API_ENDPOINT + "/v2/shop/flights/fares";
   private static final String LOW_FARE_FORECAST_SERVICE_URL = API_ENDPOINT + "/v1/forecast/flights/fares/";

   private static final String CLIENT_SECRET = "";
   private static final String CLIENT_ID = "";

   private static final String EMPTY_STRING = "";

   public String makeServiceCallToGetAuthenticationToken() {
      String tokenResponse = makeServiceCallToGetAuthenticationToken(getAuthenticationUri(), getEncodedClientSecretKey());
      HashMap deserializedTokenResponse = (HashMap) new JSONDeserializer().deserialize(tokenResponse);
      return (String) deserializedTokenResponse.get("access_token");
   }

   public String makeServiceCallToGetLeadPriceCalendar(String request, String authenticationToken) {
      String uri = getLeadPriceCalendarUri() + request;
      return makeServiceCall(uri, authenticationToken);
   }

   public String makeServiceCallToGetLowFareForecast(String request, String authenticationToken) {
      String uri = getLowFareForecastUri() + request;
      return makeServiceCall(uri, authenticationToken);
   }

   private String makeServiceCall(String uri, String authenticationToken) {
      GetMethod getMethod = new GetMethod(uri);
      getMethod.addRequestHeader(getHeaderUpdatedWithAuthorizationToken(authenticationToken));
      HttpClient client = new HttpClient();
      try {
         client.executeMethod(getMethod);
         return getMethod.getResponseBodyAsString();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         getMethod.releaseConnection();
      }
      return EMPTY_STRING;
   }

   private String makeServiceCallToGetAuthenticationToken(String authenticationUri, String encodedClientIdSecretKey) {
      PostMethod postMethod = getPostMethod(authenticationUri, encodedClientIdSecretKey);
      HttpClient httpClient = new HttpClient();

      try {
         httpClient.executeMethod(postMethod);
         return postMethod.getResponseBodyAsString();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         postMethod.releaseConnection();
      }
      return EMPTY_STRING;
   }

   private PostMethod getPostMethod(String authenticationUri, String encodedClientIdSecretKey) {
      PostMethod postMethod = new PostMethod(authenticationUri);
      postMethod.addRequestHeader(getHeaderForContentType());
      postMethod.addRequestHeader(getHeaderWithEncodedClientKey(encodedClientIdSecretKey));
      postMethod.addParameter(getParamWithGrantType());
      return postMethod;
   }

   private Header getHeaderForContentType() {
      Header header = new Header();
      header.setName("Content-Type");
      header.setValue("application/x-www-form-urlencoded");
      return header;
   }

   private Header getHeaderWithEncodedClientKey(String encodedClientIdSecretKey) {
      Header header = new Header();
      header.setName("Authorization");
      header.setValue("Basic " + encodedClientIdSecretKey);
      return header;
   }

   private NameValuePair getParamWithGrantType() {
      return new NameValuePair("grant_type", "client_credentials");
   }

   private Header getHeaderUpdatedWithAuthorizationToken(String authenticationToken) {
      Header mtHeader = new Header();
      mtHeader.setName("Authorization");
      mtHeader.setValue("Bearer " + authenticationToken);
      return mtHeader;
   }

   private String getAuthenticationUri() {
      return AUTHENTICATION_SERVICE_URL;
   }

   private String getLeadPriceCalendarUri() {
      return (LEAD_PRICE_CALENDAR_SERVICE_URL);
   }

   private String getLowFareForecastUri() {
      return (LOW_FARE_FORECAST_SERVICE_URL);
   }

   private String getEncodedClientSecretKey() {
      return Base64.encodeBase64String((getEncodedClientId() + ":" + getEncodedClientSecret()).getBytes());
   }

   private String getEncodedClientSecret() {
      return Base64.encodeBase64String(CLIENT_SECRET.getBytes());
   }

   private String getEncodedClientId() {
      return Base64.encodeBase64String(CLIENT_ID.getBytes());
   }

}
