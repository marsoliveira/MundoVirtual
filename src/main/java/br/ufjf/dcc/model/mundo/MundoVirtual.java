package br.ufjf.dcc.model.mundo;

import java.util.List;

import br.ufjf.dcc.model.criatura.Criatura;

public class MundoVirtual {

    private int turno;
    private int unidadesTempo;

    public MundoVirtual() {
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

                if (criatura.seVivo()) {
                    criatura.aplicarDesgasteNatural();
                }
            }

            this.unidadesTempo = 0;
        }
    }
}
