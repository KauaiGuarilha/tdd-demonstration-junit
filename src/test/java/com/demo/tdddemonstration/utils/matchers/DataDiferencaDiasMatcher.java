package com.demo.tdddemonstration.utils.matchers;

import com.demo.tdddemonstration.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Date;

public class DataDiferencaDiasMatcher extends TypeSafeMatcher<Date> {

    private Integer quatidadeDias;

    public DataDiferencaDiasMatcher(Integer quatidadeDias){
        this.quatidadeDias = quatidadeDias;
    }

    @Override
    protected boolean matchesSafely(Date data) {
        return DataUtils.isMesmaData(data, DataUtils.obterDataComDiferencaDias(quatidadeDias));
    }

    @Override
    public void describeTo(Description description) {}
}
