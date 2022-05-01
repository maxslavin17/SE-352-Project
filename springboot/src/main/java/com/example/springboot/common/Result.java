package com.example.springboot.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String code;
    private String msg;
    private Object data;

    public static Result success() {
        return new Result(DefConst.CODE_200, "", null);
    }
    public static Result success(Object data) {
        return new Result(DefConst.CODE_200, "", data);
    }
    public static Result error() {
        return new Result(DefConst.Cdde_500, "system error", null);
    }
    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }
}
