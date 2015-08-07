package com.sabre.getthere.devstudio.domain.intelligence;

import java.util.GregorianCalendar;

/**
 * User: Sunitha C
 * Date: 8/3/2015 4:40 PM
 */
public class LowFareForecastRequest {
   private String origin;
   private String destination;
   private GregorianCalendar departureDate;
   private GregorianCalendar returnDate;

   public void setOrigin(String origin) {
      this.origin = origin;
   }

   public void setDestination(String destination) {
      this.destination = destination;
   }

   public void setDepartureDate(GregorianCalendar departureDate) {
      this.departureDate = departureDate;
   }

   public void setReturnDate(GregorianCalendar returnDate) {
      this.returnDate = returnDate;
   }

   public String getOrigin() {
      return origin;
   }

   public String getDestination() {
      return destination;
   }

   public GregorianCalendar getDepartureDate() {
      return departureDate;
   }

   public GregorianCalendar getReturnDate() {
      return returnDate;
   }
}
