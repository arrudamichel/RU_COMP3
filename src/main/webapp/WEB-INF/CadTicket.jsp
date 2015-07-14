<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
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
  <title><%=Constantes.RU%></title>
  <%=Constantes.BASE_CSS%>
  <%=Constantes.ESTILO_CSS%>
  <%=Constantes.JQUERY_LINK%>
  <script type='text/javascript' charset='utf-8' src='resources/Javascripts/jsAlunos.js'></script>
  <script type="text/javascript" charset="utf-8">
    // <![CDATA[
    $(document).ready(function() {
    });
    // ]]>
  </script>
</head>
<div id="container">
	<%@include file="menu.jspf" %>
    <div id="wrapper" class="wat-cf">
      <div id="main">
        <div class="block" id="block-forms">
          <div class="secondary-navigation">
            <ul class="wat-cf">
                <li class="active"><a href="#block-text"><%=Constantes.TICKET%></a></li>
            </ul>
          </div>
          <div class="content">
            <h2 class="title"><%=Constantes.CADTICKET%> </h2>
            <div class="inner">
            	<%=mensagem%>
              <form id="FrmTicket" name="FrmTicket" action="Ticket" method="POST" class="form">
              <input type = "hidden" id="acao" name = "acao" value="<%=acao%>">
        	  <input type = "hidden" id="id" name = "id" <% /* Caso de edicão if (pergunta != null && pergunta.getId() != null ) { out.print(" value = '" + pergunta.getId() + "'"); } */ %>>
                <div class="group">
                  <label class="label"><%=Constantes.SITPAGO%></label>
                  <input type="text" id="sitpag" name="sitpag" <% /// if (pergunta != null && pergunta.getPergunta() != null ) { out.print(" value = '" + pergunta.getPergunta() + "'"); } %> class="text_field" />
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.REFEICAO%></label>
                    <%/// Listar aqui, se quiser mando essa funcao combo out.print(new TipoDocumentoBll().comboHtml("turno", pergunta == null || pergunta.getTipoDocumento() == null || pergunta.getTipoDocumento().getId() == null  ? null : pergunta.getTipoDocumento().getId().toString(), "Selecione"));%>
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.VALOR%></label>
                    <input type="text"  id="valor" name="valor" <% //if (pergunta != null && pergunta.getQtdRespostas() != null ) { out.print(" value = '" + pergunta.getQtdRespostas() + "'"); } %> class="text_field"  readonly/>     
                </div>
                <div class="group navform wat-cf">
                  <button class="button" type="submit" id='salvar'>
                    <img src="Images/icons/tick.png" alt="Save" /> <%=Constantes.SALVAR%>
                  </button>
                  <span class="text_button_padding">Ou</span>
                  <a class="text_button_padding link_button" href="listarDepartamentos.jsp"><%=Constantes.CANCELAR%></a>
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