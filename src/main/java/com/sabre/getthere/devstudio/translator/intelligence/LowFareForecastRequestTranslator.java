package com.sabre.getthere.devstudio.translator.intelligence;

import com.sabre.getthere.devstudio.domain.intelligence.LowFareForecastRequest;
import com.sabre.getthere.devstudio.translator.RequestTranslatorHelper;

/**
 * User: Sunitha C
 * Date: 8/4/2015 12:38 PM
 */
public class LowFareForecastRequestTranslator {

   private RequestTranslatorHelper helper = new RequestTranslatorHelper();

   public String translate(LowFareForecastRequest lowFareForecastRequest) {
      String searchCriteria = "?" + "origin=" + lowFareForecastRequest.getOrigin() + helper.PARAMETER_SEPERATOR +
              "destination=" + lowFareForecastRequest.getDestination() + helper.PARAMETER_SEPERATOR +
              "departuredate=" + helper.getDateInStringFormat(lowFareForecastRequest.getDepartureDate()) + helper.PARAMETER_SEPERATOR +
              "returndate=" + helper.getDateInStringFormat(lowFareForecastRequest.getReturnDate()) ;
      return searchCriteria;
   }

}
