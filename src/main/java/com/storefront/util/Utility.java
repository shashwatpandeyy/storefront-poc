package com.storefront.util;

import java.util.UUID;

public class Utility {
    public static class Regex {
        public static final String NAME = "^[A-Za-z ]+$";
        public static final String EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";
        public static final String PHONE_NUMBER = "^\\d{3}-\\d{3}-\\d{4}$";
        public static final String CREDIT_CARD = "^\\d{19}$";
    }

    public static String generateOrderId() {
        return UUID.randomUUID().toString().split("-")[0];
    }
}
