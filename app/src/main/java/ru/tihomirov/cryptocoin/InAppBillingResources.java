package ru.tihomirov.cryptocoin;

public class InAppBillingResources {
    private static final String RSA_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkmbclGMixvxruz8WAyRxxBJEO5DxiV7ze9ycRlW3VKMOBPHZoBuohFXxucK5rvnOiHH+QDFALVF5aLmIbfIYO0C5jbMTcEjkmNLZWTT9hOwtA2imT2Y+PjZaIC3eegrtFWNWK4K+eqcTeS9s7+nZ3voUpqqfBuSaLaA9EDXhnrVJbe0gFBnpiIYTbBLdREyTXGh3LWc9wmb3dPDwaEqYoG4GXldMnr6nppeyeOBiAdLqdhhzLCIhFXMvfT8mqSVf5Q+k94FZxvbp5E4OhexSTtQwXiOuJssjHLA2lA0pz59fZyzX+o5VINvTqZZmyWtGULg+2xgJ19msvhLwIDAQAB"; // Ваш `RSA` ключ из `Google Play Developer Console`
    private static final String MERCHANT_ID = "12538744805189849137";           // Ваш `MERCHANT_ID` из `Google Play Developer Console`
    private static final String SKU_DISABLE_ADS = "purchase_money";          // Ваш `product_id`, создается в `Google Play Developer Console`


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
