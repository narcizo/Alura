# [Aprofunde em Java com arquitetura e padrões de projeto](https://cursos.alura.com.br/formacao-arquitetura-design-projetos-java-v142135)

## [Objects Calisthenics](https://developerhandbook.stakater.com/content/architecture/object-calisthenics.html): 'Tell, don't ask' e 'Fail first'

Objects Calisthenics são um conjunto de regras para reforçar boas práticas em seu código. Não são difíceis de lembrar e com o tempo transformarão seu código em algo muito melhor, mais legível e de fácil manutenção. Alguns princípios são:

* **Não use a keyword 'else' (Fail First)**: Isso reduz o número de blocos de condicionais, deixando o código mais legível. Em casos de validação o Fail First advoga um retorno antecipado caso a validação falhe, ao invés de 2 blocos if/else para cada resultado da validação, isso também ajuda a reduzir a duplicação de código. Exemplo:

❌ Código **ruim** ❌
```java
private Integer getFinalIndexOfList(List<String> names)  
{  
    if (CollectionUtils.isEmpty(names))  
    {  
        return null;  
    }  
    else   
	{  
        return names.size() - 1;  
    }  
}
```

✅ Código **bom** ✅
```java
private Integer getFinalIndexOfList(List<String> names)
{
	if (CollectionUtils.isEmpty(names))
	{
		return null;
	}
	
	return names.size() - 1;
}
```

O uso do polimorfismo também pode ajudar nesses casos, caso sua validação leve em conta diferentes tipos de classe derivadas de uma classe abstrata/interface.

* **Tell, don't ask**: É um princípio do desenvolvimento de código que implica em solicitar a uma classe realizar uma ação ao invés de validar se a ação pode ser feita e então executá-la. Exemplo:

❌ Código **ruim** ❌

```java
AirplaneState = {
  STOPPED: 1,
  MOVING: 2
}
class MissingTakeOffPermissionException extends Exception { }
class Airplane {
  constructor() {
    hasTakeoffPermission = false;
    state =  AirplaneState.STOPPED;
  }
}
boeing747 = new Airplane();
if (boeing747.hasTakeoffPermission) {
  boeing747.state = AirplaneState.MOVING;
} else {
  throw new MissingTakeOffPermissionException();
}
```

✅ Código **bom** ✅

```java
AirplaneState = {
  STOPPED: 1,
  MOVING: 2
}
class MissingTakeOffPermissionException extends Exception { }
class Airplane {
  constructor() {
    hasTakeoffPermission = false;
    state =  AirplaneState.STOPPED;
  }
  takeoff() {
    if (!this.hasTakeoffPermission) {
      throw new MissingTakeOffPermissionException();
    }
    this.state = AirplaneState.MOVING;
  }
}
boeing747 = new Airplane();
boeing747.takeoff();
```
* **Não use getters e setters**: Getters e Setter podem prejudicar a lógica do seu código, além de servir como o maior proliferador de código boilerplate. Como alternativa, defina os atributos como públicos e defina métodos que representem comportamentos da classe. Exemplo:

❌ Código **ruim** ❌

```java
public class BankAccount {
    private final int amount;
	
	public BankAccount(int amount) {
		this.amount = amount;
	}
	
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
```

✅ Código **bom** ✅
```java
public class BankAccount {
    private final int amount;
	
	public BankAccount(int amount) {
		this.amount = amount;
	}
	
    public void depositAmount(int amount) {
        this.amount+=amount;
    }
	
	public void withdrawAmount(int amount) {
		this.amount -= amount;
	}
}
```

Getters e setters ainda são úteis caso seja necessário uma validação de validação de inputs por exemplo.

* **Não abrevie**: Não abrevie nome de variáveis, você pode checar [esse guia](https://williamdurand.fr/2012/01/24/designing-software-by-naming-things/) caso tenha dúvidas em como nomear suas variáveis/métodos/classes.

## [Curso SOLID com Java: princípios da programação orientada a objetos](./SOLID-CleanArchitecture/)

## [O que é Clean Code?](https://www.alura.com.br/artigos/o-que-e-clean-code?_gl=1*t4uo97*_ga*NDg0MzUxNTMuMTY5NDgxOTI2OQ..*_ga_1EPWSW3PCS*MTY5NzUxMTQ0Mi41LjEuMTY5NzUxMTQ4Mi4wLjAuMA..*_fplc*Q2hkS2E2azUyaGxGZXJhdTJiYkR0YldTVE5CQmFabThPWFZWOTFLY2tnTWJPYm5DdWk1bEJTZFpySHNVZWQlMkJXbnhHV0hCOWJtYU1LNTh0U0cwR0tLZTJpSERsazN2TWtxQ2oxallCaEtldTklMkZuM1laZU1oTiUyQnUxb0JZdlJ3JTNEJTNE)