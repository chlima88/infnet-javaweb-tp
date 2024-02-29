# TPAPP

## O ambiente de desenvolvimento consiste em:

Java versão 17
Spring Boot 3.2.3
Maven 3.9.0

## Projeto

O projeto se trata de um software destinado à venda de produtos.

A primeira classe implementada foi a classe Customer que possui os atributos:

**id (int)** – Identificador único do cliente no sistema
**name (String)** – Nome do cliente
**document (String)** – Documento de identificação do cliente
**email (String)** – Email para contato com o cliente (utilizado no login)
**birthday (LocaDate)** – Data de nascimento do cliente (utilizado para oferecer descontos)
**createdAt (LocalDate)** – Data de cadastro (Utilizado para classificação no plano de fidelidade)
**active (boolean)** – Estado do cadastro do cliente (ativo – true / inativo – false)

## Implementação de Métodos

A classe Customer possui três métodos com regras de negócio, sendo eles:

1) getRank – um método criado para calcular a classificação de fidelidade do cliente de acordo com a data desde quando fez o cadastro.

	a) Clientes com menos de 6 meses – Rank Basic
	b) Clientes entre 6 e 12 meses – Rank Premium
	c) Clientes com mais de 12 meses – Rank Elite

2) activate e deactivate - métodos criados para ativar e desativar a conta do cliente. Esses métodos alteram o estado do atributo active para true ou false, representando uma conta ativa ou desativada respectivamente.

