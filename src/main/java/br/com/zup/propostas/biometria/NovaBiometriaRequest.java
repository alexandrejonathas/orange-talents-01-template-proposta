package br.com.zup.propostas.biometria;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.codec.binary.Base64;

import javax.validation.constraints.NotBlank;

public class NovaBiometriaRequest {

    @NotBlank
    private String text;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NovaBiometriaRequest(@NotBlank String text) {
        this.text = text;
    }

    public boolean estaEmBase64() {
        return Base64.isBase64(text.getBytes());
    }

    public String getText() {
        return text;
    }
}
