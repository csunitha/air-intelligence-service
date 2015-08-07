package com.sabre.getthere.devstudio.domain.airshopping;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: Sunitha C
 * Date: 8/5/2015 1:54 PM
 */
public class FareInfo {
   private BigDecimal fare;
   private List<String> airlineCodes;

   public void setFare(BigDecimal fare) {
      this.fare = fare;
   }

   public BigDecimal getFare() {
      return fare;
   }

   public void setAirlineCodes(List<String> airlineCodes) {
      this.airlineCodes = airlineCodes;
   }

   public List<String> getAirlineCodes() {
      return airlineCodes;
   }
}
