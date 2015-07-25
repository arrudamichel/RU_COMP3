<%@page import="br.uffrj.comp3.rusys.model.TurnoEnum"%>
<%@page import="br.uffrj.comp3.rusys.model.vo.TicketVO"%>
<%@page import="br.uffrj.comp3.rusys.model.RefeicaoImpl"%>
<%@page import="br.uffrj.comp3.rusys.model.interfaces.*"%>
<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
	ArrayList<Refeicao> refeicoesTurno = (ArrayList<Refeicao>)request.getAttribute("refeicoesTurno");
	TicketVO ticket = (TicketVO)request.getAttribute("ticket");
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
        	  <input type = "hidden" id="consumidor_id" name = "consumidor_id" value="<%if (ticket != null && ticket.getConsumidorId() != 0 ) { out.print(ticket.getConsumidorId()); }%>">
                
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.REFEICAO%></label>
                    <select id="refeicao" name="refeicao">
					<%		
					if(refeicoesTurno != null){
						for(int i=0; i < refeicoesTurno.size(); i++){
					%>
							<option value="<%=refeicoesTurno.get(i).getId()%>" >
							<%=refeicoesTurno.get(i).getDescricao()%></option>
					<%
						}
					}
					%> 
					</select>
                </div>
                
                <div class="group">
					<label class="label" for="post_title"><%=Constantes.PAGO%></label>
					
					<select id ="pago" name="pago">												
				        <option value="1" ><%=Constantes.SIM%></option>
                    	<option value="0" ><%=Constantes.NAO%></option>                    	
                	</select> 
												
			  </div> 
			  
                <div class="group">
        	        <label class="label" for="post_title"><%=Constantes.VALOR%></label>
        	        <input type = "hidden" id="valor" name = "valor" value="<%if (ticket != null && ticket.getValor() != 0.0 ) { out.print(ticket.getValor()); }%>">
        	        
					<label class="label" for="post_title" id ="valorLabel" name="valorLabel" value="<%if (ticket != null && ticket.getValor() != 0.0 ) { out.print(ticket.getValor()); }%>"> <%if (ticket != null && ticket.getValor() != 0.0 ) { out.print(ticket.getValor()); }%></label>                  
                </div>
                <div class="group navform wat-cf">
                	<input type="submit" name="acao" value="<%=Constantes.ACAO_SALVAR%>">
					<input type="submit" name="acao" value="<%=Constantes.CANCELAR%>">                  
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