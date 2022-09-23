package br.com.fiap.util;

import java.util.Comparator;

import br.com.fiap.model.Veiculo;

public class ComparadorPorAno implements Comparator<Veiculo> {

    @Override
    public int compare(Veiculo o1, Veiculo o2) {
       return Integer.compare(o1.getAno(), o2.getAno());
    }
    
}
