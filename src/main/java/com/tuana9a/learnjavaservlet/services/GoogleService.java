package com.tuana9a.learnjavaservlet.services;

import com.google.gson.JsonObject;
import com.tuana9a.learnjavaservlet.config.AppConfig;
import com.tuana9a.learnjavaservlet.models.HttpRequestOption;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class GoogleService {

    private static final GoogleService instance = new GoogleService();
    private final HttpClientService httpClientService = HttpClientService.getInstance();

    private GoogleService() {

    }

    public static GoogleService getInstance() {
        return instance;
    }

    public String createAuthUrl() {
        AppConfig config = AppConfig.getInstance();

        return config.OAUTH_GOOGLE_AUTH_URL() +
                "?scope=email" +
                "&redirect_uri=" + config.OAUTH_GOOGLE_REDIRECT_URL() +
                "&response_type=code" +
                "&client_id=" + config.OAUTH_GOOGLE_APP_ID() +
                "&approval_prompt=force";
    }

    public String createAccessToken(String code) {
        AppConfig config = AppConfig.getInstance();
        String url = config.OAUTH_GOOGLE_GET_TOKEN_URL();
        List<NameValuePair> formData = new ArrayList<>();

        formData.add(new BasicNameValuePair("client_id", config.OAUTH_GOOGLE_APP_ID()));
        formData.add(new BasicNameValuePair("client_secret", config.OAUTH_GOOGLE_APP_SECRET()));
        formData.add(new BasicNameValuePair("code", code));
        formData.add(new BasicNameValuePair("redirect_uri", config.OAUTH_GOOGLE_REDIRECT_URL()));
        formData.add(new BasicNameValuePair("grant_type", "authorization_code"));

        HttpRequestOption option = HttpRequestOption.builder()
                .url(url)
                .formData(formData)
                .build();

        JsonObject response = httpClientService.post(option);
        return response.get("access_token").getAsString().replaceAll("\"", "");
    }

    public JsonObject getUserInfo(String accessToken) {
        AppConfig appConfig = AppConfig.getInstance();
        String url = appConfig.OAUTH_GOOGLE_GET_USER_INFO_URL() + accessToken;
        return httpClientService.get(HttpRequestOption.builder().url(url).build());
    }

}
