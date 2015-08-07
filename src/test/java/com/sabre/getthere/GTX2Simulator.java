package com.sabre.getthere;

import com.sabre.getthere.devstudio.domain.airshopping.FaresInfo;
import com.sabre.getthere.devstudio.domain.airshopping.LeadPriceCalendarRequest;
import com.sabre.getthere.devstudio.domain.airshopping.LeadPriceCalendarResponse;
import com.sabre.getthere.devstudio.domain.intelligence.LowFareForecastRequest;
import com.sabre.getthere.devstudio.domain.intelligence.LowFareForecastResponse;
import com.sabre.getthere.devstudio.services.airshopping.LeadPriceCalendarService;
import com.sabre.getthere.devstudio.services.intelligence.LowFareForecast;
import com.sabre.getthere.devstudio.services.intelligence.LowFareForecastService;
import flexjson.JSONDeserializer;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

/**
 * User: Sunitha C
 * Date: 8/3/2015 4:32 PM
 */
public class GTX2Simulator {

   public static void main(String args[]) {

//      testLowForecastService();
//      testLowForecastServiceNoResultsScenario();
//      testLowForecastServiceErrorScenario();
//      testLowForecastOldServiceCall();
      testLeadPriceCalendarServiceErrorScenario();
//      testLeadPriceCalendarService();
      testLeadPriceCalendarServiceWhichReturnsMultipleOptionsForSpecificDepartureDate();
//      testLeadPriceCalendarServiceWithNoDepartureDate();

   }

   private static void testLeadPriceCalendarServiceWhichReturnsMultipleOptionsForSpecificDepartureDate() {
      LeadPriceCalendarService leadPriceCalendar = new LeadPriceCalendarService();
      LeadPriceCalendarResponse leadPriceCalendarResponse = leadPriceCalendar.execute(
              "JFK",
              "LAX",
              new GregorianCalendar(2015, 9, 5),
              new GregorianCalendar(2015, 9, 9));
      System.out.println("******* Lead Price Calendar Response with Departure Date in request ************ ");
      System.out.println("LeadPriceCalendarResponse Status => " + leadPriceCalendarResponse.getStatus());
      System.out.println("LeadPriceCalendarResponse Total recommendations => " + leadPriceCalendarResponse.getFaresInfo().size());

      int index = 1;
      for(FaresInfo faresInfo : leadPriceCalendarResponse.getFaresInfo()) {
         System.out.println("                  ---- Fare : " + (index++) + " ----" );
         System.out.println("                  Currency Code for Fare  => " + faresInfo.getCurrencyCode());
         System.out.println("                  LowestFare => " + faresInfo.getLowestFare().getFare());
         System.out.println("                  LowestFare Airlines => " + faresInfo.getLowestFare().getAirlineCodes().toString());
         System.out.println("                  LowestNonStopFare => " + faresInfo.getLowestNonStopFare().getFare());
         System.out.println("                  LowestNonStopFare Airline => " + faresInfo.getLowestNonStopFare().getAirlineCodes().get(0));
         System.out.println("                  Departure Date         =>  " + getDateAsString(faresInfo.getDepartureDate()));
         System.out.println("                  Return Date            =>  " + getDateAsString(faresInfo.getReturnDate()));
      }
      System.out.println();
   }

   private static void testLeadPriceCalendarService() {
      LeadPriceCalendarService leadPriceCalendar = new LeadPriceCalendarService();
      LeadPriceCalendarRequest leadPriceCalendarRequest = new LeadPriceCalendarRequest();
      leadPriceCalendarRequest.setOrigin("JFK");
      leadPriceCalendarRequest.setDestination("LAX");
      List<Integer> lengthOfStay = new ArrayList<>();
      lengthOfStay.add(5);
      leadPriceCalendarRequest.setLengthOfStay(lengthOfStay);
      leadPriceCalendarRequest.setDepartureDate(new GregorianCalendar(2015, 9, 5));
      LeadPriceCalendarResponse leadPriceCalendarResponse = leadPriceCalendar.execute(leadPriceCalendarRequest);
      System.out.println("******* Lead Price Calendar Response with Departure Date in request ************ ");
      System.out.println("LeadPriceCalendarResponse Status => " + leadPriceCalendarResponse.getStatus());
      System.out.println("LeadPriceCalendarResponse Total recommendations => " + leadPriceCalendarResponse.getFaresInfo().size());

      int index = 1;
      for(FaresInfo faresInfo : leadPriceCalendarResponse.getFaresInfo()) {
         System.out.println("                  ---- Fare : " + (index++) + " ----" );
         System.out.println("                  Currency Code for Fare  => " + faresInfo.getCurrencyCode());
         System.out.println("                  LowestFare => " + faresInfo.getLowestFare().getFare());
         System.out.println("                  LowestFare Airlines => " + faresInfo.getLowestFare().getAirlineCodes().toString());
         System.out.println("                  LowestNonStopFare => " + faresInfo.getLowestNonStopFare().getFare());
         System.out.println("                  LowestNonStopFare Airline => " + faresInfo.getLowestNonStopFare().getAirlineCodes().get(0));
         System.out.println("                  Departure Date         =>  " + getDateAsString(faresInfo.getDepartureDate()));
         System.out.println("                  Return Date            =>  " + getDateAsString(faresInfo.getReturnDate()));
      }
      System.out.println();
   }

