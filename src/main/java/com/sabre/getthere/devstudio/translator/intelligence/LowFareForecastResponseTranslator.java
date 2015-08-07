package com.sabre.getthere.devstudio.translator.intelligence;

import com.sabre.getthere.devstudio.domain.intelligence.LowFareForecastResponse;
import com.sabre.getthere.devstudio.translator.AbstractResponseTranslator;
import flexjson.JSONDeserializer;

import java.util.HashMap;

/**
 * User: Sunitha C
 * Date: 8/4/2015 12:56 PM
 */
public class LowFareForecastResponseTranslator extends AbstractResponseTranslator {

   public LowFareForecastResponse translate(String response) {
      HashMap deserializedResponse = (HashMap) new JSONDeserializer().deserialize(response);

      String error = getValue(deserializedResponse, "status");
      if(error != null) {
         return getLowFareForecastResponseUpdatedWithErrorDetails(deserializedResponse);
      }
      return getLowFareForecastResponse(deserializedResponse);
   }

   private LowFareForecastResponse getLowFareForecastResponse(HashMap deserializedResponse) {
      LowFareForecastResponse lowFareForecast = new LowFareForecastResponse();
      lowFareForecast.setStatus(SERVICE_CALL_STATUS_SUCCESS);
      lowFareForecast.setOrigin(getValue(deserializedResponse, "OriginLocation"));
      lowFareForecast.setDestination(getValue(deserializedResponse, "DestinationLocation"));
      lowFareForecast.setRecommendation(getValue(deserializedResponse, "Recommendation"));
      lowFareForecast.setCurrencyCode(getValue(deserializedResponse, "CurrencyCode"));
      HashMap forecast = (HashMap) deserializedResponse.get("Forecast");
      lowFareForecast.setHighestPredictedFare(getFareDetails(forecast, "HighestPredictedFare"));
      lowFareForecast.setLowestPredictedFare(getFareDetails(forecast, "LowestPredictedFare"));
      lowFareForecast.setLowestFare(getFareDetails(deserializedResponse, "LowestFare"));
      lowFareForecast.setDepartureDate(getDate(deserializedResponse, "DepartureDateTime"));
      lowFareForecast.setReturnDate(getDate(deserializedResponse, "ReturnDateTime"));
      return lowFareForecast;
   }

   private LowFareForecastResponse getLowFareForecastResponseUpdatedWithErrorDetails(HashMap deserializedResponse) {
      LowFareForecastResponse errorResponse = new LowFareForecastResponse();
      errorResponse.setStatus(SERVICE_CALL_STATUS_FAILURE);
      errorResponse.setErrorCode(getValue(deserializedResponse, "errorCode"));
      errorResponse.setErrorMessage(getValue(deserializedResponse, "message"));
      return errorResponse;
   }

}
