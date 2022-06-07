package bestbuy.constants;


public class EndPoints {

    /**
     * Endpoints for PRODUCTS
     */
    public static final String GET_SINGLE_PRODUCT_BY_ID = "/products/{productID}";
    public static final String UPDATE_PRODUCT_BY_ID = "/products/{productID}";
    public static final String DELETE_PRODUCT_BY_ID = "/products/{productID}";

    /**
     * Endpoints for STORES
     */
    public static final String GET_SINGLE_STORE_BY_ID = "/stores/{storeID}";
    public static final String UPDATE_STORE_BY_ID = "/stores/{storeID}";
    public static final String DELETE_STORE_BY_ID = "/stores/{storeID}";

    /**
     * Endpoints for CATEGORIES
     */
    public static final String GET_SINGLE_CATEGORY_BY_ID = "/categories/{categoryID}";
    public static final String UPDATE_CATEGORY_BY_ID = "/categories/{categoryID}";
    public static final String DELETE_CATEGORY_BY_ID = "/categories/{categoryID}";

    /**
     * Endpoints for SERVICES
     */
    public static final String GET_SINGLE_SERVICE_BY_ID = "/services/{serviceID}";
    public static final String UPDATE_SERVICE_BY_ID = "/services/{serviceID}";
    public static final String DELETE_SERVICE_BY_ID = "/services/{serviceID}";
    /**
     * Endpoints for UTILITIES
     */
    public static final String GET_VERSION = "/version";
    public static final String GET_HEALTHCHECK = "/healthcheck";


}
