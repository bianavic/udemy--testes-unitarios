package br.ce.waquino;

import br.ce.wcaquino.entidades.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

    @Test
    public void test(){
        Assert.assertTrue(true);
        Assert.assertFalse(false);

        Assert.assertEquals(1, 1);
        // delta = margem de erro
        Assert.assertEquals(0.51, 0.51, 0.01);
        Assert.assertEquals(Math.PI, 3.14, 0.01);

        int i = 5;
        Integer i2 = 5;

        Assert.assertEquals(Integer.valueOf(i), i2);
        // ou passo o objeto para o tipo primitivo
        Assert.assertEquals(i, i2.intValue());

        // STRINGS
        Assert.assertEquals("bola", "bola");
        Assert.assertNotEquals("bola", "casa");
        Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
        Assert.assertTrue("bola".startsWith("bo"));

        Usuario u1 = new Usuario("Usuario 1");
        Usuario u2 = new Usuario("Usuario 1");
        Usuario u3 = null;

        Assert.assertEquals(u1, u2);

        // testar se sao da mesma instancia
        Assert.assertSame(u2, u2);
        Assert.assertNotSame(u1, u3); // apesar de iguais sao de instancias diferentes

        // testar se é nulo
        Assert.assertTrue(u3 == null);
        // ou
        Assert.assertNull(u3);
        Assert.assertNotNull(u2);
    }

}
