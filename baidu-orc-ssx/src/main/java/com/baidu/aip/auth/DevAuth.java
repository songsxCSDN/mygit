
package com.baidu.aip.auth;

import com.baidu.aip.http.AipHttpClient;
import com.baidu.aip.http.AipRequest;
import com.baidu.aip.http.AipResponse;
import com.baidu.aip.util.AipClientConfiguration;
import com.baidu.aip.util.AipClientConst;
import com.baidu.aip.util.Util;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;

public class DevAuth {

    /**
     * get access_token from openapi
     * @param apiKey API key from console
     * @param secretKey Secret Key from console
     * @param config network config settings
     * @return JsonObject of response from OAuth server
     */
    public static JSONObject oauth(String apiKey, String secretKey, AipClientConfiguration config) {
        try {
            AipRequest request = new AipRequest();
            request.setUri(new URI(AipClientConst.OAUTH_URL));
            request.addBody("grant_type", "client_credentials");
            request.addBody("client_id", apiKey);
            request.addBody("client_secret", secretKey);
            request.setConfig(config);
            AipResponse response = AipHttpClient.post(request);
            String res = response.getBodyStr();
            int statusCode = response.getStatus();
            if (res != null && !res.equals("")) {
                return new JSONObject(res);
            }
            else {
                return Util.getGeneralError(statusCode, "Server response code: " + statusCode);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }
}