   private static void testLeadPriceCalendarServiceErrorScenario() {
      LeadPriceCalendarService leadPriceCalendar = new LeadPriceCalendarService();
      LeadPriceCalendarRequest leadPriceCalendarRequest = new LeadPriceCalendarRequest();
      leadPriceCalendarRequest.setOrigin("JFK");
      leadPriceCalendarRequest.setDestination("LAX");
      List<Integer> lengthOfStay = new ArrayList<>();
      lengthOfStay.add(5);
      leadPriceCalendarRequest.setLengthOfStay(lengthOfStay);
      leadPriceCalendarRequest.setDepartureDate(new GregorianCalendar(2015, 7, 5));
      LeadPriceCalendarResponse response = leadPriceCalendar.execute(leadPriceCalendarRequest);
      System.out.println("******* Lead Price Calendar Response ERROR SCENARIO************ ");
      if(response.getStatus() ==  "FAILURE") {
         System.out.println("Error Code            =>  " + response.getErrorCode());
         System.out.println("Error Message         =>  " + response.getErrorMessage());
         System.out.println();
      }
   }

   private static void testLeadPriceCalendarServiceWithNoDepartureDate() {
      LeadPriceCalendarService leadPriceCalendar = new LeadPriceCalendarService();
      LeadPriceCalendarRequest leadPriceCalendarRequest = new LeadPriceCalendarRequest();
      leadPriceCalendarRequest.setOrigin("JFK");
      leadPriceCalendarRequest.setDestination("LAX");
      List<Integer> lengthOfStay = new ArrayList<>();
      lengthOfStay.add(5);
      leadPriceCalendarRequest.setLengthOfStay(lengthOfStay);
      LeadPriceCalendarResponse leadPriceCalendarResponse = leadPriceCalendar.execute(leadPriceCalendarRequest);
      System.out.println("******* Lead Price Calendar Response with NO Departure Date in request ************ ");
      System.out.println("LeadPriceCalendarResponse Status => " + leadPriceCalendarResponse.getStatus());
      System.out.println("LeadPriceCalendarResponse Total recommendations => " + leadPriceCalendarResponse.getFaresInfo().size());

      int index = 1;
      for(FaresInfo faresInfo : leadPriceCalendarResponse.getFaresInfo()) {
         System.out.println("                  ---- Fare : " + (index++) + " ----" );
         System.out.println("                  Currency Code for Fare  => " + faresInfo.getCurrencyCode());
         System.out.println("                  LowestFare => " + faresInfo.getLowestFare().getFare());
         System.out.println("                  LowestFare Airlines => " + faresInfo.getLowestFare().getAirlineCodes().toString());
         System.out.println("                  LowestNonStopFare => " + faresInfo.getLowestNonStopFare().getFare());
         System.out.println("                  LowestNonStopFare Airline => " + faresInfo.getLowestNonStopFare().getAirlineCodes().get(0));
         System.out.println("                  Departure Date         =>  " + getDateAsString(faresInfo.getDepartureDate()));
         System.out.println("                  Return Date            =>  " + getDateAsString(faresInfo.getReturnDate()));
      }
      System.out.println();
   }


