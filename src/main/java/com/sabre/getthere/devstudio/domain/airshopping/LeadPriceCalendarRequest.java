package com.sabre.getthere.devstudio.domain.airshopping;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * User: Sunitha C
 * Date: 8/4/2015 4:20 PM
 */
public class LeadPriceCalendarRequest {
   private String origin;
   private String destination;
   private GregorianCalendar departureDate;
   private List<Integer> lengthOfStay;

   public String getOrigin() {
      return origin;
   }

   public void setOrigin(String origin) {
      this.origin = origin;
   }

   public String getDestination() {
      return destination;
   }

   public void setDestination(String destination) {
      this.destination = destination;
   }

   public GregorianCalendar getDepartureDate() {
      return departureDate;
   }

   public void setDepartureDate(GregorianCalendar departureDate) {
      this.departureDate = departureDate;
   }

   public List<Integer> getLengthOfStay() {
      return lengthOfStay;
   }

   public void setLengthOfStay(List<Integer> lengthOfStay) {
      this.lengthOfStay = lengthOfStay;
   }
}
