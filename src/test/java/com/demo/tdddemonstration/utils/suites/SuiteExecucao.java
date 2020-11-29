package com.demo.tdddemonstration.utils.suites;

import com.demo.tdddemonstration.model.service.junit.LocacaoServiceJUnitAssertTest;
import com.demo.tdddemonstration.model.service.junit.LocacaoServiceJUnitAssertThatTest;
import com.demo.tdddemonstration.model.service.junit.LocacaoServiceJUnitBeforeAfter;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        LocacaoServiceJUnitAssertTest.class,
        LocacaoServiceJUnitAssertThatTest.class,
        LocacaoServiceJUnitBeforeAfter.class
})
public class SuiteExecucao {}
