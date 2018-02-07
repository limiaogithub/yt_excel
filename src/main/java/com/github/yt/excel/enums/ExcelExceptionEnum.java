package com.github.yt.excel.enums;

public enum ExcelExceptionEnum {

    ERROR_401("导入数据中存在数据格式不规范！{0}"),
    ERROR_402("Excel读取异常！");

    private String message;

    ExcelExceptionEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
