package com.github.yt.excel.exception;


import com.github.yt.base.exception.BaseAccidentException;
import com.github.yt.base.exception.ExceptionBody;
import com.github.yt.excel.enums.ExcelExceptionEnum;

public class ParseExcelException extends BaseAccidentException {

    public ParseExcelException(ExcelExceptionEnum excelExceptionEnum, Object... param) {
        super(new ExceptionBody(excelExceptionEnum), param);
    }

    public ParseExcelException(ExcelExceptionEnum excelExceptionEnum, Throwable e, Object... param) {
        super(new ExceptionBody(excelExceptionEnum), e, param);
    }

    public ParseExcelException(Exception e) {
        super(new ExceptionBody(ExcelExceptionEnum.ERROR_402.name(), ExcelExceptionEnum.ERROR_402.getMessage()), e);
    }
}
