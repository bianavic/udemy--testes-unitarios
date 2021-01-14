package br.ce.waquino.servicos;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;
import br.ce.wcaquino.servicos.LocacaoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static br.ce.waquino.builders.FilmeBuilder.umFilme;
import static br.ce.waquino.builders.UsuarioBuilder.umUsuario;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

// DATA DRIVEN TEST | Teste Orientado a Dados
// Ja temos toda a estrutura só falta inserir os dados que vao guia-los, é onde implementamos o PARAMETERIZER
@RunWith(Parameterized.class)
public class CalculoValorLocacaoTeste {

    // instanciar a declaracao do servico
    private LocacaoService service;

    // criar as 2 variaveis que utilizaremos
    @Parameterized.Parameter(value = 0) // primeiro pameter | inicial
    public List<Filme> filmes;
    @Parameterized.Parameter(value = 1) // segundo pameter | e porque a lista comeca em ZERO, declaro o valor UM
    public Double valorLocacao;
    // criar 1 variavel apenas para melhorar na visualizacao dos testes
    @Parameterized.Parameter(value = 2)
    public String cenario;

    @Before
    public void setup(){
        service = new LocacaoService();
    }

    // refatoracao
    private static Filme filme1 = umFilme().agora();
    private static Filme filme2 = umFilme().agora();
    private static Filme filme3 = umFilme().agora();
    private static Filme filme4 = umFilme().agora();
    private static Filme filme5 = umFilme().agora();
    private static Filme filme6 = umFilme().agora();
    private static Filme filme7 = umFilme().agora();

    // definir o conjunto de dados que sera testado
    // retornaremos uma COLECAO de ARRAY de objetos.
    // os dados utilizados no teste ficarao em um ARRAY
    @Parameterized.Parameters(name = "{2}") // quero mostrar apenas pelo parametro 3 | @Parameterized.Parameter(value = 2)
    public static Collection<Object[]> getParametros() {
        return Arrays.asList(new Object[][] {
                {Arrays.asList(filme1, filme2), 8.0, "Filmes: Sem Desconto"},
                {Arrays.asList(filme1, filme2, filme3), 11.0, "3 filmes: 25%"},
                {Arrays.asList(filme1, filme2, filme3, filme4), 13.0, "4 filmes: 50%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5), 14.0, "5 filmes: 75%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), 14.0, "6 filmes: 100%"},
                {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6, filme7), 18.0, "7 filmes: Sem Desconto"}
        });
    }

    @Test  // teste generico
    public void deveCalcularValorLocacaoConsiderandoDescontos() throws FilmeSemEstoqueException, LocadoraException {
        // cenario
        Usuario usuario = umUsuario().agora();

        // acao
        Locacao resultado = service.alugarFilme(usuario, filmes);

        // verificacao
        assertThat(resultado.getValor(), is(valorLocacao));

        System.out.println("!");
    }

    @Test
    public void print() {
        System.out.println(valorLocacao);
    }
}
