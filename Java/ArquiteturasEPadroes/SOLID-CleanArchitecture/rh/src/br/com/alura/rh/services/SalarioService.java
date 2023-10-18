package br.com.alura.rh.services;

import br.com.alura.rh.ValidacaoException;
import br.com.alura.rh.model.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalarioService {
    /*
    // Passando o método reajustarSalario() para a classe de SalarioService aumenta a coesão da classe Funcionário pois
    // ela não precisa se preocupar com a regra de negócio envolvendo o ajuste de salário
    */
    public void reajustarSalario(Funcionario funcionario, BigDecimal aumento){
        BigDecimal salarioAtual = funcionario.getSalario();
        BigDecimal percentualReajuste = aumento.divide(salarioAtual, RoundingMode.HALF_UP);
        if (percentualReajuste.compareTo(new BigDecimal("0.4")) > 0) {
            throw new ValidacaoException("Reajuste nao pode ser superior a 40% do salario!");
        }
        funcionario.atualizarSalario(salarioAtual.add(aumento));
    }
}