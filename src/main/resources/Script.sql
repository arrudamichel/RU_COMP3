DROP TABLE IF EXISTS "turno" ;
DROP TABLE IF EXISTS "jantar" ;
DROP TABLE IF EXISTS "dejejum" ;
DROP TABLE IF EXISTS "almoco" ;
DROP TABLE IF EXISTS "refeicao" ;
DROP TABLE IF EXISTS "consumidor" ;
DROP TABLE IF EXISTS "ticket" ;
DROP TABLE IF EXISTS "departamento" ;
DROP TABLE IF EXISTS "curso" ;
DROP TABLE IF EXISTS "funcionario" ;
DROP TABLE IF EXISTS "aluno" ;


-- Tabela Refeição
CREATE TABLE "refeicao" (
  "id_refeicao" INT NOT NULL AUTO_INCREMENT,
  "descricao" VARCHAR(45) NULL,
  "opcaoVegetariana" VARCHAR(45) NULL,  
  "turno" VARCHAR(45) NULL,
  "situacao" TINYINT(1) NOT NULL,
  PRIMARY KEY ("id_refeicao"));

-- Tabela Jantar
CREATE TABLE "jantar" (
  "refeicao_id_refeicao" INT NOT NULL,
  PRIMARY KEY ("refeicao_id_refeicao"),
  CONSTRAINT "fk_jantar_refeicao"
    FOREIGN KEY ("refeicao_id_refeicao")
    REFERENCES "refeicao" ("id_refeicao")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Tabela Almoço
CREATE TABLE "almoco" (
  "refeicao_id_refeicao" INT NOT NULL,
  PRIMARY KEY ("refeicao_id_refeicao"),
  CONSTRAINT "fk_almoco_refeicao"
    FOREIGN KEY ("refeicao_id_refeicao")
    REFERENCES "refeicao" ("id_refeicao")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Tabela Almoço
CREATE TABLE "dejejum" (
  "refeicao_id_refeicao" INT NOT NULL,
  PRIMARY KEY ("refeicao_id_refeicao"),
  CONSTRAINT "fk_dejejum_refeicao"
    FOREIGN KEY ("refeicao_id_refeicao")
    REFERENCES "refeicao" ("id_refeicao")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Tabela "consumidor"
CREATE TABLE IF NOT EXISTS "consumidor" (
  "consumidor_id" INT NOT NULL AUTO_INCREMENT,
  "matricula" INT NOT NULL,
  "nome" VARCHAR(45) NOT NULL,
  "ano_ingresso" VARCHAR(45) NOT NULL,
  "sexo" VARCHAR(45) NOT NULL,
  "titulo" VARCHAR(45) NULL,
  "cpf" VARCHAR(45) NULL,
  "situacao" TINYINT(1) NOT NULL,
  PRIMARY KEY ("consumidor_id"),
  UNIQUE INDEX "cpf_UNIQUE" ("cpf" ASC));

-- Tabela "ticket"
CREATE TABLE IF NOT EXISTS "ticket" (
  "ticket_id" INT NOT NULL AUTO_INCREMENT,
  "consumidor_id" INT NOT NULL,
  "refeicao_id_refeicao" INT NOT NULL,
  "preco" DECIMAL(10,2) NOT NULL,
  "pago" TINYINT(1) NOT NULL,
  PRIMARY KEY ("consumidor_id", "refeicao_id_refeicao"),
  CONSTRAINT "fk_Consumidor_has_Refeicao_Consumidor1"
    FOREIGN KEY ("consumidor_id")
    REFERENCES "consumidor" ("consumidor_id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_Consumidor_has_Refeicao_Refeicao1"
    FOREIGN KEY ("refeicao_id_refeicao")
    REFERENCES "refeicao" ("id_refeicao")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Tabela "departamento"
CREATE TABLE IF NOT EXISTS "departamento" (
  "id_departamento" INT NOT NULL AUTO_INCREMENT,
  "nome" VARCHAR(45) NOT NULL,
  "sigla" VARCHAR(45) NOT NULL,
  PRIMARY KEY ("id_departamento"));


-- Tabela "curso"
CREATE TABLE IF NOT EXISTS "curso" (
  "id_curso" INT NOT NULL AUTO_INCREMENT,
  "nome" VARCHAR(45) NOT NULL,
  "sigla" VARCHAR(45) NOT NULL,
  "departamento_id_departamento" INT NOT NULL,
  PRIMARY KEY ("id_curso"),
  CONSTRAINT "fk_curso_departamento1"
    FOREIGN KEY ("departamento_id_departamento")
    REFERENCES "departamento" ("id_departamento")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Tabela "funcionario"
CREATE TABLE IF NOT EXISTS "funcionario" (
  "consumidor_id" INT NOT NULL,
  "departamento_id_departamento" INT NOT NULL,
  
  PRIMARY KEY ("departamento_id_departamento", "consumidor_id"),
  CONSTRAINT "fk_departamento_has_consumidor_departamento1"
    FOREIGN KEY ("departamento_id_departamento")
    REFERENCES "departamento" ("id_departamento")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_departamento_has_consumidor_consumidor1"
    FOREIGN KEY ("consumidor_id")
    REFERENCES "consumidor" ("consumidor_id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Tabela "aluno"
CREATE TABLE IF NOT EXISTS "aluno" (
  "consumidor_id" INT NOT NULL,
  "curso_id_curso" INT NOT NULL,
  PRIMARY KEY ("consumidor_id", "curso_id_curso"),
  CONSTRAINT "fk_consumidor_has_curso_consumidor1"
    FOREIGN KEY ("consumidor_id")
    REFERENCES "consumidor" ("consumidor_id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "fk_consumidor_has_curso_curso1"
    FOREIGN KEY ("curso_id_curso")
    REFERENCES "curso" ("id_curso")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- Insert refeicao
INSERT INTO "refeicao" ("descricao","opcaoVegetariana", "situacao", "turno") VALUES ('Arroz com bifé','Bolinho de soja',1, 'MANHA');
INSERT INTO "refeicao" ("descricao","opcaoVegetariana", "situacao", "turno") VALUES ('Macarrão com salsicha','Quiche de legumes',1, 'TARDE');
INSERT INTO "refeicao" ("descricao","opcaoVegetariana", "situacao", "turno") VALUES ('Arroz com carré','Soja',1, 'NOITE');

-- almoco, dejejum, jantar
INSERT INTO "dejejum" VALUES (1);
INSERT INTO "almoco" VALUES (2);
INSERT INTO "jantar" VALUES (3);

-- Insert departamento
INSERT INTO "departamento" ("nome","sigla") VALUES ('Departamento de Tecnologias e Liguagens','DTL');
INSERT INTO "departamento" ("nome","sigla") VALUES ('Departamento de Ciência da Computação','DCC');

-- Insert curso
INSERT INTO "curso" ("nome","sigla","departamento_id_departamento") VALUES ('Matematica','Mat', 1);
INSERT INTO "curso" ("nome","sigla","departamento_id_departamento") VALUES ('Ciência da Computação','CCOMP', 2);

-- Insert consumidor
INSERT INTO "consumidor" ("matricula","nome","ano_ingresso","sexo","titulo","cpf","situacao") VALUES (123,'Miguel','2010','M','ESPECIALIZACAO','12345678901',1);
INSERT INTO "consumidor" ("matricula","nome","ano_ingresso","sexo","titulo","cpf","situacao") VALUES (456,'Hugo','2010','M','ESPECIALIZACAO','12345678911',1);
INSERT INTO "consumidor" ("matricula","nome","ano_ingresso","sexo","titulo","cpf","situacao") VALUES (789,'Duarte','2013','M','DOUTORADO','12345678922',1);

-- Insert Aluno
INSERT INTO "aluno" ("consumidor_id", "curso_id_curso") VALUES (1,2);
INSERT INTO "aluno"  ("consumidor_id", "curso_id_curso")  VALUES (2,1);

-- Insert funcionario
INSERT INTO "funcionario" ("consumidor_id", "departamento_id_departamento") VALUES (3, 2);

-- Insert ticket
INSERT INTO "ticket" ("consumidor_id", "refeicao_id_refeicao", "preco", "pago") VALUES (1,1,0.5,1);
INSERT INTO "ticket" ("consumidor_id", "refeicao_id_refeicao", "preco", "pago") VALUES  (3,2,3.0,1);