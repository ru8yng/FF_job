package utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

/**
 * @Author 杨亚茹
 * @Date 2022/11/29 17:18
 * @PackageName:com.yyr.dto
 * @ClassName: CommonResponse
 * @Description: TODO
 * @Version 1.0
 */


@ToString
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> {
    private int code;
    private String message;
    private T data;

    private static int DEFAULT_SUCCESS_CODE = 200;

    private static int DEFAULT_ERROR_CODE = 511;

    public static int USER_NOT_LOGIN_ERROR_CODE = 510;

    public CommonResponse() {
        this.code = DEFAULT_SUCCESS_CODE;
    }

    public CommonResponse(T d) {
        this.data = d;
        this.code = DEFAULT_SUCCESS_CODE;
    }

    private CommonResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> CommonResponse<T> ok() {
        return new CommonResponse<T>();
    }

    public static <T> CommonResponse<T> ok(T d) {
        return new CommonResponse<>(d);
    }

    public static <T> CommonResponse<T> error(int code, String message) {
        return new CommonResponse<>(code, message);
    }

    public static <T> CommonResponse<T> error(String message) {
        return new CommonResponse<>(DEFAULT_ERROR_CODE, message);
    }

    public static <T> CommonResponse<T> error(int code) {
        CommonResponse<T> response = new CommonResponse<>();
        response.setCode(code);
        return response;
    }

    public CommonResponse<T> errorMessage(String message) {
        this.message = message;
        return this;
    }

    public CommonResponse<T> msg(String message) {
        this.message = message;
        return this;
    }

    public CommonResponse<T> content(T data) {
        this.data = data;
        return this;
    }
}