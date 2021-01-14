package br.ce.waquino.suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;


public class SuiteExecucao {

    @BeforeClass
    public static void before() {
        System.out.println("before");
    }

    @AfterClass
    public static void after() {
        System.out.println("after");
    }

    // o java obriga a ter uma declaracaode classe
    // Remova se puder!

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(SuiteTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println(result.wasSuccessful());
    }

}
