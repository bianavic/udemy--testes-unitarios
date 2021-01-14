package br.ce.waquino.servicos;

import br.ce.waquino.builders.FilmeBuilder;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static br.ce.waquino.builders.FilmeBuilder.umFilme;
import static br.ce.waquino.builders.UsuarioBuilder.umUsuario;
import static br.ce.waquino.matchers.MatchersProprios.caiNumaSegunda;
import static br.ce.waquino.matchers.MatchersProprios.ehHoje;
import static br.ce.waquino.matchers.MatchersProprios.ehHojeComDiferencaDias;
import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class LocacaoServiceTest {

    private LocacaoService service;

    @Rule
    public ErrorCollector error = new ErrorCollector();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup(){
        service = new LocacaoService();
    }

    // teste que nao funciona no SABADO
    @Test
    public void deveAlugarFilmeComSucesso() throws Exception {
        Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        //cenario
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = asList(umFilme().comValor(5.0).agora());

        //acao
        Locacao locacao = service.alugarFilme(usuario, filmes);

        //verificacao
        error.checkThat(locacao.getValor(), is(equalTo(5.0)));
        error.checkThat(locacao.getDataLocacao(), ehHoje());
        error.checkThat(locacao.getDataRetorno(), ehHojeComDiferencaDias(1));

    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void deveLancarExcecaoAoLancarFilmeSemEstoque() throws Exception{
        //cenario
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = asList(FilmeBuilder.umFilmeSemEstoque().agora());

        //acao
        service.alugarFilme(usuario, filmes);
    }

    @Test
    public void deveLancarExcecaoAoLancarAlugarFilmeSemUsuario() throws FilmeSemEstoqueException{
        //cenario
        List<Filme> filmes = asList(umFilme().agora());
        //acao
        try {
            service.alugarFilme(null, filmes);
            Assert.fail();
        } catch (LocadoraException e) {
            assertThat(e.getMessage(), is("Usuario vazio"));
        }
    }

    @Test
    public void deveLancarExcecaoAoAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException{
        //cenario
        Usuario usuario = umUsuario().agora();

        exception.expect(LocadoraException.class);
        exception.expectMessage("Filme vazio");

        //acao
        service.alugarFilme(usuario, null);
    }

    // checagem DINAMICA
    @Test // cuidado com o cenario por causa dos dias da semana, este teste so funciona no sabado
    // @Ignore é uma forma de ignorar o teste na execucao de todos
    public void deveDevolverNaSegundaAoAlugarNoSabado() throws FilmeSemEstoqueException, LocadoraException {
        Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));

        // cenario
        Usuario usuario = umUsuario().agora();
        List<Filme> filmes = asList(umFilme().agora());

        // acao
        Locacao retorno = service.alugarFilme(usuario, filmes);

        // verificacao
        assertThat(retorno.getDataRetorno(), caiNumaSegunda());
    }

}