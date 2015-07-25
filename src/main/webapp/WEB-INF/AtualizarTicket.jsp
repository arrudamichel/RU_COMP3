<%@page import="br.uffrj.comp3.rusys.model.TurnoEnum"%>
<%@page import="br.uffrj.comp3.rusys.model.Ticket"%>
<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
    
	Ticket ticket = (Ticket)request.getAttribute("ticket");

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
            <h2 class="title"><%=Constantes.EDITAR%> </h2>
            <div class="inner">
            <%@include file="messagePage.jsp" %>
              <form id="FrmTicket" name="FrmTicket" action="GerirTicket" method="POST" class="form">
        	  <input type = "hidden" id="id" name = "id" value=<%if (ticket != null && ticket.getId() != 0 ) { out.print(ticket.getId()); }%>>
              <div class="group">
					<label class="label" for="post_title"><%=Constantes.MATRICULA%></label>
					<label class="label" for="post_title"> <%if (ticket != null && ticket.getConsumidor().getMatricula() != 0 ) { out.print(ticket.getConsumidor().getMatricula()); }%></label>								
			  </div>
              <div class="group">
					<label class="label" for="post_title"><%=Constantes.VALOR%></label>
					<label class="label" for="post_title"> <%if (ticket != null && ticket.getValor() != 0 ) { out.print(ticket.getValor()); }%></label>								
			  </div>			  			  
               
              <div class="group">
					<label class="label" for="post_title"><%=Constantes.TURNO%></label>
					<label class="label" for="post_title"> <%if (ticket != null && ticket.getRefeicao().getTurno().toString() != "" ) { out.print(ticket.getRefeicao().getTurno().toString()); }%></label>								
			  </div>			  			 
			  
			  <div class="group">
					<label class="label" for="post_title"><%=Constantes.DESCRICAO%></label>
					<label class="label" for="post_title"> <%if (ticket != null && ticket.getRefeicao().getDescricao() != "" ) { out.print(ticket.getRefeicao().getDescricao()); }%></label>								
			  </div>
			  
			  <div class="group">
					<label class="label" for="post_title"><%=Constantes.VALOR%></label>
					<label class="label" for="post_title"> <%if (ticket != null && ticket.getValor() != 0 ) { out.print(ticket.getValor()); }%></label>								
			  </div>
			  
			  <div class="group">
					<label class="label" for="post_title"><%=Constantes.PAGO%></label>
					
					<select id ="pago" name="pago">												
				        <option value="1" <%=(ticket != null && ticket.isPago() == true) ? "SELECTED" : "" %>><%=Constantes.SIM%></option>
                    	<option value="0" <%=(ticket != null && ticket.isPago() == false) ? "SELECTED" : "" %>><%=Constantes.NAO%></option>                    	
                	</select> 
												
			  </div> 
			  
                <div class="group navform wat-cf">
                  <input type="submit" name="acao" value="<%=Constantes.ACAO_EDITAR%>">
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