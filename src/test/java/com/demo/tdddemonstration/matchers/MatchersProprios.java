package com.demo.tdddemonstration.matchers;

import java.util.Calendar;

public class MatchersProprios {

    public static DiaSemanaMatcher caiEm(Integer diaSemana){
        return new DiaSemanaMatcher(diaSemana);
    }

    public static DiaSemanaMatcher caiNumaSegunda(){
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }

    public static DataDiferencaDiasMatcher hojeComDiferencaDias(Integer quantidadeDias){
        return new DataDiferencaDiasMatcher(quantidadeDias);
    }

    public static DataDiferencaDiasMatcher hoje(){
        return new DataDiferencaDiasMatcher(0);
    }
}
