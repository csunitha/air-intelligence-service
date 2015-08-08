package com.sabre.getthere.devstudio.translator.airshopping;

import com.sabre.getthere.devstudio.domain.airshopping.FareInfo;
import com.sabre.getthere.devstudio.domain.airshopping.FaresInfo;
import com.sabre.getthere.devstudio.domain.airshopping.LeadPriceCalendarResponse;
import com.sabre.getthere.devstudio.translator.ResponseTranslatorHelper;
import flexjson.JSONDeserializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: Sunitha C
 * Date: 8/4/2015 4:34 PM
 */
public class LeadPriceCalendarResponseTranslator {

   private ResponseTranslatorHelper helper = new ResponseTranslatorHelper();

   public LeadPriceCalendarResponse translate(String response) {
      HashMap deserializedResponse = (HashMap) new JSONDeserializer().deserialize(response);
      String error = helper.getValue(deserializedResponse, "status");

      if(error != null) {
         return getLeadPriceCalendarResponseUpdatedWithErrorDetails(deserializedResponse);
      }
      return getLeadPriceCalendarResponse(deserializedResponse);

   }

   private LeadPriceCalendarResponse getLeadPriceCalendarResponseUpdatedWithErrorDetails(HashMap deserializedResponse) {
      LeadPriceCalendarResponse errorResponse = new LeadPriceCalendarResponse();
      errorResponse.setStatus(helper.SERVICE_CALL_STATUS_FAILURE);
      errorResponse.setErrorCode(helper.getValue(deserializedResponse, "errorCode"));
      errorResponse.setErrorMessage(helper.getValue(deserializedResponse, "message"));
      return errorResponse;
   }

   private LeadPriceCalendarResponse getLeadPriceCalendarResponse(HashMap deserializedResponse) {
      LeadPriceCalendarResponse response = new LeadPriceCalendarResponse();
      response.setStatus(helper.SERVICE_CALL_STATUS_SUCCESS);
      response.setOrigin(helper.getValue(deserializedResponse, "OriginLocation"));
      response.setDestination(helper.getValue(deserializedResponse, "DestinationLocation"));
      response.setFaresInfo(getFaresInfo(deserializedResponse, "FareInfo"));
      return response;
   }

   private List<FaresInfo> getFaresInfo(HashMap deserializedResponse, String key) {
      List<HashMap> listOfFares = (List<HashMap>) deserializedResponse.get(key);
      List<FaresInfo> faresInfoList = new ArrayList<>();
      for(HashMap fare : listOfFares) {
         if(!helper.getValue(fare, "CurrencyCode").equals("N/A") ) {
            FaresInfo faresInfo = new FaresInfo();
            faresInfo.setCurrencyCode(helper.getValue(fare, "CurrencyCode"));
            faresInfo.setLowestFare(getFareDetailsWithAirlineCode(fare, "LowestFare"));
            faresInfo.setLowestNonStopFare(getFareDetailsWithAirlineCode(fare, "LowestNonStopFare"));
            faresInfo.setDepartureDate(helper.getDate(fare, "DepartureDateTime"));
            faresInfo.setReturnDate(helper.getDate(fare, "ReturnDateTime"));
            faresInfoList.add(faresInfo);
         }
      }
      return faresInfoList ;
   }

   private FareInfo getFareDetailsWithAirlineCode(HashMap deserializedResponse, String fareName) {
      HashMap individualFare = (HashMap) deserializedResponse.get(fareName);
      FareInfo fareInfo = new FareInfo();
      fareInfo.setAirlineCodes(getAirlineCodes(individualFare));
      fareInfo.setFare(helper.getFareDetails(individualFare, "Fare"));
      return fareInfo;
   }

   private List<String> getAirlineCodes(HashMap fare) {
      List<String> airlineCodes = new ArrayList<>();
      airlineCodes.addAll((ArrayList) fare.get("AirlineCodes"));
      return airlineCodes;
   }

}
