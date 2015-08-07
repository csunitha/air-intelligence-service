package com.sabre.getthere.devstudio.domain.airshopping;

import java.util.List;

/**
 * User: Sunitha C
 * Date: 8/4/2015 4:20 PM
 */
public class LeadPriceCalendarResponse {
   private String status;
   private String origin;
   private String destination;
   private List<FaresInfo> faresInfo;
   private String errorCode;
   private String errorMessage;

   public void setStatus(String status) {
      this.status = status;
   }

   public String getStatus() {
      return status;
   }

   public void setOrigin(String origin) {
      this.origin = origin;
   }

   public String getOrigin() {
      return origin;
   }

   public void setDestination(String destination) {
      this.destination = destination;
   }

   public String getDestination() {
      return destination;
   }

   public void setFaresInfo(List<FaresInfo> faresInfo) {
      this.faresInfo = faresInfo;
   }

   public List<FaresInfo> getFaresInfo() {
      return faresInfo;
   }

   public void setErrorCode(String errorCode) {
      this.errorCode = errorCode;
   }

   public String getErrorCode() {
      return errorCode;
   }

   public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
   }

   public String getErrorMessage() {
      return errorMessage;
   }
}
