package br.com.alura.rh.services.reajuste;

import br.com.alura.rh.ValidacaoException;
import br.com.alura.rh.model.Funcionario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ValidacaoPeriodicidadeEntreReajustes implements ValidacaoReajuste{
    public void validar(Funcionario funcionario, BigDecimal aumento){
        LocalDate ultimoReajuste = funcionario.getDataUltimoReajuste();
        LocalDate atual = LocalDate.now();
        long intervaloReajuste = ChronoUnit.MONTHS.between(ultimoReajuste, atual);

        if (intervaloReajuste < 6) {
            throw new ValidacaoException("Reajustes não podem ser feitos num intervalo menos do que 6 meses.");
        }
    }
}
