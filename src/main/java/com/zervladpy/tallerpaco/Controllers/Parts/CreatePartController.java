package com.zervladpy.tallerpaco.Controllers.Parts;

import com.zervladpy.tallerpaco.Controllers.Common.FormController;
import com.zervladpy.tallerpaco.Core.Entities.Parts.Part;

public class CreatePartController implements FormController<Part> {



    @Override
    public void initialize() {

    }

    @Override
    public Part getData() {
        return null;
    }

    @Override
    public void setData(Part data) {

    }

    @Override
    public boolean isInputValid() {
        return false;
    }
}
