package br.com.zup.propostas.comum;

import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataCryptUtilTest {

    @Autowired
    private CryptDecryptUtil dataCryptUtil;

    @Test
    public void deveEndocadarEDecodarUmaString(){
        String valor = "meudocumento";
        String esperado = dataCryptUtil.encode(valor);
        String atual = dataCryptUtil.encode(valor);
        Assertions.assertEquals(esperado, atual);
    }

    @Test
    public void tese1() {
        String valor = "meudocumento";
        String esperado = Base64.encodeBase64String(valor.getBytes());
        String atual = Base64.encodeBase64String(valor.getBytes());
        Assertions.assertEquals(esperado, atual);
    }


}
