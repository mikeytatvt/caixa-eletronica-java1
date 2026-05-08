# caixa-eletronica-java
Projeto de caixa eletrônico desenvolvido em Java para a disciplina de Programação Orientada a Objetos.

# Caixa Eletrônico em Java

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos.

## Funcionalidades
- Saque
- Relatório de cédulas
- Valor total disponível
- Reposição de cédulas
- Controle de cota mínima
- Histórico de transações

## Tecnologias utilizadas
- Java
- Swing
- Eclipse IDE

## Objetivo

Simular o funcionamento de um caixa eletrônico realizando operações bancárias e controle de cédulas disponíveis no caixa.

## Funcionamento do Sistema

O sistema trabalha com uma matriz 6x2 contendo:

| Cédula | Quantidade |
|---|---|
| 100 | 100 |
| 50 | 200 |
| 20 | 300 |
| 10 | 350 |
| 5 | 450 |
| 2 | 500 |

O algoritmo realiza os saques utilizando sempre as maiores notas disponíveis, visando entregar a menor quantidade possível de cédulas.

Caso o valor solicitado não possa ser atendido:
```text
Saque não realizado por falta de cédulas.
```
## Interface do Sistema
<img width="1279" height="719" alt="WhatsApp Image 2026-05-06 at 16 19 49" src="https://github.com/user-attachments/assets/51133cac-cb5a-493a-83c0-6494c61e3e5d" />
