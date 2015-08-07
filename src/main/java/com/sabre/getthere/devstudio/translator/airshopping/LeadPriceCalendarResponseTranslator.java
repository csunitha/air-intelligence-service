package com.sabre.getthere.devstudio.translator.airshopping;

import com.sabre.getthere.devstudio.domain.airshopping.FareInfo;
import com.sabre.getthere.devstudio.domain.airshopping.FaresInfo;
import com.sabre.getthere.devstudio.domain.airshopping.LeadPriceCalendarResponse;
import com.sabre.getthere.devstudio.translator.AbstractResponseTranslator;
import flexjson.JSONDeserializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: Sunitha C
 * Date: 8/4/2015 4:34 PM
 */
public class LeadPriceCalendarResponseTranslator extends AbstractResponseTranslator {

   public LeadPriceCalendarResponse translate(String response) {
      HashMap deserializedResponse = (HashMap) new JSONDeserializer().deserialize(response);
      String error = getValue(deserializedResponse, "status");

      if(error != null) {
         return getLeadPriceCalendarResponseUpdatedWithErrorDetails(deserializedResponse);
      }
      return getLeadPriceCalendarResponse(deserializedResponse);

   }

   private LeadPriceCalendarResponse getLeadPriceCalendarResponseUpdatedWithErrorDetails(HashMap deserializedResponse) {
      LeadPriceCalendarResponse errorResponse = new LeadPriceCalendarResponse();
      errorResponse.setStatus(SERVICE_CALL_STATUS_FAILURE);
      errorResponse.setErrorCode(getValue(deserializedResponse, "errorCode"));
      errorResponse.setErrorMessage(getValue(deserializedResponse, "message"));
      return errorResponse;
   }

   private LeadPriceCalendarResponse getLeadPriceCalendarResponse(HashMap deserializedResponse) {
      LeadPriceCalendarResponse response = new LeadPriceCalendarResponse();
      response.setStatus(SERVICE_CALL_STATUS_SUCCESS);
      response.setOrigin(getValue(deserializedResponse, "OriginLocation"));
      response.setDestination(getValue(deserializedResponse, "DestinationLocation"));
      response.setFaresInfo(getFaresInfo(deserializedResponse, "FareInfo"));
      return response;
   }

   private List<FaresInfo> getFaresInfo(HashMap deserializedResponse, String key) {
      List<HashMap> listOfFares = (List<HashMap>) deserializedResponse.get(key);
      List<FaresInfo> faresInfoList = new ArrayList<>();
      for(HashMap fare : listOfFares) {
         if(!getValue(fare, "CurrencyCode").equals("N/A") ) {
            FaresInfo faresInfo = new FaresInfo();
            faresInfo.setCurrencyCode(getValue(fare, "CurrencyCode"));
            faresInfo.setLowestFare(getFareDetailsWithAirlineCode(fare, "LowestFare"));
            faresInfo.setLowestNonStopFare(getFareDetailsWithAirlineCode(fare, "LowestNonStopFare"));
            faresInfo.setDepartureDate(getDate(fare, "DepartureDateTime"));
            faresInfo.setReturnDate(getDate(fare, "ReturnDateTime"));
            faresInfoList.add(faresInfo);
         }
      }
      return faresInfoList ;
   }

   private FareInfo getFareDetailsWithAirlineCode(HashMap deserializedResponse, String fareName) {
      HashMap individualFare = (HashMap) deserializedResponse.get(fareName);
      FareInfo fareInfo = new FareInfo();
      fareInfo.setAirlineCodes(getAirlineCodes(individualFare));
      fareInfo.setFare(getFareDetails(individualFare, "Fare"));
      return fareInfo;
   }

   private List<String> getAirlineCodes(HashMap fare) {
      List<String> airlineCodes = new ArrayList<>();
      airlineCodes.addAll((ArrayList) fare.get("AirlineCodes"));
      return airlineCodes;
   }

}
