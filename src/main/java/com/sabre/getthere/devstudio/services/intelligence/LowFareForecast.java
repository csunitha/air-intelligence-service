package com.sabre.getthere.devstudio.services.intelligence;

import com.sabre.getthere.devstudio.services.AbstractService;
import flexjson.JSONDeserializer;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

/**
 * User: Sunitha C
 * Date: 7/30/2015 2:42 PM
 */
@Deprecated
public class LowFareForecast extends AbstractService {
   private static final String EMPTY_STRING = "";
   private HttpClient client = new HttpClient();

   public static void main(String args[]) {
      LowFareForecast lowFareForecast = new LowFareForecast();
      String output = lowFareForecast.getLowFareForecast("JFK", "LAX", "2015-09-15", "2015-09-15");
      HashMap jsonoutput = (HashMap) new JSONDeserializer().deserialize(output);
      System.out.println(output);

   }

   public String getLowFareForecast(String origin, String destination, String departureDate, String returnDate) {

      GetMethod getMethod = null;
      try {
         getMethod = getRequest(origin, destination, departureDate, returnDate);
         client.executeMethod(getMethod);
         return getMethod.getResponseBodyAsString();
      } catch (UnsupportedEncodingException | HttpException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      finally {
         getMethod.releaseConnection();
      }
      
      return EMPTY_STRING;
   }

   private GetMethod getRequest(String origin, String destination, String departureDate, String returnDate) {
      GetMethod getMethod = new GetMethod(LOW_FARE_FORECAST_SERVICE_URL+
              consolidatedSearchCriteria(origin, destination, departureDate, returnDate));
      getMethod.addRequestHeader(getHeaderUpdatedWithAuthorizationToken());
      return getMethod;
   }

   private String consolidatedSearchCriteria(String origin, String destination, String departureDate, String returnDate) {
      return "?origin=" + origin + "&" + "destination=" + destination + "&" +
              "departuredate="+ departureDate + "&" +
              "returndate="+ returnDate;
   }

   private Header getHeaderUpdatedWithAuthorizationToken() {
      Header mtHeader = new Header();
      mtHeader.setName("Authorization");
      mtHeader.setValue("Bearer " + makeServiceCallToGetAuthenticationToken());
      return mtHeader;
   }

}
