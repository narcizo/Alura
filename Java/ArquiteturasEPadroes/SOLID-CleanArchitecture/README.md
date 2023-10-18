# [SOLID com Java: princípios da programação orientada a objetos](https://cursos.alura.com.br/course/solid-orientacao-objetos-java)

<details>
  <summary>Princípios básicos POO </summary>

## Coesão
União harmônica entre uma coisa e outra. Se tratando de orientação a objetos, coesão é a relação existente entre as responsabilidades de uma determinada classe e de cada um de seus métodos. Uma classe altamente coesa tem responsabilidades e propósitos claros e bem definidos, enquanto uma classe com baixa coesão tem muitas responsabilidades diferentes e pouco relacionadas. Ou seja, uma classe que executa bem a sua única tarefa, de forma concisa.

Classes coesas não deveriam ter várias responsabilidades.
Classes não coesas tendem a crescer indefinidamente, o que as tornam difíceis de manter.

---

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

---

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

</details>

<details>
<summary> SOLID</summary>

## Single Responsability Principle
##### Aumenta a coesão

Não é porque você pode fazer, que você deveria fazer. Só porque você pode ter o sistema inteiro implementado numa única classe não quer dizer que você deve fazer isso.

Esse princípio ajuda a manter uma **alta coesão** no código. Uma classe deve ter um, e somente um, motivo para ser modificada.

❌ Código **RUIM** ❌

```java
public class GerarNotaFiscal{
	public void ValidarDadosDoCliente(){ ... }
	public void GerarImpostoFiscal(decimal ValorVenda){ ... }
	public void SalvarNotaFiscal(decimal ValorFinal) { ... }
	public void ImprimirCupomFiscal() { ... }
	public void EnviarCupomPorEmail() { ... }
}
```
Uma única classe está responsável por: 
* Validar dados do cliente;
* Gerar Imposto;
* Salvar Cupom Fiscal;
* Imprimir Cupom Fiscal;
* Enviar Cupom por e-mail;

Se ela tem 5 responsabilidades ela tem 5 motivos para ser modificada.

✅ Código **BOM** ✅
Seguindo os princípios do SRP.
```java
public class DadosDoCliente{
	Validar();
}

public class ImpostosCupomFiscal{
	GerarImpostos(decimal ValorVenda);
}

public class PersistenciaCupomFiscal{
	Salvar (decimal ValorFinal);
}

public class EmissaoCupomFiscal{
	Imprimir();
}

public class ComunicacaoCupomFiscal{
	EnviarPorEmail();
}
```

* Facilidade de manutenção e evolução do código;
* Código limpo e de fácil entendimento;
* Facilidade para desenvolvimento de testes;
* Redução do acoplamento;
* Complexidade reduzida;
* Coesão das classes;

Quando trabalhamos com SRP tem-se o sentimento de estarmos criando muitas classes para pouco uso, mas quando as funcionalidades do sistema crescem, podemos perceber que a expansão nada mais é do que criar novas classes nos lugares corretos e conecta-las de forma coesa.

---

## Open Closed Principle
##### Diminui o acoplamento

>Entidades de software (classes, módulos, funções, etc) devem estar abertas para extensão, porém fechadas para modificação.

Explicando a frase acima, diz respeito a extendibilidade do código. Isso significa que devemos poder criar novas funcionalidades e estender o sistema sem precisar modificar muitas classes já existentes. Sempre vai existir a necessidade de adicionar novos componentes, regras de negócio, módulos, validações, etc ao nosso código e ele deve estar totalmente aberto a isso, porém ao mesmo tempo deve estar fechado a modificação (zero ou muito pouco) de código já existente para essas novas adições. 
Claro, se for para correção de bugs ou alterações de regras de negócio já existente é inevitável modificar código já feito. Porém quando se fala de adicionar coisas novas, o ideal é já deixar a base de código preparada para receber novas features sem que seja necessário alterar a lógica antiga (Um grande exemplo é a utilização de interfaces com polimorfismo).
Uma classe que tende a crescer "para sempre" é uma forte candidata a sofrer alguma espécie de refatoração.

Exemplos:

```java
public enum TipoEmail {
	Texto,
	Html,
	Criptografado
}

public void class EnviarEmail(string mensagem, string assunto, TipoEmail tipo){
	if(tipo == TipoEmail.Texto)
	{
		mensagem = this.RemoverFormatacao(mensagem);
	}
	else if(tipo == TipoEmail.Html)
	{
		mensagem = this.InserirHtml(mensagem); 
	} 
	else if(tipo == TipoEmail.Criptografado)
	{
		mensagem = this.CriptografarMensagem(mensagem);
	}

	this.EnviarMensagem();
}
```
Quanto mais tipos de email forem adicionados, mais ifs deverão ser acrescentados.
O conceito de aberto/fechado (OCP) tem a premissa de criarmos novas classes para funcionalidades de tipos semelhantes, e caso tenhamos novas funcionalidades, novas classes sejam criadas.

```java
public abstract class Email
{
	public abstract void Enviar(string assunto, string mensagem);
}

public class TextoEmail : Email
{
	public override void Enviar(string assunto, string mensagem)
	{
	    Util.RemoverFormatacao(mensagem);U
	}
}

public class HtmlEmail : Email
{
	public override void Enviar(string assunto, string mensagem)
	{
  		Util.InserirHtml(mensagem);
	}
}

public class CriptografadoEmail : Email
{
	public override void Enviar(string assunto, string mensagem)
	{
		Util.CriptografarMensagem(mensagem);;
	}
}
```
Veja que no código bom, criamos várias classes, cada uma com uma responsabilidade definida, suas próprias regras de negócios e sem a necessidade de alterarmos a funcionalidade padrão devido a criação de uma nova regra.
Com isso ganhamos um grande potencial de **extensibilidade**, tudo isso com a ajuda da **abstração** e do **OCP**.

---

## Liskov Substitution Principle

---

## Dependency Inversion Principle

</details>
