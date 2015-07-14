<%@page import="br.uffrj.comp3.rusys.model.Refeicao"%>
<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="br.uffrj.comp3.rusys.model.TurnoEnum"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java"
	pageEncoding="UTF-8"%>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
	//se tivesse verificacao de login, aqui que ele seria programado

	String mensagem = request.getAttribute("mensagem") == null ? "" : (String) request.getAttribute("mensagem");

	String acao = (String) request.getParameter("acao");
	Refeicao refeicao  = (Refeicao) request.getAttribute("refeicao");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constantes.RU%></title>
<%=Constantes.BASE_CSS%>
<%=Constantes.ESTILO_CSS%>
<%=Constantes.JQUERY_LINK%>
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
						<%
							if (mensagem.contains("Erro")) {
						%>
						<div
							style="background-color: #FF9999; padding: 4px 0; margin: 2px; width: auto; overflow: visible; text-align: center; border: 1px solid #bfbfbf;">
							<%=mensagem%>
						</div>
						<%
							} else if (mensagem.contains("Sucesso")) {
						%>
						<div
							style="background-color: #CCFFCC; padding: 4px 0; margin: 2px; width: auto; overflow: visible; text-align: center; border: 1px solid #bfbfbf;">
							<%=mensagem%>
						</div>
						<%
							}
						%>
						<form id="FrmRefeicao" name="FrmRefeicao" action="Refeicao" method="POST" class="form" onLoad="return ComportamentoTela('<%=acao%>');">
							<input type="hidden" id="acao" name="acao" value="<%=acao%>">
							<input type="hidden" id="id" name="id"
								<% if (refeicao != null && refeicao.getIdentificador() != 0 ) { out.print(" value = '" + refeicao.getIdentificador() + "'"); }%>>
							<div class="group">
								<label class="label"><%=Constantes.DESCRICAO%></label> <input
									type="text" id="descricao" name="descricao"
									<% if (refeicao != null && refeicao.getDescricao() != null ) { out.print(" value = '" + refeicao.getDescricao() + "'"); }%>
									class="text_field" />
							</div>
							<div class="group">
								<label class="label" for="post_title"><%=Constantes.TURNO%></label>
								<select id="turno" name="turno">
									<%-- <%
										for (Turno turno : Turno.values()) {
									%>
									<option value='<%=turno.toString()%>' <% 
										if((refeicao !=null) 
												&& 
												(refeicao.getTurno().toString().equals(turno.toString()))) 
											out.print("selected = 'true'") ;%>>
										<%=turno.toString()%>
									</option>
									<%
										}
									%> --%>
								</select>
							</div>
							<div class="group">
								<label class="label" for="post_title"><%=Constantes.OPVEG%></label>
								<input type="text" id="opVeg" name="opVeg"
									<%if (refeicao != null && refeicao.getOpcaoVeg() != null ) { out.print(" value = '" + refeicao.getOpcaoVeg() + "'"); }%>
									class="text_field" />
							</div>
							<div class="group navform wat-cf">
								<button class="button" type="submit" id='salvar'>
									<img src="Images/icons/tick.png" alt="Save" />
									<%=Constantes.SALVAR%>
								</button>
								<span class="text_button_padding">Ou</span> <a
									class="text_button_padding link_button" href="index.jsp"><%=Constantes.CANCELAR%></a>
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