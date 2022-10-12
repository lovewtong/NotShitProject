package com.Zlibrary.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @className: BaseException
 * @description: 统一异常处理
 * @author: sy
 * @date: 2022-10-11
 **/
public abstract class BaseException extends RuntimeException{
    private static final long  serialVersionUID = 1L;

    @Getter
    @Setter
    private int errorCode;

    public BaseException(String errorMessage){
        super(errorMessage);
    }

    public BaseException(int errorCode,String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public BaseException(String errorMessage,Throwable e){
        super(errorMessage,e);
    }

    public BaseException(int errorCode,String errorMessage,Throwable e){
        super(errorMessage,e);
        this.errorCode = errorCode;
    }
}
