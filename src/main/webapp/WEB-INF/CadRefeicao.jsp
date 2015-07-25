<%@page import="br.uffrj.comp3.rusys.model.RefeicaoImpl"%>
<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="br.uffrj.comp3.rusys.model.TurnoEnum"%>
<%@page import="br.uffrj.comp3.rusys.model.TipoRefeicaoEnum"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html; charset=ISO-8859-1" language="java"
	pageEncoding="UTF-8"%>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%	
	ArrayList<TurnoEnum> turnos = (ArrayList<TurnoEnum>)request.getAttribute("turnos");
	ArrayList<TipoRefeicaoEnum> tipos = (ArrayList<TipoRefeicaoEnum>)request.getAttribute("tipos");
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
						
						<form id="FrmRefeicao" name="FrmRefeicao" action="GerirRefeicao" method="POST" class="form">
							<input type="hidden" id="id" name="id">
							  <%@include file="messagePage.jsp" %>
							<div class="group">
								<label class="label"><%=Constantes.DESCRICAO%></label> <input
									type="text" id="descricao" name="descricao"
									<%-- <% if (refeicao != null && refeicao.getDescricao() != null ) { out.print(" value = '" + refeicao.getDescricao() + "'"); }%> --%>
									class="text_field" />
							</div>
							<div class="group">
								<label class="label" for="post_title"><%=Constantes.TIPO%></label>
								<select id="tipos" name="tipo">
									<%
									if(tipos != null){
										for(int i=0; i < tipos.size(); i++){
									%>
									<option value="<%=tipos.get(i)%>"><%=tipos.get(i)%></option>
									<%
										}
									}
									%>
								</select>
							</div>
							<div class="group">
								<label class="label" for="post_title"><%=Constantes.OPVEG%></label>
								<input type="text" id="opVeg" name="opVeg"
									<%-- <%if (refeicao != null && refeicao.getOpcaoVeg() != null ) { out.print(" value = '" + refeicao.getOpcaoVeg() + "'"); }%> --%>
									class="text_field" />
							</div>
							<div class="group navform wat-cf">
								<button class="button" type="submit" id='acao' name='acao' value="<%=Constantes.ACAO_SALVAR%>">				
									<%=Constantes.SALVAR%>
								</button>
								<span class="text_button_padding">Ou</span>
								 <button class="button" type="submit" id='acao' name='acao' value="<%=Constantes.ACAO_CANCELAR%>">
								 	<%=Constantes.CANCELAR%>
								</button>
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