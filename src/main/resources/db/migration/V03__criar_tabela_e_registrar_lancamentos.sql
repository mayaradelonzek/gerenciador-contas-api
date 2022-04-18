create table lancamento (
    codigo bigint(20) primary key auto_increment,
    descricao varchar(50) not null,
    data_vencimento date not null,
    data_pagamento date,
    valor decimal(10,2) not null,
    observacao varchar(100),
    tipo varchar(20) not null,
    codigo_categoria bigint(20) not null,
    codigo_pessoa bigint(20) not null,
    foreign key (codigo_categoria) references categoria(codigo),
    foreign key (codigo_pessoa) references pessoa(codigo)
) engine=InnoDB default charset=utf8;

insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Salário mensal', '2021-10-22', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);
insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Bahamas', '2021-10-22', null, 500.00, null, 'DESPESA', 2, 2);
insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Top Club', '2021-10-22', null, 5500.00, 'Distribuição de lucros', 'DESPESA', 3, 3);
insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('DMAE', '2021-10-22', null, 2500.00, null, 'RECEITA', 3, 4);
insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Extra', '2021-10-22', null, 4500.00, 'Distribuição de lucros', 'DESPESA', 3, 5);
insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Super', '2021-10-22', null, 900.00, 'Distribuição de lucros', 'RECEITA', 4, 8);
insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Café', '2021-10-22', null, 400.00, null, 'DESPESA', 4, 6);
insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Lanche', '2021-10-22', null, 6300.00, 'Distribuição de lucros', 'RECEITA', 1, 5);
insert into lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Pneus', '2021-10-22', null, 2500.00, null, 'RECEITA', 4, 2);

