<%@page import="br.uffrj.comp3.rusys.model.Refeicao"%>
<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="br.uffrj.comp3.rusys.model.TurnoEnum"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html; charset=ISO-8859-1" language="java"
	pageEncoding="UTF-8"%>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%@include file="messagePage.jsp" %>
<%
	//se tivesse verificacao de login, aqui que ele seria programado

	/* String mensagem = request.getAttribute("mensagem") == null ? "" : (String) request.getAttribute("mensagem");

	String acao = (String) request.getParameter("acao");
	Refeicao refeicao  = (Refeicao) request.getAttribute("refeicao"); */
	
	Refeicao refeicao = (Refeicao)request.getAttribute("refeicao");
	ArrayList<TurnoEnum> turnos = (ArrayList<TurnoEnum>)request.getAttribute("turnos");
	

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%@include file="header.jspf" %>
<script type='text/javascript' charset='utf-8' src='resources/Javascripts/jsRefeicao.js'></script>
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
						<li class="active"><a href="#block-text"><%=Constantes.REFEICAO%></a></li>
					</ul>
				</div>
				<div class="content">
					<h2 class="title"><%=Constantes.CADREFEICAO%></h2>
					<div class="inner">
						<form id="FrmRefeicao" name="FrmRefeicao" action="AtualizarRefeicao" method="POST" class="form">
							<input type="hidden" id="id" name="id"
								 <% if (refeicao != null && refeicao.getId() != 0 ) { out.print(" value = '" + refeicao.getId() + "'"); }%>> 
							<div class="group">
								<label class="label"><%=Constantes.DESCRICAO%></label> <input
									type="text" id="descricao" name="descricao"
									 <% if (refeicao != null && refeicao.getDescricao() != null ) { out.print(" value = '" + refeicao.getDescricao() + "'"); }%>
									class="text_field" />
							</div>
							<div class="group">
								<label class="label" for="post_title"><%=Constantes.TURNO%></label>
								<select id="turno" name="turno">
									<%
										for(int i=0; i < turnos.size(); i++){
									%>
									<option value="<%=turnos.get(i)%>"  <%=(refeicao!=null && refeicao.getTurno().equals(turnos.get(1))) ? "SELECTED" : "" %>><%=turnos.get(i)%></option>
									<%
										}
									%>
								</select>
							</div>
							<div class="group">
								<label class="label" for="post_title"><%=Constantes.OPVEG%></label>
								<input type="text" id="opVeg" name="opVeg"
									 <%if (refeicao != null && refeicao.getOpcaoVeg() != null ) { out.print(" value = '" + refeicao.getOpcaoVeg() + "'"); }%> --%>
									class="text_field" />
							</div>
							<div class="group navform wat-cf">
								<button class="button" type="submit" id='salvar' name='acao' value="<%=Constantes.SALVAR%>">				
									<%=Constantes.SALVAR%>
								</button>
								<!--  <span class="text_button_padding">Ou</span> <a
									class="text_button_padding link_button" href="index.jsp"><%=Constantes.CANCELAR%></a> -->
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