# BancoFacil
 Projeto que simula um sistema bancário em Java
 
 Um mini sistema de controle de recursos pessoais. O sistema é conectado ao
 um banco de dados MySql que armazena todos os usuários e contas dos mesmos.
 
 O sistema é capaz de gerir novos usuários e tem possíveis relações entre
 contas, podendo, efetuar transferências.
 
 Gera um relatótio em formato .pdf com as movimentações da conta, podendo filtrar
 por um mês específico.
 ***
 ## Gerenciamento de Login.
 
 ![BancoFacil](https://user-images.githubusercontent.com/76791121/104332847-61296b80-54cf-11eb-9034-6827dbbbb92e.png)
 
 * Primeiro passo identificar se o usuário é comum ou administrador.
 * Logar com seu usuário e senha
 * Ter acesso ao menu principal
     * DEPÓSITO
     * SAQUE
     * TRANSFERÊNCIA
     * SALDO
     * EXTRATO
     * SAIR

No Menu também se encontra o nome do usuário e o número da conta.

![2](https://user-images.githubusercontent.com/76791121/104333115-a2ba1680-54cf-11eb-8dea-b5f136edf90c.png)

***
Saldo total da conta

Extrato de todas as movimentações da conta, podendo filtrar mês e ano.

![3](https://user-images.githubusercontent.com/76791121/104333136-a8176100-54cf-11eb-86ed-2465cf2e56c0.png)

No botão **Salvar** o sistema irá gerar um *.pdf* de acordo com o filtro aplicado.

[Veja um exemplo](https://github.com/FernandesJr/BancoFacil/blob/main/BancoFaciil/BkFacil0601132123.pdf)

**Email**
O sistema irá enviar o *.pdf* contendo o extrato para o email do usuário automaticamente.

![emailBancoFacil](https://github.com/FernandesJr/BancoFacil/blob/main/BancoFaciil/BancoFacil7fb580d2-3e0c-4029-ab21-04ef526420fa.pdf)


***
## Área administrativa
O administrador poderá;
* cadastrar usuário
* editar usuário
* excluir usuário
* lista com todos usuários e configurações dos mesmos
* receita total do banco
![4](https://user-images.githubusercontent.com/76791121/104333145-abaae800-54cf-11eb-8236-2ec2c89acf2b.png)


 
