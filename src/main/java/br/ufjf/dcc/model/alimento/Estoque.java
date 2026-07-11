package br.ufjf.dcc.model.alimento;

import java.io.IOException;
import java.util.List;

import br.ufjf.dcc.model.enums.TipoAlimento;
import br.ufjf.dcc.util.LeitorCSV;

public class Estoque {

    private List<Alimento> alimentos;

    public Estoque(String caminho) throws IOException {
        this.alimentos = LeitorCSV.carregar(caminho, new ConversorAlimentoCSV());
    }

    public List<Alimento> getAlimentos() {
        return alimentos;
    }

    public Alimento buscarAlimento(TipoAlimento tipo) {

        for (Alimento alimento : alimentos) {

            if (alimento.getTipo() == tipo) {
                return alimento;
            }
        }

        return null;
    }

    public boolean possui(TipoAlimento tipoAlimento) {

        Alimento alimento = buscarAlimento(tipoAlimento);

        return alimento != null && alimento.getQuantidade() > 0;
    }

    public void consumir(TipoAlimento tipoAlimento) {

        Alimento alimento = buscarAlimento(tipoAlimento);

        alimento.consumir(1);
    }

}
