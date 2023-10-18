package br.com.alura.rh.services;

import br.com.alura.rh.model.Funcionario;

import java.math.BigDecimal;

public interface ValidacaoReajuste {
    /*
    // As regras de negócio atreladas ao reajuste de salários está muito propício a mudanças pois é muito possível
    // que mais validações sejam solicitadas, fazendo assim com que uma única classe implementando todas as validações
    // se tornasse uma classe pouco coesa e que não respeito o SRP.
    // Essa alteração também torno o código mais extensível, é muito fácil adicionar uma nova validação pois o código
    // já está preparado para essa eventual adição de regra de negócio
     */
    void validar(Funcionario funcionario, BigDecimal aumento);
}
