package com.sabre.getthere.devstudio.services.intelligence;

import com.sabre.getthere.devstudio.domain.intelligence.LowFareForecastRequest;
import com.sabre.getthere.devstudio.domain.intelligence.LowFareForecastResponse;
import com.sabre.getthere.devstudio.services.ServiceHelper;
import com.sabre.getthere.devstudio.translator.intelligence.LowFareForecastRequestTranslator;
import com.sabre.getthere.devstudio.translator.intelligence.LowFareForecastResponseTranslator;

/**
 * User: Sunitha C
 * Date: 8/3/2015 4:17 PM
 */
public class LowFareForecastService {
   private LowFareForecastResponseTranslator responseTranslator = new LowFareForecastResponseTranslator();
   private LowFareForecastRequestTranslator requestTranslator = new LowFareForecastRequestTranslator();
   private ServiceHelper helper = new ServiceHelper();

   public LowFareForecastResponse execute(LowFareForecastRequest lowFareForecastRequest) {
      return responseTranslator.translate(
              helper.makeServiceCallToGetLowFareForecast(
                      requestTranslator.translate(lowFareForecastRequest), helper.makeServiceCallToGetAuthenticationToken()));
   }

}
