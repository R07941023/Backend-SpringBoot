package com.dev.constant;

public final class CommConstant {
    
    private CommConstant(){
    }

    public static final int RET_CD_SUCCESS = 0;
    public static final int RET_CD_FAIL = -1;
    public static final int RET_CD_TIMEOUT = -2;
    public static final int RET_CD_TIMEOUT_FAIL = -3;
    public static final long DEFAULT_TIMEOUT = 50;

    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "FAIL";

    public static final String APPLICATION_JONS = "application/json";
    public static final String AUTHOIZATION = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String ACTION_INSERT = "Insert";
    public static final String ACTION_UPDATE = "Update";
    public static final String ACTION_DELETE = "Delete";



}
