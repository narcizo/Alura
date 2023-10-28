package br.com.alura.rh.services.promocao;

import br.com.alura.rh.ValidacaoException;
import br.com.alura.rh.model.Cargo;
import br.com.alura.rh.model.Funcionario;

public class PromocaoService {
    public void promover(Funcionario funcionario, boolean isMetaBatida){
        Cargo cargoAtual = funcionario.getCargo();

        if(cargoAtual == Cargo.GERENTE)
            throw new ValidacaoException("Gerentes não podem ser promovidos.");
        if(!isMetaBatida)
            throw new ValidacaoException("Funcionário nao pode ser promovido pois meta não foi batida.");

        Cargo novoCargo = cargoAtual.getProximoCargo();
        funcionario.promover(novoCargo); 
    }
}
