package com.zervladpy.tallerpaco.Controllers.Common;

public interface FormController<T> extends  Controller {
    T getData();
    void setData(T data);
    boolean isInputValid();
}
