package com.tuana9a.learnjavaservlet.services;

import com.google.gson.JsonObject;
import com.tuana9a.learnjavaservlet.models.HttpRequestOption;
import com.tuana9a.learnjavaservlet.utils.IoUtils;
import com.tuana9a.learnjavaservlet.utils.JsonUtils;
import com.tuana9a.learnjavaservlet.utils.LogUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

public class HttpClientService {

    private static final HttpClientService instance = new HttpClientService();

    private HttpClientService() {

    }

    public static HttpClientService getInstance() {
        return instance;
    }

    //EXPLAIN: apache bá v lon, dùng global tiết kiệm request, port, resource
    private static final CloseableHttpClient client = HttpClients.createDefault();


    //SECTION: specification
    public boolean download(String url, String path) {
        try {
            HttpGet request = new HttpGet(url);
            return client.execute(request, response -> {
                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    inputStream = response.getEntity().getContent();
                    outputStream = new FileOutputStream(path);
                    IoUtils.getInstance().writeInputToOutput(inputStream, outputStream);
                    return true;

                } catch (Exception e) {
                    LogUtils.getLogger().error("write failed: " + path, e);
                    return false;

                } finally {
                    IoUtils.getInstance(). close(inputStream);
                    IoUtils.getInstance().close(outputStream);
                }
            });
        } catch (Exception e) {
            LogUtils.getLogger().error("download failed: " + url, e);
            return false;
        }
    }


    //SECTION: general purpose
    public <T> T get(HttpRequestOption option, Class<T> type) {
        try {
            HttpGet request = new HttpGet(option.getUrl());

            Header[] headers = option.getHeaders();
            if (headers != null) {
                request.setHeaders(headers);
            }

            HttpResponse response = client.execute(request);
            return JsonUtils.getInstance().fromJson(response.getEntity().getContent(), type);

        } catch (Exception e) {
            LogUtils.getLogger().error("request GET failed: " + option.getUrl(), e);
            return null;
        }
    }

    public <T> T post(HttpRequestOption option, Class<T> type) {
        try {
            HttpPost request = new HttpPost(option.getUrl());

            Header[] headers = option.getHeaders();
            if (headers != null) {
                request.setHeaders(headers);
            }

            JsonObject bodyJson = option.getBodyJson();
            List<NameValuePair> formData = option.getFormData();
            File file = option.getFile();

            if (bodyJson != null) {
                request.setEntity(new StringEntity(JsonUtils.getInstance().toJson(bodyJson)));
                request.setHeader("Content-Type", "application/json; charset=utf-8");

            } else if (formData != null) {
                request.setEntity(new UrlEncodedFormEntity(formData));
                request.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

            } else if (file != null) {
                HashMap<String, String> bodyFile = option.getBodyFile();
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                builder.addPart("file", new FileBody(file, ContentType.DEFAULT_BINARY));

                if (bodyFile != null) {
                    bodyFile.forEach(builder::addTextBody);
                }

                request.setEntity(builder.build());
            }

            HttpResponse response = client.execute(request);
            return JsonUtils.getInstance().fromJson(response.getEntity().getContent(), type);

        } catch (Exception e) {
            LogUtils.getLogger().error("request POST failed: " + option.getUrl(), e);
            return null;
        }
    }

    public <T> T put(HttpRequestOption option, Class<T> type) {
        try {
            HttpPut request = new HttpPut(option.getUrl());

            Header[] headers = option.getHeaders();
            if (headers != null) {
                request.setHeaders(headers);
            }

            JsonObject bodyJson = option.getBodyJson();
            List<NameValuePair> formData = option.getFormData();
            File file = option.getFile();

            if (bodyJson != null) {
                request.setEntity(new StringEntity(JsonUtils.getInstance().toJson(bodyJson)));
                request.setHeader("Content-Type", "application/json; charset=utf-8");

            } else if (formData != null) {
                request.setEntity(new UrlEncodedFormEntity(formData));
                request.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");

            } else if (file != null) {
                HashMap<String, String> bodyFile = option.getBodyFile();
                MultipartEntityBuilder builder = MultipartEntityBuilder.create();
                builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                builder.addPart("file", new FileBody(file, ContentType.DEFAULT_BINARY));

                if (bodyFile != null) {
                    bodyFile.forEach(builder::addTextBody);
                }

                request.setEntity(builder.build());
            }

            HttpResponse response = client.execute(request);
            return JsonUtils.getInstance().fromJson(response.getEntity().getContent(), type);

        } catch (Exception e) {
            LogUtils.getLogger().error("request PUT failed: " + option.getUrl(), e);
            return null;
        }
    }

    public <T> T delete(HttpRequestOption option, Class<T> type) {
        try {
            HttpDelete request = new HttpDelete(option.getUrl());

            Header[] headers = option.getHeaders();
            if (headers != null) {
                request.setHeaders(headers);
            }

            HttpResponse response = client.execute(request);
            return JsonUtils.getInstance().fromJson(response.getEntity().getContent(), type);

        } catch (Exception e) {
            LogUtils.getLogger().error("request DELETE failed", e);
            return null;
        }
    }


    //SECTION: json default
    public JsonObject get(HttpRequestOption option) {
        return get(option, JsonObject.class);
    }

    public JsonObject put(HttpRequestOption option) {
        return put(option, JsonObject.class);
    }

    public JsonObject post(HttpRequestOption option) {
        return post(option, JsonObject.class);
    }

    public JsonObject delete(HttpRequestOption option) {
        return delete(option, JsonObject.class);
    }

}
