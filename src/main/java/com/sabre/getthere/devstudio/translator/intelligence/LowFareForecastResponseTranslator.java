package com.sabre.getthere.devstudio.translator.intelligence;

import com.sabre.getthere.devstudio.domain.intelligence.LowFareForecastResponse;
import com.sabre.getthere.devstudio.translator.ResponseTranslatorHelper;
import flexjson.JSONDeserializer;

import java.util.HashMap;

/**
 * User: Sunitha C
 * Date: 8/4/2015 12:56 PM
 */
public class LowFareForecastResponseTranslator {

   private ResponseTranslatorHelper helper = new ResponseTranslatorHelper();

   public LowFareForecastResponse translate(String response) {
      HashMap deserializedResponse = (HashMap) new JSONDeserializer().deserialize(response);

      String error = helper.getValue(deserializedResponse, "status");
      if(error != null) {
         return getLowFareForecastResponseUpdatedWithErrorDetails(deserializedResponse);
      }
      return getLowFareForecastResponse(deserializedResponse);
   }

   private LowFareForecastResponse getLowFareForecastResponse(HashMap deserializedResponse) {
      LowFareForecastResponse lowFareForecast = new LowFareForecastResponse();
      lowFareForecast.setStatus(helper.SERVICE_CALL_STATUS_SUCCESS);
      lowFareForecast.setOrigin(helper.getValue(deserializedResponse, "OriginLocation"));
      lowFareForecast.setDestination(helper.getValue(deserializedResponse, "DestinationLocation"));
      lowFareForecast.setRecommendation(helper.getValue(deserializedResponse, "Recommendation"));
      lowFareForecast.setCurrencyCode(helper.getValue(deserializedResponse, "CurrencyCode"));
      HashMap forecast = (HashMap) deserializedResponse.get("Forecast");
      lowFareForecast.setHighestPredictedFare(helper.getFareDetails(forecast, "HighestPredictedFare"));
      lowFareForecast.setLowestPredictedFare(helper.getFareDetails(forecast, "LowestPredictedFare"));
      lowFareForecast.setLowestFare(helper.getFareDetails(deserializedResponse, "LowestFare"));
      lowFareForecast.setDepartureDate(helper.getDate(deserializedResponse, "DepartureDateTime"));
      lowFareForecast.setReturnDate(helper.getDate(deserializedResponse, "ReturnDateTime"));
      return lowFareForecast;
   }

   private LowFareForecastResponse getLowFareForecastResponseUpdatedWithErrorDetails(HashMap deserializedResponse) {
      LowFareForecastResponse errorResponse = new LowFareForecastResponse();
      errorResponse.setStatus(helper.SERVICE_CALL_STATUS_FAILURE);
      errorResponse.setErrorCode(helper.getValue(deserializedResponse, "errorCode"));
      errorResponse.setErrorMessage(helper.getValue(deserializedResponse, "message"));
      return errorResponse;
   }

}
