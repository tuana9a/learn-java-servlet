package com.tuana9a.learn.java.servlet.models;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.io.File;
import java.util.HashMap;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HttpRequest {
    private String url;
    private Header[] headers;
    private JsonObject bodyJson;
    private List<NameValuePair> formData;
    private HashMap<String, String> bodyFile;
    private File file;
}
