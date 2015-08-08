package com.sabre.getthere.devstudio.translator;

import org.apache.commons.lang3.StringUtils;

import java.util.GregorianCalendar;

/**
 * User: Sunitha C
 * Date: 8/8/2015 8:56 PM
 */
public class RequestTranslatorHelper {
   private static final int STRING_SIZE_TWO = 2;
   private static final String DATE_SEPERATOR = "-";
   private static final String PAD_WITH_ZERO = "0";
   public static final String PARAMETER_SEPERATOR = "&";

   public String getDateInStringFormat(GregorianCalendar date) {
      return String.valueOf(date.get(GregorianCalendar.YEAR)) + DATE_SEPERATOR +
              getStringPaddedWithZeroIfSizeNotTwo(date.get(GregorianCalendar.MONTH)) + DATE_SEPERATOR +
              getStringPaddedWithZeroIfSizeNotTwo(date.get(GregorianCalendar.DATE));
   }

   private String getStringPaddedWithZeroIfSizeNotTwo(int value) {
      String stringValue = String.valueOf(value);
      return stringValue.length() == STRING_SIZE_TWO ? stringValue : StringUtils.rightPad(PAD_WITH_ZERO, STRING_SIZE_TWO, stringValue) ;
   }

}
