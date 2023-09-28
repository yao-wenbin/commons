package io.github.yaowenbin.commons.http;

import java.io.Serializable;

/**
 * unify http response structure, 200 will like this:
 * {
 *  "data": XXX,
 *  "code": 200,
 *  "message": "success"
 * }
 * 400 will like this:
 * {
 *  "data": null,
 *  "code": 400,
 *  "message": "bad request"
 * }
 * ResultCode : you can extend your own Result Code with an enum implement ResultCode.
 * BaseResultCode : give some basic response enum.
 *
 * @param <T> Result Data Type.
 */

public class R<T> implements Serializable {

    private static final long serialVersionUID = 3452913614867544640L;

    private T data;

    private int code;

    private String message;

    public R() {
    }

    public R(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private R(int code, String message) {
        this(code, message, null);
    }


    public static <T> R<T> success(T data) {
        return new R<>(BaseResultCode.SUCCESS.getCode(), BaseResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> R<T> success() {
        return success((T) null);
    }



    public static <T> R<T> failed() {
        return failed(BaseResultCode.ERROR.getMessage());
    }

    public static <T> R<T> failed(String message) {
        return new R<>(BaseResultCode.ERROR.getCode(), message);
    }

    public static <T> R<T> failed(ResultCode result) {
        return new R<>(result.getCode(), result.getMessage());
    }

    public interface ResultCode {
        Integer getCode();

        String getMessage();
    }


    public enum BaseResultCode implements ResultCode {
        // 数据操作错误定义
        SUCCESS(200, "success"),
        BAD_REQUEST(400, "bad request"),
        ERROR(500, "server internal error, please contact administrator"),



        /**
         * 600MySQL实例模块
         */
        ;
        private final Integer code;
        private final String message;

        BaseResultCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
