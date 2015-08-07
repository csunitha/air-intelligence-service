package com.sabre.getthere.devstudio.translator.intelligence;

import com.sabre.getthere.devstudio.domain.intelligence.LowFareForecastRequest;
import com.sabre.getthere.devstudio.translator.AbstractRequestTranslator;

/**
 * User: Sunitha C
 * Date: 8/4/2015 12:38 PM
 */
public class LowFareForecastRequestTranslator extends AbstractRequestTranslator{

   public String translate(LowFareForecastRequest lowFareForecastRequest) {
      String searchCriteria = "?" + "origin=" + lowFareForecastRequest.getOrigin() + PARAMETER_SEPERATOR +
                                    "destination=" + lowFareForecastRequest.getDestination() + PARAMETER_SEPERATOR +
                                    "departuredate=" + getDateInStringFormat(lowFareForecastRequest.getDepartureDate()) + PARAMETER_SEPERATOR +
                                    "returndate=" + getDateInStringFormat(lowFareForecastRequest.getReturnDate()) ;
      return searchCriteria;
   }

}
