package com.tuana9a.services;

import com.google.gson.JsonObject;
import com.tuana9a.config.AppConfig;
import com.tuana9a.models.HttpRequestOption;
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
        AppConfig appConfig = AppConfig.getInstance();

        return appConfig.OAUTH_GOOGLE_AUTH_URL +
                "?scope=email" +
                "&redirect_uri=" + appConfig.OAUTH_GOOGLE_REDIRECT_URL +
                "&response_type=code" +
                "&client_id=" + appConfig.OAUTH_GOOGLE_APP_ID +
                "&approval_prompt=force";
    }

    public String createAccessToken(String code) {
        AppConfig appConfig = AppConfig.getInstance();
        String url = appConfig.OAUTH_GOOGLE_GET_TOKEN_URL;
        List<NameValuePair> formData = new ArrayList<>();

        formData.add(new BasicNameValuePair("client_id", appConfig.OAUTH_GOOGLE_APP_ID));
        formData.add(new BasicNameValuePair("client_secret", appConfig.OAUTH_GOOGLE_APP_SECRET));
        formData.add(new BasicNameValuePair("code", code));
        formData.add(new BasicNameValuePair("redirect_uri", appConfig.OAUTH_GOOGLE_REDIRECT_URL));
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
        String url = appConfig.OAUTH_GOOGLE_GET_USER_INFO_URL + accessToken;
        return httpClientService.get(HttpRequestOption.builder().url(url).build());
    }

}
