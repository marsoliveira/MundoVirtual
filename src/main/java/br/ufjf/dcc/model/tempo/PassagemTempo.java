package br.ufjf.dcc.model.tempo;

import java.util.List;

import br.ufjf.dcc.model.criatura.Criatura;

public class PassagemTempo {

    private int turno;
    private int unidadesTempo;

    public PassagemTempo() {
        this.turno = 0;
        this.unidadesTempo = 0;
    }

    public int getTurno() {
        return this.turno;
    }

    public int getUnidadesTempo() {
        return this.unidadesTempo;
    }

    public void registrarAtividade(List<Criatura> criaturas) {

        this.unidadesTempo++;

        if (this.unidadesTempo == 5) {

            this.turno++;

            for (Criatura criatura : criaturas) {

                if (criatura.seViva()) {
                    criatura.aplicarDesgasteNatural();
                }
            }

            this.unidadesTempo = 0;
        }
    }
}
