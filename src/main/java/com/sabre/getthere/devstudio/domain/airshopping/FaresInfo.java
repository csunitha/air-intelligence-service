package com.sabre.getthere.devstudio.domain.airshopping;

import java.util.GregorianCalendar;

/**
 * User: Sunitha C
 * Date: 8/4/2015 5:23 PM
 */
public class FaresInfo {

   private String currencyCode;
   private FareInfo lowestFare;
   private FareInfo lowestNonStopFare;
   private GregorianCalendar departureDate;
   private GregorianCalendar returnDate;

   public void setCurrencyCode(String currencyCode) {
      this.currencyCode = currencyCode;
   }

   public String getCurrencyCode() {
      return currencyCode;
   }

   public void setLowestFare(FareInfo lowestFare) {
      this.lowestFare = lowestFare;
   }

   public FareInfo getLowestFare() {
      return lowestFare;
   }

   public void setLowestNonStopFare(FareInfo lowestNonStopFare) {
      this.lowestNonStopFare = lowestNonStopFare;
   }

   public FareInfo getLowestNonStopFare() {
      return lowestNonStopFare;
   }

   public void setDepartureDate(GregorianCalendar departureDate) {
      this.departureDate = departureDate;
   }

   public GregorianCalendar getDepartureDate() {
      return departureDate;
   }

   public void setReturnDate(GregorianCalendar returnDate) {
      this.returnDate = returnDate;
   }

   public GregorianCalendar getReturnDate() {
      return returnDate;
   }
}
