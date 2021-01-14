package br.ce.waquino.suites;


import br.ce.waquino.servicos.CalculadoraTest;
import br.ce.waquino.servicos.CalculoValorLocacaoTeste;
import br.ce.wcaquino.servicos.LocacaoService;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CalculadoraTest.class,
        CalculoValorLocacaoTeste.class,
        LocacaoService.class
})

public class SuiteTest {

}
