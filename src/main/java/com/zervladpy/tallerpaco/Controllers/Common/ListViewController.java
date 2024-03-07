package com.zervladpy.tallerpaco.Controllers.Common;

import java.util.List;

public interface ListViewController<T> extends Controller {
    void setItems(List<T> items);
    T getSelectedItem();

}
