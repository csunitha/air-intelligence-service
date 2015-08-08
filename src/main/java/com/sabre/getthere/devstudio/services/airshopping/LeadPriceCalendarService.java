package com.sabre.getthere.devstudio.services.airshopping;

import com.sabre.getthere.devstudio.domain.airshopping.FaresInfo;
import com.sabre.getthere.devstudio.domain.airshopping.LeadPriceCalendarRequest;
import com.sabre.getthere.devstudio.domain.airshopping.LeadPriceCalendarResponse;
import com.sabre.getthere.devstudio.services.ServiceHelper;
import com.sabre.getthere.devstudio.translator.airshopping.LeadPriceCalendarRequestTranslator;
import com.sabre.getthere.devstudio.translator.airshopping.LeadPriceCalendarResponseTranslator;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

/**
 * User: Sunitha C
 * Date: 8/4/2015 4:18 PM
 */
public class LeadPriceCalendarService {
   private static final int MILLI_SECONDS_PER_DAY = 24 * 60 * 60 * 1000;
   private LeadPriceCalendarRequestTranslator requestTranslator = new LeadPriceCalendarRequestTranslator();
   private LeadPriceCalendarResponseTranslator responseTranslator = new LeadPriceCalendarResponseTranslator();
   private ServiceHelper serviceHelper = new ServiceHelper();

   public LeadPriceCalendarResponse execute(LeadPriceCalendarRequest leadPriceCalendarRequest) {
      return responseTranslator.translate(
              serviceHelper.makeServiceCallToGetLeadPriceCalendar(
                      requestTranslator.translate(leadPriceCalendarRequest), serviceHelper.makeServiceCallToGetAuthenticationToken()));
   }

   public LeadPriceCalendarResponse execute(String origin, String destination, GregorianCalendar departureDate, GregorianCalendar returnDate) {
      LeadPriceCalendarRequest leadPriceCalendarRequest = new LeadPriceCalendarRequest();
      leadPriceCalendarRequest.setOrigin(origin);
      leadPriceCalendarRequest.setDestination(destination);

      List<Integer> numberOfDays = new ArrayList<>();
      numberOfDays.add((int) getNumberOfDays(departureDate, returnDate));
      leadPriceCalendarRequest.setLengthOfStay(numberOfDays);

      LeadPriceCalendarResponse response = responseTranslator.translate(
              serviceHelper.makeServiceCallToGetLeadPriceCalendar(
                      requestTranslator.translate(leadPriceCalendarRequest), serviceHelper.makeServiceCallToGetAuthenticationToken()));

      Iterator<FaresInfo> faresInfoIterator = response.getFaresInfo().iterator();

      while(faresInfoIterator.hasNext()) {
         FaresInfo faresInfo = faresInfoIterator.next();
         if (isFaresInfoDepartureDateEarlierThanRequestedDepartureDate(departureDate, faresInfo.getDepartureDate())) {
            faresInfoIterator.remove();
         }
      }
      return response;
   }

   private boolean isFaresInfoDepartureDateEarlierThanRequestedDepartureDate(GregorianCalendar requestDepartureDate, GregorianCalendar faresInfoDepartureDate) {
      return faresInfoDepartureDate.getTimeInMillis() - requestDepartureDate.getTimeInMillis() > 0 ;
   }

   private long getNumberOfDays(GregorianCalendar departureDate, GregorianCalendar returnDate) {
      return (returnDate.getTimeInMillis() -  departureDate.getTimeInMillis())  / MILLI_SECONDS_PER_DAY;
   }
}
