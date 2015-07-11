<%@page import="br.uffrj.comp3.model.Menu"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
	//se tivesse verificacao de login, aqui que ele seria programado
    
	String mensagem = request.getAttribute("mensagem") == null ? "" : (String)request.getAttribute("mensagem");

	String acao = (String)request.getAttribute("acao");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>Restaurante Universitário</title>
  <link rel="stylesheet" href="Stylesheets/base.css" type="text/css" media="screen" />
  <link rel="stylesheet" id="current-theme" href="Stylesheets/themes/default/style.css" type="text/css" media="screen" />
  <script type='text/javascript' charset='utf-8' src='Javascripts/jquery-2.1.3.min.js'></script>
  <script type="text/javascript" charset="utf-8">
    // <![CDATA[
    $(document).ready(function() {
    });
    // ]]>
  </script>
</head>
<div id="container">
    <%= new Menu().menu()%>
    <div id="wrapper" class="wat-cf">
      <div id="main">
        <div class="block" id="block-forms">
          <div class="secondary-navigation">
            <ul class="wat-cf">
              <li class="active"><a href="#block-text">Refeição</a></li>
            </ul>
          </div>
          <div class="content">
            <h2 class="title">Cadastro de Refeições</h2>
            <div class="inner">
            	<%=mensagem%>
              <form id="Frmpergunta" name="FrmRefeicao" action="Refeicao" method="POST" class="form">
              <input type = "hidden" id="acao" name = "acao" value="<%=acao%>">
        	  <input type = "hidden" id="id" name = "id" <% /* Caso de edicão if (pergunta != null && pergunta.getId() != null ) { out.print(" value = '" + pergunta.getId() + "'"); } */ %>>
                <div class="group">
                  <label class="label">Descrição</label>
                  <input type="text" id="descricao" name="descricao" <% /// if (pergunta != null && pergunta.getPergunta() != null ) { out.print(" value = '" + pergunta.getPergunta() + "'"); } %> class="text_field" />
                </div>
                <div class="group">
                    <label class="label" for="post_title">Turno</label>
                    <%/// Listar aqui, se quiser mando essa funcao combo out.print(new TipoDocumentoBll().comboHtml("turno", pergunta == null || pergunta.getTipoDocumento() == null || pergunta.getTipoDocumento().getId() == null  ? null : pergunta.getTipoDocumento().getId().toString(), "Selecione"));%>
                </div>
                <div class="group">
                    <label class="label" for="post_title">Opção Vegetariana</label>
                  	<input type="text"  id="qtdrespostas" name="opVeg" <% //if (pergunta != null && pergunta.getQtdRespostas() != null ) { out.print(" value = '" + pergunta.getQtdRespostas() + "'"); } %> class="text_field" />
                </div> 
                <div class="group navform wat-cf">
                  <button class="button" type="submit" id='salvar'>
                    <img src="Images/icons/tick.png" alt="Save" /> Salvar
                  </button>
                  <span class="text_button_padding">Ou</span>
                  <a class="text_button_padding link_button" href="index.jsp">Cancelar</a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
</div>
</body>
</html>