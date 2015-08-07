package com.sabre.getthere.devstudio.services.intelligence;

import com.sabre.getthere.devstudio.services.AbstractService;
import com.sabre.getthere.devstudio.domain.intelligence.LowFareForecastRequest;
import com.sabre.getthere.devstudio.domain.intelligence.LowFareForecastResponse;
import com.sabre.getthere.devstudio.translator.intelligence.LowFareForecastRequestTranslator;
import com.sabre.getthere.devstudio.translator.intelligence.LowFareForecastResponseTranslator;

/**
 * User: Sunitha C
 * Date: 8/3/2015 4:17 PM
 */
public class LowFareForecastService extends AbstractService {
   private LowFareForecastResponseTranslator responseTranslator = new LowFareForecastResponseTranslator();
   private LowFareForecastRequestTranslator requestTranslator = new LowFareForecastRequestTranslator();

   public LowFareForecastResponse execute(LowFareForecastRequest lowFareForecastRequest) {
      return responseTranslator.translate(
              makeServiceCallToGetLowFareForecast(
                      requestTranslator.translate(lowFareForecastRequest), makeServiceCallToGetAuthenticationToken()));
   }

}
