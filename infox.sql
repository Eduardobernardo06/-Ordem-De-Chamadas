create database dbinfox
default character set utf8
default collate utf8_general_ci;

use dbinfox;

create table tbusuarios(
iduser int primary key,
usuario varchar(50) not null,
fone varchar (15),
login varchar(15) not null unique,
senha varchar(15) not null
)default charset=utf8;

describe tbusuarios;

-- CRUD-> sistema básico de um bd(create{insert};read{select};update;delete)
insert into tbusuarios(iduser,usuario,fone,login,senha)values
(1,'josé de Assis','9999-9999','joseassis','123456');
insert into tbusuarios(iduser,usuario,fone,login,senha)values
(2,'Administrator','9999-9999','admin','admin');
insert into tbusuarios(iduser,usuario,fone,login,senha)values
(3,'Bill Gates','9999-9999','bill','123456');

select * from tbusuarios;

describe tbusuarios;

update tbusuarios set fone='8888-8888' where iduser=2;

update tbusuarios set usuario='Administrador' where iduser=2;

delete from tbusuarios where iduser=3;

alter table tbusuarios add column perfil varchar(20) not null;

update tbusuarios set perfil='user' where iduser = 1;
update tbusuarios set perfil='admin' where iduser = 2;


create table tbclientes(
	idcli int auto_increment,
    nomecli varchar(50) not null,
    endcli varchar(100),
    fonecli varchar(50) not null,
    emailcli varchar(50) not null,
    cidadecli varchar(50) not null,
    estado char(2) not null,
    cep varchar(9) not null,
    primary key (idcli)
)default charset= utf8;

describe tbclientes;

alter table tbclientes drop column cidadecli;
alter table tbclientes drop column estado;
alter table tbclientes drop column cep;

insert into tbclientes(idcli,nomecli,endcli,fonecli,emailcli,cidadecli,estado,cep) values
(1,'Eduardo Bernardo','Rua:Manoel alves de prado,238','98722-4946','bernardodasilva13@gmail.com');

select * from tbclientes;


create table tbos(
os int primary key auto_increment,
data_os timestamp default current_timestamp, /*timestamp default current_timestamp-> quando tiver um insert ele puxa data e hora do servidor pra gerar no BD data e hora*/
equipamento varchar(150) not null,
defeito varchar(150) not null,
servico varchar(150),
tecnico varchar(30),
valor decimal(10,2),/*dez digitos com 2 casas decimais depois da vírgula já fazendo o arredondamento*/
idcli int not null,

foreign key(idcli) references tbclientes(idcli)

)default charset=utf8;

describe tbos;

insert into tbos(equipamento,defeito,servico,tecnico,valor,idcli) values
('Notebook','não liga','Troca da fonte','zé',87.50,1);
insert into tbos(equipamento,defeito,servico,tecnico,valor,idcli) values
('gabinete','super aquecimento do processador','aplicar nova camada de pasta térmica','zé',60.50,4);

select * from tbos;

SELECT NOW(); -- varificar hora e data atual
SET @@global.time_zone = '+3:00';-- Em seguida, altere a variável global de fuso horário e desconecte do servidor:
-- Você precisa sair e entrar de novo na sua sessão MySQL para poder ver os efeitos.Assim que reiniciar a sessão do MySQL, verifique novamente a hora atual:


-- a linha abixao altera a tabela adicionando em uma determinada posição

alter table tbos add tipo varchar(15) not null after data_os;
alter table tbos add situacao varchar(20) not null after tipo;

-- o código abaixo traz informações de 2 tabelas
-- vc cria uma váriavel

select 
O.os,equipamento,defeito,servico,valor,
C.nomecli,fonecli
from tbos as O 
inner join tbclientes as C
on (O.idcli = C.idcli); 

select OSER.os,data_os,tipo,situacao,equipamento,valor,
CLI.nomecli,fonecli from tbos as OSER
inner join tbclientes as CLI 
on(CLI.idcli = OSER.idcli);