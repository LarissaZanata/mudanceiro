INSERT INTO USUARIO(EMAIL, NOME, SENHA) VALUES ('larissa.zanata@gmail.com', 'Larissa', '12345678');
INSERT INTO MUDANCEIRO ( NOME, TELEFONE , TIPO_SERVICO ) VALUES ('Bruno', '99999999', 'MUDANCEIRO');

INSERT INTO MUDANCA(CEP_DESTINO, CEP_ORIGEN, DATA_CRIACAO, DATA_MUDANCA, IMOVEL_DESTINO, IMOVEL_ORIGEM, MOBILIA, MOBILIA_IMAGEM, STATUS_MUDANCA, VALOR_ORCAMENTO , CLIENTE_ID, MUDANCEIRO_ID) VALUES (87030070, 87030078, '2019-05-05 18:00:00', '2019-05-05 18:00:00', 'CASA', 'APARTAMENTO', 'guarda roupa e cama', null, 'ABERTA', 10.00, 1, 1);