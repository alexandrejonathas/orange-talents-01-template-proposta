package br.com.zup.propostas.avisos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class NovoAvisoRequest {

    @NotBlank
    private String destino;

    @Future
    @NotNull
    private LocalDate validoAte;

    public NovoAvisoRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    @Override
    public String toString() {
        return "NovoAvisoRequest{" +
                "destino='" + destino + '\'' +
                ", validoAte=" + validoAte +
                '}';
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
