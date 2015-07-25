<%@page import="br.uffrj.comp3.rusys.model.TurnoEnum"%>
<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 <%@include file="header.jspf" %>
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
            <%@include file="messagePage.jsp" %>
              <form id="FrmTicket" name="FrmTicket" action="GerirTicket" method="POST" class="form">
        	  <input type = "hidden" id="id" name = "id" >
                <div class="group">
                  <label class="label"><%=Constantes.MATRICULA%></label>
                  <input type="text" id="matricula" name="matricula"  class="text_field" />
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.TURNO%></label>
                    <select id="turno" name="turno">
						<option value="<%=TurnoEnum.MANHA.toString()%>"><%=TurnoEnum.MANHA.toString()%></option>
						<option value="<%=TurnoEnum.TARDE.toString()%>"><%=TurnoEnum.TARDE.toString()%></option>
						<option value="<%=TurnoEnum.NOITE.toString()%>"><%=TurnoEnum.NOITE.toString()%></option>
					</select>  
                </div>
                <div class="group navform wat-cf">
                	<input type="submit" name="acao" value="<%=Constantes.ACAO_PROXIMO%>">
					<input type="submit" name="acao" value="<%=Constantes.ACAO_CANCELAR%>">               
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