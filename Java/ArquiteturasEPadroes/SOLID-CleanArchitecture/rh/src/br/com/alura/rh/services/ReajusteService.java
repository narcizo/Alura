package br.com.alura.rh.services;

import br.com.alura.rh.model.Funcionario;

import java.math.BigDecimal;
import java.util.List;

public class ReajusteService {
    /*
    // Passando o método reajustarSalario() para a classe de ReajusteService aumenta a coesão da classe Funcionário pois
    // ela não precisa se preocupar com a regra de negócio envolvendo o ajuste de salário
    */
    private List<ValidacaoReajuste> validacaoReajusteList;

    public ReajusteService(List<ValidacaoReajuste> validacaoReajusteList) {
        this.validacaoReajusteList = validacaoReajusteList;
    }

    public void reajustarSalario(Funcionario funcionario, BigDecimal aumento){
        this.validacaoReajusteList.forEach(v -> v.validar(funcionario, aumento));

        BigDecimal salarioAtual = funcionario.getSalario();
        funcionario.atualizarSalario(salarioAtual.add(aumento));
    }
}