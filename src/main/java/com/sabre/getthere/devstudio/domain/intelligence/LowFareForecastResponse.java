package com.sabre.getthere.devstudio.domain.intelligence;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * User: Sunitha C
 * Date: 8/3/2015 4:40 PM
 */
public class LowFareForecastResponse {
   private String destination;
   private String origin;
   private GregorianCalendar departureDate;
   private GregorianCalendar returnDate;
   private BigDecimal highestPredictedFare;
   private BigDecimal lowestPredictedFare;
   private String recommendation;
   private BigDecimal lowestFare;
   private String currencyCode;
   private String status;
   private String errorCode;
   private String errorMessage;

   public String getDestination() {
      return destination;
   }

   public String getOrigin() {
      return origin;
   }

   public GregorianCalendar getDepartureDate() {
      return departureDate;
   }

   public GregorianCalendar getReturnDate() {
      return returnDate;
   }

   public BigDecimal getHighestPredictedFare() {
      return highestPredictedFare;
   }

   public BigDecimal getLowestPredictedFare() {
      return lowestPredictedFare;
   }

   public String getRecommendation() {
      return recommendation;
   }

   public BigDecimal getLowestFare() {
      return lowestFare;
   }


   public String getCurrencyCode() {
      return currencyCode;
   }

   public void setDestination(String destination) {
      this.destination = destination;
   }

   public void setOrigin(String origin) {
      this.origin = origin;
   }

   public void setDepartureDate(GregorianCalendar departureDate) {
      this.departureDate = departureDate;
   }

   public void setReturnDate(GregorianCalendar returnDate) {
      this.returnDate = returnDate;
   }

   public void setHighestPredictedFare(BigDecimal highestPredictedFare) {
      this.highestPredictedFare = highestPredictedFare;
   }

   public void setLowestPredictedFare(BigDecimal lowestPredictedFare) {
      this.lowestPredictedFare = lowestPredictedFare;
   }

   public void setRecommendation(String recommendation) {
      this.recommendation = recommendation;
   }

   public void setLowestFare(BigDecimal lowestFare) {
      this.lowestFare = lowestFare;
   }

   public void setCurrencyCode(String currencyCode) {
      this.currencyCode = currencyCode;
   }

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return status;
   }

   public void setErrorCode(String errorCode) {
      this.errorCode = errorCode;
   }

   public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
   }

   public String getErrorCode() {
      return errorCode;
   }

   public String getErrorMessage() {
      return errorMessage;
   }
}