   private static void testLowForecastService() {
      LowFareForecastRequest lowFareForecastRequest = new LowFareForecastRequest();
      lowFareForecastRequest.setOrigin("JFK");
      lowFareForecastRequest.setDestination("LAX");
      lowFareForecastRequest.setDepartureDate(new GregorianCalendar(2015, 9, 15));
      lowFareForecastRequest.setReturnDate(new GregorianCalendar(2015, 9, 15));

      LowFareForecastService lowForeCastService = new LowFareForecastService();
      LowFareForecastResponse lowFareForecastResponse =  lowForeCastService.execute(lowFareForecastRequest);

      System.out.println("*************************** N E W  S E R V I C E  C A L L  R E S P O N S E *************************** ");

      if(lowFareForecastResponse.getStatus() == "SUCCESS") {
         System.out.println("Recommendation         =>  " + lowFareForecastResponse.getRecommendation());
         System.out.println();

         System.out.println("Origin                 =>  " + lowFareForecastResponse.getOrigin());
         System.out.println("Destination            =>  " + lowFareForecastResponse.getDestination());
         System.out.println("Departure Date         =>  " + getDateAsString(lowFareForecastResponse.getDepartureDate()));
         System.out.println("Return Date            =>  " + getDateAsString(lowFareForecastResponse.getReturnDate()));
         System.out.println("Currency Code          =>  " + lowFareForecastResponse.getCurrencyCode());
         System.out.println("Highest Predicted Fare =>  " + lowFareForecastResponse.getHighestPredictedFare());
         System.out.println("Lowest Predicted Fare  =>  " + lowFareForecastResponse.getLowestPredictedFare());
         System.out.println("Lowest Fare            =>  " + lowFareForecastResponse.getLowestFare());
      }

      System.out.println("*************************** N E W  S E R V I C E  C A L L  R E S P O N S E ends here ****************** ");
      System.out.println();
      System.out.println();
      System.out.println();
   }

   private static void testLowForecastServiceErrorScenario() {
      LowFareForecastRequest lowFareForecastRequest = new LowFareForecastRequest();
      lowFareForecastRequest.setOrigin("JFK");
      lowFareForecastRequest.setDestination("LAX");
      lowFareForecastRequest.setDepartureDate(new GregorianCalendar(2015, 9, 25));
      lowFareForecastRequest.setReturnDate(new GregorianCalendar(2015, 9, 15));

      LowFareForecastService lowForeCastService = new LowFareForecastService();
      LowFareForecastResponse lowFareForecastResponse =  lowForeCastService.execute(lowFareForecastRequest);

      System.out.println("*************************** N E W  S E R V I C E  C A L L  R E S P O N S E - ERROR SCENARIO*************************** ");

      if(lowFareForecastResponse.getStatus() ==  "FAILURE") {
         System.out.println("Error Code            =>  " + lowFareForecastResponse.getErrorCode());
         System.out.println("Error Message         =>  " + lowFareForecastResponse.getErrorMessage());

      }
      System.out.println("*************************** N E W  S E R V I C E  C A L L  R E S P O N S E ends here ****************** ");
      System.out.println();
      System.out.println();
      System.out.println();
   }

   private static void testLowForecastServiceNoResultsScenario() {
      LowFareForecastRequest lowFareForecastRequest = new LowFareForecastRequest();
      lowFareForecastRequest.setOrigin("DFW");
      lowFareForecastRequest.setDestination("ORD");
      lowFareForecastRequest.setDepartureDate(new GregorianCalendar(2015, 9, 15));
      lowFareForecastRequest.setReturnDate(new GregorianCalendar(2015, 9, 15));

      LowFareForecastService lowForeCastService = new LowFareForecastService();
      LowFareForecastResponse lowFareForecastResponse =  lowForeCastService.execute(lowFareForecastRequest);

      System.out.println("*************************** N E W  S E R V I C E  C A L L  R E S P O N S E - No Results Scenario*************************** ");

      if(lowFareForecastResponse.getStatus() ==  "FAILURE") {
         System.out.println("Error Code            =>  " + lowFareForecastResponse.getErrorCode());
         System.out.println("Error Message         =>  " + lowFareForecastResponse.getErrorMessage());

      }
      System.out.println("*************************** N E W  S E R V I C E  C A L L  R E S P O N S E ends here ****************** ");
      System.out.println();
      System.out.println();
      System.out.println();
   }


   private static void testLowForecastOldServiceCall() {

      System.out.println("*************************** O L D  S E R V I C E  C A L L *************************** ");
      System.out.println();
      LowFareForecast lowFareForecast = new LowFareForecast();
      String output = lowFareForecast.getLowFareForecast("JFK", "LAX", "2015-09-15", "2015-09-15");
      HashMap jsonoutput = (HashMap) new JSONDeserializer().deserialize(output);
      System.out.println(output);
      System.out.println();
      System.out.println("*************************** O L D  S E R V I C E  C A L L ends here*************************** ");
   }

   private static String getDateAsString(GregorianCalendar date) {
      return date.get(GregorianCalendar.YEAR) + "-" +
              date.get(GregorianCalendar.MONTH) + "-" +
              date.get(GregorianCalendar.DATE);
   }

}
