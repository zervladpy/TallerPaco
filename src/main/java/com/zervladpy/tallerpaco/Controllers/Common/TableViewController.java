package com.zervladpy.tallerpaco.Controllers.Common;

import java.util.List;

public interface TableViewController<T> extends Controller {
    void setColumns();
    void setItems(List<T> items);
    T getSelectedItem();

}
