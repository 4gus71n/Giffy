package com.kimboo.giffy.utils;

public class Constants {
    public static class System {
        public static final String DEVICE_WIDTH = "device-width";
        public static final String DEVICE_HEIGHT = "device-height";
        public static final String SHARED_PREFERENCE_NAME = "giffy-preferences";
        public static final String FIRST_INSTALLATION = "first-installation";
    }
    
    public static class User {
        public static final String OAUTH_USERNAME = "oauth-user";
    }

    public static class Server {

        public static final String REST_HOSTNAME = "http://10.0.2.2:8080/server/rest/";
        
    }

    public static class Service {
        
        public static class ServerUpdateReceiver {

            public static final String NEW_GIFS_INCOMING = "com.kimboo.giffy.NEW_GIFS_INCOMING";
            public static final String GIF_UPDATE_FAILED = "com.kimboo.giffy.GIF_UPDATE_FAILED";
            
        }
        
    }
}
