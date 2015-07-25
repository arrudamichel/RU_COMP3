# RUSYS 


Para rodar o projeto.
Método1:

Instale o Maven3.

Vá em ~/workspace/

Rode o comando: git clone git@github.com:arrudamichel/RU_COMP3.git

Abra o Eclipse e importe este projeto.

Vá ao terminal e execute: mvn clean tomcat7:run -X

Abra o browser e abra: http://localhost:8080/rusys

Metodo2:

Importe o projeto no eclipse.

Use a view Servers e crie um servidor com o tomcat7.

Adicione o projeto durante a configuração do Tomcat.

Na view Data Sourse Explorer: crie uma New Generic JDBC connection (necessário o jar do h2, incluido na versao usada).

Inicie o tomcat com a view Servers


------------------------------------------------------------
IMPORTANTE:

*Na classe Constantes.java encontram-se variáveis de ambiente com a url do banco e a senha e usuario do banco. Altere-as de acordo.

*Na pasta resources se encontra um script SQL para criar e inicar o banco. Ele precisa ser rodado antes de se usar o sistema. 

*Isso pode ser feito pela interface grafica do h2, executando o .jar diretamente.
