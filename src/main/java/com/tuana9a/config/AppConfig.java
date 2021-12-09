package com.tuana9a.config;

import com.tuana9a.utils.IoUtils;
import com.tuana9a.utils.LogUtils;
import lombok.ToString;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@ToString
public class AppConfig {
    public String OAUTH_GOOGLE_AUTH_URL;
    public String OAUTH_GOOGLE_APP_ID;
    public String OAUTH_GOOGLE_APP_SECRET;
    public String OAUTH_GOOGLE_GET_TOKEN_URL;
    public String OAUTH_GOOGLE_GET_USER_INFO_URL;
    public String OAUTH_GOOGLE_REDIRECT_URL;

    public String PATH_RESOURCE_DIR;

    public String SECURITY_JWT_SECRET;
    public String SECURITY_JWT_EXPIRE;
    public String SECURITY_COOKIE_EXPIRE;
    public String SECURITY_REJECT_MESSAGE;

    public int BUFFER_SIZE = 1024;                 // 1KB
    public long DEFAULT_EXPIRE_TIME = 604800000L;  // 1 weeks
    public String MULTIPART_BOUNDARY = "MULTIPART_BYTERANGES";
    public String ROOT_FOLDER;

    public String DATABASE_URL;
    public String DATABASE_USERNAME;
    public String DATABASE_PASSWORD;

    public Set<String> SECRETS;

    public boolean SHOW_SQL = true;

    public String EXPLORER_PREFIX = "/explorer";

    private AppConfig() {

    }

    private static final AppConfig instance = new AppConfig();

    public static AppConfig getInstance() {
        return instance;
    }

    public void load() {
        Properties properties = new Properties();
        Logger logger = LogUtils.getLogger();
        IoUtils ioUtils = IoUtils.getInstance();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("resource/app.conf.properties");
            properties.load(inputStream);

            OAUTH_GOOGLE_AUTH_URL = properties.getProperty("oauth.google.auth_url");
            OAUTH_GOOGLE_APP_ID = properties.getProperty("oauth.google.app_id");
            OAUTH_GOOGLE_APP_SECRET = properties.getProperty("oauth.google.app_secret");
            OAUTH_GOOGLE_GET_TOKEN_URL = properties.getProperty("oauth.google.get_token_url");
            OAUTH_GOOGLE_GET_USER_INFO_URL = properties.getProperty("oauth.google.get_user_info_url");
            OAUTH_GOOGLE_REDIRECT_URL = properties.getProperty("oauth.google.redirect_url");

            PATH_RESOURCE_DIR = properties.getProperty("path.resource.dir");

            SECURITY_JWT_SECRET = properties.getProperty("security.jwt.secret");
            SECURITY_JWT_EXPIRE = properties.getProperty("security.jwt.expire");
            SECURITY_COOKIE_EXPIRE = properties.getProperty("security.cookie.expire");
            SECURITY_REJECT_MESSAGE = properties.getProperty("security.reject_message");

            SECRETS = Arrays.stream(properties.getProperty("security.admins").split(",")).collect(Collectors.toSet());

            ROOT_FOLDER = properties.getProperty("path.upload.dir");

            DATABASE_URL = properties.getProperty("database.url");
            DATABASE_USERNAME = properties.getProperty("database.username");
            DATABASE_PASSWORD = properties.getProperty("database.password");
        } catch (Exception e) {
            logger.error("Failed to load app-config.json", e);
        }
        ioUtils.close(inputStream);
    }
}
