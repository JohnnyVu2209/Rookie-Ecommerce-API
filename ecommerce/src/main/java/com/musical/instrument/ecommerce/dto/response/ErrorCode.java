package com.musical.instrument.ecommerce.dto.response;

public class ErrorCode {
    //Product
    public static final String ERR_PRODUCT_LIST_LOAD_FAIL = "PRODUCT_LIST_LOAD_FAIL";
    public static final String ERR_PRODUCT_LOAD_FAIL = "PRODUCT_LOAD_FAIL";
    public static final String ERR_PRODUCT_NOT_FOUND = "PRODUCT_NOT_FOUND";
    public static final String ERR_CREATE_PRODUCT_FAIL = "CREATE_PRODUCT_FAIL";
    public static final String ERR_UPDATE_PRODUCT_FAIL = "UPDATE_PRODUCT_FAIL";
    public static final String ERR_PRODUCT_DELETED_FAIL = "DELETE_PRODUCT_FAIL";

    //Category
    public static final String ERR_CATEGORY_LIST_LOAD_FAIL = "CATEGORY_LIST_LOAD_FAIL";
    public static final String ERR_CATEGORY_NOT_FOUND = "CATEGORY_NOT_FOUND";
    public static final String ERR_CATEGORY_ALREADY_EXISTS = "CATEGORY_ALREADY_EXISTS";
    public static final String ERR_CREATE_CATEGORY_FAIL = "CREATE_CATEGORY_FAIL";
    public static final String ERR_UPDATE_CATEGORY_FAIL = "UPDATE_CATEGORY_FAIL";
    public static final String ERR_DELETE_CATEGORY_FAIL = "DELETE_CATEGORY_FAIL";

    //Brand
    public static final String ERR_BRAND_NOT_FOUND = "BRAND_NOT_FOUND";

    //Auth
    public static final String ERR_USERNAME_ALREADY_TAKEN = "USERNAME_ALREADY_TAKEN";
    public static final String ERR_EMAIL_ALREADY_TAKEN = "EMAIL_ALREADY_TAKEN";
    public static final String ERR_SIGN_UP_FAIL = "SIGN_UP_FAIL";
    public static final String ERR_USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String ERR_UPDATE_USER_FAIL = "UPDATE_USER_FAIL";
    public static final String ERR_UPDATE_ROLE_FAIL = "UPDATE_ROLE_FAIL";
    public static final String ERR_DELETE_ROLE_FAIL = "DELETE_ROLE_FAIL";
    public static final String ERR_LOAD_USER_FAIL = "LOAD_USER_FAIL";

    //Role
    public static final String ERR_ROLE_NOT_FOUND = "ROLE_NOT_FOUND";

    //Cart
    public static final String ERR_ADD_ITEM_FAIL = "ADD_ITEM_FAIL";
    public static final String ERR_CART_NOT_HAVE_ITEM = "CART_NOT_HAVE_ITEM";
    public static final String ERR_ITEM_NOT_EXIST_IN_CART = "ITEM_NOT_EXIST_IN_CART";
    public static final String ERR_REMOVE_ITEM_FAIL = "REMOVE_ITEM_FAIL";
}
