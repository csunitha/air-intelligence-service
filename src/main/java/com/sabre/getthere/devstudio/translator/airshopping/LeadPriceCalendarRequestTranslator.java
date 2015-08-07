package com.sabre.getthere.devstudio.translator.airshopping;

import com.sabre.getthere.devstudio.domain.airshopping.LeadPriceCalendarRequest;
import com.sabre.getthere.devstudio.translator.AbstractRequestTranslator;

import java.util.List;

/**
 * User: Sunitha C
 * Date: 8/4/2015 4:22 PM
 */
public class LeadPriceCalendarRequestTranslator extends AbstractRequestTranslator {
   private static final String LENGTH_OF_STAY_SEPARTOR = ",+";

   public String translate(LeadPriceCalendarRequest request) {
      String searchCriteria = "?" + "origin=" + request.getOrigin() + PARAMETER_SEPERATOR +
              "destination=" + request.getDestination() + PARAMETER_SEPERATOR +
              "lengthofstay=" + getLengthOfStayInStringFormat(request.getLengthOfStay()) ;

      if(request.getDepartureDate() != null ) {
         searchCriteria = searchCriteria + PARAMETER_SEPERATOR +
                 "departuredate=" + getDateInStringFormat(request.getDepartureDate());
      }

      //TODO
      // There is support for following criteria also minFare, maxFare, pointOfSaleCountry. If required can add them
      return searchCriteria;
   }

   private String getLengthOfStayInStringFormat(List<Integer> lengthOfStays) {
      String lengthOfStaysInStringFormat = "";
      for (Integer days: lengthOfStays) {
         lengthOfStaysInStringFormat  = lengthOfStaysInStringFormat.length() > 0 ? lengthOfStaysInStringFormat.concat(LENGTH_OF_STAY_SEPARTOR) : "";
         lengthOfStaysInStringFormat = lengthOfStaysInStringFormat.concat(days.toString());
      }
      return lengthOfStaysInStringFormat;
   }

}
