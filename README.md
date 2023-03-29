Este é um projeto criado a partir da disciplina "Programação Desktop" na "Universidade Tecnológica Federal do Paraná".
O seu principal objetivo era a introdução de banco de dados, neste caso, relacional. Dito isto, este foi meu primeiro projeto a usar Banco de Dados de forma útil.

O programa executa tudo o que estava planejado (foi um projeto com a única intenção de satisfazer o que foi requerido na disciplina, por isto é bem simples); 
O software é pensado em uma loja que vende histórias em quadrinhos, ou melhor, o administrador desta loja.

O banco de dados utilizado por fim foi, integralmente o SGBD MySQL porém, o professor utilizada o SGBD PostgreSQL (era de uso facultativo o SGBD PostgreSQL).
Observei esta situação como um divertido desafio, tinha mais familiaridade com o MySQL mas decidi tentar implementar o projeto também no PostgreSQL e, CONSEGUI! 😀

*Caso o uso do programa com PostgreSQL, teste com MySQL antes de qualquer correção por favor.

O projeto foi criado usando o MAVEN, dito isto, o driver do SGBD não se faz necessário, somente deve-se importar a dependência referente ao SGBD.
O software já contém as dependencia do MySQL e PostgreSQL, se desejar inserir mais algum importe a dependência no projeto.

Utiliza-se o arquivo '.properties' porém, resolvi criar uma janela para criar este arquivo! Ao rodar o programa, o banco estará desconectado e, 
ao clicar no botão (que é um JToggleButton) será realizado uma busca para encontrar o arquivo '.properties' (que não existe) então,
abrirá a janela para criar este arquivo. Para alterar as informações depois é necessário somente abrir o arquivo e alterar direto lá.

Não foi criado uma aba para "listar todos os 'escritores, desenhistas, editoras ou quadrinhos'" pode ser feito direto no banco após cadastramento no programa.
Os comandos estão em um dos arquivos '.txt'. O buscar mostra o registro mas um por um.

Os índices são atualizados após a exclusão, por exemplo:

Há 4 registros: 
1º
2º
3º
4º
Ao excluir o registro 2, o registro 3 passa a ser o 2º e o 4 passa a ser o 3º. Assim, o 4 não existe mais..

                                                                        William da Cruz Pires, aluno de Engenharia de Software - UTFPR.
