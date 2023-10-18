# [SOLID com Java: princípios da programação orientada a objetos](https://cursos.alura.com.br/course/solid-orientacao-objetos-java)

## Coesão
União harmônica entre uma coisa e outra. Se tratando de orientação a objetos, coesão é a relação existente entre as responsabilidades de uma determinada classe e de cada um de seus métodos. Uma classe altamente coesa tem responsabilidades e propósitos claros e bem definidos, enquanto uma classe com baixa coesão tem muitas responsabilidades diferentes e pouco relacionadas. Ou seja, uma classe que executa bem a sua única tarefa, de forma concisa.

Classes coesas não deveriam ter várias responsabilidades.
Classes não coesas tendem a crescer indefinidamente, o que as tornam difíceis de manter.

## Encapsulamento

Incluir ou proteger alguma coisa em uma cápsula. Proteger uma classe contra influências externas que podem prejudicar a consistência das informações. Tudo que é sensível a classe deve ser feito dentro da classe, e não fora dela. Exemplo: validações, cálculos de atualização de valores, mudança de estado, etc. 

❌ Código **RUIM** ❌
```java
public class Funcionario {

    private String nome;
    private String cpf;
    private Cargo cargo;

// double apenas para fins didáticos
    private double salario;

    public void reajustarSalario(double aumento) {
        double percentualReajuste = (aumento / salario) * 100;

        if (percentualReajuste > 40) {
            throw new IllegalArgumentException(
                "percentual de reajuste deve ser inferior a 40%");
        }

        this.salario += aumento;
    }
}
```

✅ Código **BOM** ✅
```java
public class Funcionario {

    private String nome;
    private String cpf;
    private Cargo cargo;

// double apenas para fins didáticos
    private double salario;

    public void setSalario(double salario) {
        this.salario = salario
    }
}
...

Fundionario novoFuncionario = carregaDoBancoDeDados();
novoFuncionario.setSalario(1000000000);
```
Classes não encapsuladas permitem violação das regras de negócio, além de aumentar o acoplamento.

## Acoplamento

Ação de acoplar, agrupamento aos pares. Quando dois ou mais componentes estão interligados entre si, causando uma dependência entre eles

❌ Código **RUIM** ❌

```java
Funcionario funcionario = carregarDoBancoDeDados();

double valorTotalReajustes = 0;
List<Reajuste> reajustes = funcionario.getReajustes();
for (Reajuste r : reajustes) {
    valorTotalReajustes += r.getValor();
}
```

✅ Código **BOM** ✅

```java
Funcionario funcionario = carregarDoBancoDeDados();
double reajustes = funcionario.getValorTotalRecebidoEmReajustes();
```

Note que no código com baixo acoplamento a classe que chama o método `getValorTotalRecebidoEmReajuste()` não sabe como esse valor ta sendo calculado, onde e como está guardado, ou qualquer outro detalhe de implementação que possa haver. Ela assume (corretamente, se o código for bem feito) que a classe do funcionário ja vai lidar com toda a implementação necessária para esse cáluclo e vai te dar o retorno pronto do valor correto já calculado. 

E se futuramente o `getReajustes()` retornar um `Map` ao invés de um `List`? O desenvolvedor terá que caçar todos os lugares em que foi feito o código ruim, porque com certeza haverá duplicação, para ai sim fazer a modificação, ao invés de tratar isso só uma vez no método `getValorTotalRecebidoEmReajuste()`. Nesse exemplo o alto acoplamento causa uma pequena mudança, a de `List` pra `Map` num problema generalizado que irá impactar __n__ outras classes.

Classes acopladas causam fragilidade no código da aplicação, tornando difícil sua manutenção.

