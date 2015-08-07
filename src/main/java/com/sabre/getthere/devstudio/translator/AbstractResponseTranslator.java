package com.sabre.getthere.devstudio.translator;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * User: Sunitha C
 * Date: 8/4/2015 5:04 PM
 */
public abstract class AbstractResponseTranslator {
   protected static final String SERVICE_CALL_STATUS_SUCCESS = "SUCCESS";
   protected static final String SERVICE_CALL_STATUS_FAILURE = "FAILURE";

   protected String getValue(HashMap deserializedResponse, String key) {
      return (String) deserializedResponse.get(key);
   }

   protected BigDecimal getFareDetails(HashMap deserializedResponse, String fareName) {
      return new BigDecimal(String.valueOf(deserializedResponse.get(fareName)));
   }

   protected GregorianCalendar getDate(HashMap deserializedResponse, String dateKey) {
      String dateValue = getValue(deserializedResponse, dateKey);
      GregorianCalendar date = new GregorianCalendar();
      int year = Integer.parseInt(StringUtils.mid(dateValue, 0, 4));
      int month = Integer.parseInt(StringUtils.mid(dateValue, 5, 2));
      int day = Integer.parseInt(StringUtils.mid(dateValue, 8, 2));
      date.set(year, month, day);
      return date;
   }
}
