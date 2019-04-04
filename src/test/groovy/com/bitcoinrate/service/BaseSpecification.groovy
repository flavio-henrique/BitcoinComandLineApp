package com.bitcoinrate.service

import com.google.api.client.http.LowLevelHttpRequest
import com.google.api.client.http.LowLevelHttpResponse
import com.google.api.client.json.Json
import com.google.api.client.testing.http.MockHttpTransport
import com.google.api.client.testing.http.MockLowLevelHttpRequest
import com.google.api.client.testing.http.MockLowLevelHttpResponse
import spock.lang.Specification

class BaseSpecification extends Specification {

    def getMockHttpTransport() {
        return new MockHttpTransport() {
            @Override
            public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
                return new MockLowLevelHttpRequest() {
                    @Override
                    public LowLevelHttpResponse execute() throws IOException {
                        MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
                        response.addHeader("custom_header", "value");
                        response.setStatusCode(200);
                        response.setContentType(Json.MEDIA_TYPE);
                        response.setContent("{\"time\": { \"updated\": \"Apr 4, 2019 04:52:00 UTC\"  }," +
                                "    \"disclaimer\": \"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\"," +
                                "    \"chartName\": \"Bitcoin\"," +
                                "    \"bpi\": {" +
                                "        \"USD\": {" +
                                "            \"code\": \"USD\"," +
                                "            \"symbol\": \"&#36;\"," +
                                "            \"rate\": \"5,008.2033\"," +
                                "            \"description\": \"United States Dollar\"," +
                                "            \"rate_float\": 5008.2033" +
                                "        }" +
                                "    }" +
                                "}");
                        return response
                    }
                }
            }
        }
    }

}
