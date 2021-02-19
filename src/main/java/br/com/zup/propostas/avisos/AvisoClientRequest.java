package br.com.zup.propostas.avisos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoClientRequest {
    @NotBlank
    @JsonProperty
    private String destino;

    @Future
    @NotNull
    @JsonProperty
    private LocalDate validoAte;

    public AvisoClientRequest(NovoAvisoRequest request) {
        this.destino = request.getDestino();
        this.validoAte = request.getValidoAte();
    }
}
