<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList"%>
<%@page import="br.uffrj.comp3.rusys.model.Departamento"%>

<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
	//se tivesse verificacao de login, aqui que ele seria programado
    
/* 	String mensagem = request.getAttribute("mensagem") == null ? "" : (String)request.getAttribute("mensagem");

	String acao = (String)request.getAttribute("acao");
	String id = (String)request.getAttribute("id"); */
	ArrayList<Departamento> departamentos = (ArrayList<Departamento>)request.getAttribute("departamentos");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="header.jspf" %>
<script type='text/javascript' charset='utf-8'  src='resources/Javascripts/jsAlunos.js'></script>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
	});
</script>
<div id="container">
	<%@include file="menu.jspf" %>
    <div id="wrapper" class="wat-cf">
      <div id="main">
        <div class="block" id="block-forms">
          <div class="secondary-navigation">
            <ul class="wat-cf">
                <li class="active"><a href="#block-text"><%=Constantes.DEPTO%></a></li>
            </ul>
          </div>
          <div class="content">
            <h2 class="title"><%=Constantes.CADDEPTO%> </h2>  
            <%@include file="messagePage.jsp" %>
    					
              <form id="FrmDepartamento" name="FrmDeparmento" action="GerirDepartamento" method="POST" class="form">              
                <div class="group">
                  <label class="label"><%=Constantes.NOME%></label>
                  <input type="text" id="nome" name="nome" <% /// if (pergunta != null && pergunta.getPergunta() != null ) { out.print(" value = '" + pergunta.getPergunta() + "'"); } %> class="text_field" />
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.SIGLA%></label>
                   <input type="text" id="sigla" name="sigla" <% /// if (pergunta != null && pergunta.getPergunta() != null ) { out.print(" value = '" + pergunta.getPergunta() + "'"); } %> class="text_field" />
                </div>
                <div class="group navform wat-cf">                
                  <button class="button" type="submit" id='salvar' name="acao" value="<%=Constantes.ACAO_SALVAR%>"><%=Constantes.SALVAR%></button>
                 <!--  <span class="text_button_padding">Ou</span>
                  <a class="text_button_padding link_button" href="listarDepartamentos.jsp"><%=Constantes.CANCELAR%></a> -->
                </div>
              </form>
          </div>
        </div>
      </div>
    </div>
</div>
</body>
</html>