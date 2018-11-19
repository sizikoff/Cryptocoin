package ru.tihomirov.cryptocoin;

public class InAppBillingResources {
    private static final String RSA_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmTRr0VMxPwW6SSIWHzBPuzXWjkdQjzl3vKttE0AxRyu0vecHz9+6Vrhg0OSWUaAdGStprmNxtylTi0DyxdA6SoFVzZAKZOIju0l4nr9mmuFOC72WwicrU1z5Rs8rzv80dYHgDrVjqYPkqOs5+JpJV2X2tsRl0W0F8pkT30JZ3A0GdNxrscMFLEBpHjBhkppeZuIjY3L2Y6yjIvo1FnsDKFl0PqJJSiDozh3HcUCWnBMWc1IohHkkAlJYdUkQqLlI+BfR11lthbSm4YsO7tGafIUq6Jyp2SZDPXTZn8QWfzGLC9KS2k7718mbCw18U35E7cqMk1jz7so4qVQIDAQAB"; // Ваш `RSA` ключ из `Google Play Developer Console`
    private static final String MERCHANT_ID = "12538744805189849137";           // Ваш `MERCHANT_ID` из `Google Play Developer Console`
    private static final String SKU_DISABLE_ADS = "purchase_moneys";          // Ваш `product_id`, создается в `Google Play Developer Console`


    public static String getRsaKey() {
        return RSA_KEY;
    }

    public static String getMerchantId() {
        return MERCHANT_ID;
    }

    public static String getSKU_Disable_Ads() {
        return SKU_DISABLE_ADS;
    }
}
