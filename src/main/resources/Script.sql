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
  "id" INT NOT NULL AUTO_INCREMENT,
  "descricao" VARCHAR(45) NULL,
  "opcaoVegetariana" VARCHAR(45) NULL,  
  "turno" VARCHAR(45) NOT NULL,
  "tipo" VARCHAR(45) NULL,
  "situacao" TINYINT(1) NOT NULL,
  PRIMARY KEY ("id"));

-- Tabela "consumidor"
CREATE TABLE "consumidor" (
  "id" INT NOT NULL AUTO_INCREMENT,
  "matricula" INT NOT NULL,
  "nome" VARCHAR(45) NOT NULL,
  "ano_ingresso" INT NOT NULL,
  "sexo" VARCHAR(45) NOT NULL,
  "titulo" VARCHAR(45) NULL,
  "cpf" VARCHAR(45) NULL,
  "situacao" TINYINT(1) NOT NULL,
  PRIMARY KEY ("id"));

-- Tabela "ticket"
CREATE TABLE "ticket" (
  "id" INT NOT NULL AUTO_INCREMENT,
  "consumidor_fk" INT NOT NULL,
  "refeicao_fk" INT NOT NULL,
  "preco" DECIMAL(10,2) NOT NULL,
  "pago" TINYINT(1) NOT NULL,
  PRIMARY KEY ("id"),
  CONSTRAINT "ticket_consumidor_fk_has_consumidor"
    FOREIGN KEY ("consumidor_fk")
    REFERENCES "consumidor" ("id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "ticket_refeicao_fk_has_refeicao"
    FOREIGN KEY ("refeicao_fk")
    REFERENCES "refeicao" ("id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Tabela "departamento"
CREATE TABLE "departamento" (
  "id" INT NOT NULL AUTO_INCREMENT,
  "nome" VARCHAR(45) NOT NULL,
  "sigla" VARCHAR(45) NOT NULL,
  PRIMARY KEY ("id"));


-- Tabela "curso"
CREATE TABLE "curso" (
  "id" INT NOT NULL AUTO_INCREMENT,
  "nome" VARCHAR(45) NOT NULL,
  "sigla" VARCHAR(45) NOT NULL,
  "departamento_fk" INT NOT NULL,
  PRIMARY KEY ("id"),
  CONSTRAINT "curso_departamento_fk_has_departamento"
    FOREIGN KEY ("departamento_fk")
    REFERENCES "departamento" ("id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Tabela "funcionario"
CREATE TABLE "funcionario" (
  "id" INT NOT NULL,
  "departamento_fk" INT NOT NULL,
  PRIMARY KEY ("id"),
  CONSTRAINT "funcionario_departamento_fk_has_departamento"
    FOREIGN KEY ("departamento_fk")
    REFERENCES "departamento" ("id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "funcionario_has_consumidor"
    FOREIGN KEY ("id")
    REFERENCES "consumidor" ("id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Tabela "aluno"
CREATE TABLE "aluno" (
  "id" INT NOT NULL,
  "curso_fk" INT NOT NULL,
  PRIMARY KEY ("id"),
  CONSTRAINT "aluno_has_consumidor"
    FOREIGN KEY ("id")
    REFERENCES "consumidor" ("id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT "aluno_curso_fk_has_curso"
    FOREIGN KEY ("curso_fk")
    REFERENCES "curso" ("id")
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- Insert refeicao
INSERT INTO "refeicao" ("descricao","opcaoVegetariana", "situacao", "turno", "tipo") VALUES ('Arroz com bifé','Bolinho de soja',1, 'Manha', 'Dejejum');
INSERT INTO "refeicao" ("descricao","opcaoVegetariana", "situacao", "turno", "tipo") VALUES ('Macarrão com salsicha','Quiche de legumes',1, 'Tarde', 'Almoco');
INSERT INTO "refeicao" ("descricao","opcaoVegetariana", "situacao", "turno", "tipo") VALUES ('Arroz com carré','Soja',1, 'Noite', 'Jantar');

-- Insert departamento
INSERT INTO "departamento" ("nome","sigla") VALUES ('Departamento de Tecnologias e Liguagens','DTL');
INSERT INTO "departamento" ("nome","sigla") VALUES ('Departamento de Ciência da Computação','DCC');

-- Insert curso
INSERT INTO "curso" ("nome","sigla","departamento_fk") VALUES ('Matematica','Mat', 1);
INSERT INTO "curso" ("nome","sigla","departamento_fk") VALUES ('Ciência da Computação','CCOMP', 2);

-- Insert consumidor
INSERT INTO "consumidor" ("matricula","nome","ano_ingresso","sexo","titulo","cpf","situacao") VALUES (123,'Miguel','2010','M','ESPECIALIZACAO','12345678901',1);
INSERT INTO "consumidor" ("matricula","nome","ano_ingresso","sexo","titulo","cpf","situacao") VALUES (456,'Hugo','2010','M','ESPECIALIZACAO','12345678911',1);
INSERT INTO "consumidor" ("matricula","nome","ano_ingresso","sexo","titulo","cpf","situacao") VALUES (789,'Duarte','2013','M','DOUTORADO','12345678922',1);

-- Insert Aluno
INSERT INTO "aluno" ("id", "curso_fk") VALUES (1,2);
INSERT INTO "aluno"  ("id", "curso_fk")  VALUES (2,1);

-- Insert funcionario
INSERT INTO "funcionario" ("id", "departamento_fk") VALUES (3, 2);

-- Insert ticket
INSERT INTO "ticket" ("consumidor_fk", "refeicao_fk", "preco", "pago") VALUES (1,1,0.5,1);
INSERT INTO "ticket" ("consumidor_fk", "refeicao_fk", "preco", "pago") VALUES  (3,2,3.0,1);