package com.tuana9a.learnjavaservlet.config;

import com.tuana9a.learnjavaservlet.utils.IoUtils;
import com.tuana9a.learnjavaservlet.utils.LogUtils;

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
    public Properties properties;

    public String OAUTH_GOOGLE_AUTH_URL() {
        return properties.getProperty("oauth.google.auth_url");
    }

    public String OAUTH_GOOGLE_APP_ID() {
        return properties.getProperty("oauth.google.app_id");
    }

    public String OAUTH_GOOGLE_APP_SECRET() {
        return properties.getProperty("oauth.google.app_secret");
    }

    public String OAUTH_GOOGLE_GET_TOKEN_URL() {
        return properties.getProperty("oauth.google.get_token_url");
    };

    public String OAUTH_GOOGLE_GET_USER_INFO_URL() {
        return properties.getProperty("oauth.google.get_user_info_url");
    };

    public String OAUTH_GOOGLE_REDIRECT_URL() {
        return properties.getProperty("oauth.google.redirect_url");
    };

    public String SECURITY_JWT_SECRET() {
        return properties.getProperty("security.jwt.secret");
    };

    public String SECURITY_JWT_EXPIRE() {
        return properties.getProperty("security.jwt.expire");
    };

    public String SECURITY_COOKIE_EXPIRE() {
        return properties.getProperty("security.cookie.expire");
    };

    public String SECURITY_REJECT_MESSAGE() {
        return properties.getProperty("security.reject_message");
    };

    public String ROOT_FOLDER() {
        return properties.getProperty("path.root");
    };

    public String DATABASE_URL() {
        return properties.getProperty("database.url");
    };

    public String DATABASE_USERNAME() {
        return properties.getProperty("database.username");
    };

    public String DATABASE_PASSWORD() {
        return properties.getProperty("database.password");
    };

    public String HIBERNATE_CONFIG_PATH() {
        return properties.getProperty("hibernate.conf");
    };

    public Set<String> SECRETS;

    public boolean SHOW_SQL = true;

    public int BUFFER_SIZE = 1024; // 1KB
    public long DEFAULT_EXPIRE_TIME = 604800000L; // 1 weeks
    public String MULTIPART_BOUNDARY = "MULTIPART_BYTERANGES";

    public String EXPLORER_PREFIX = "/explorer";

    private AppConfig() {

    }

    private static final AppConfig instance = new AppConfig();

    public static AppConfig getInstance() {
        return instance;
    }

    public void load() {
        properties = new Properties();
        Logger logger = LogUtils.getLogger();
        IoUtils ioUtils = IoUtils.getInstance();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("resource/app.conf.properties");
            properties.load(inputStream);
            SECRETS = Arrays.stream(properties.getProperty("security.secrets").split(",")).collect(Collectors.toSet());
        } catch (Exception e) {
            logger.error("Failed to load app-config.json", e);
        }
        ioUtils.close(inputStream);
    }
}
