<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="br.uffrj.comp3.rusys.model.Departamento"%>
<%@page import="br.uffrj.comp3.rusys.model.Curso"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java"
	pageEncoding="UTF-8"%>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%@include file="messagePage.jsp" %>
<%
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
						<li class="active"><a href="#block-text"><%=Constantes.CURSO%></a></li>
					</ul>
				</div>
				<div class="content">
					<h2 class="title"><%=Constantes.CADCURSO%>
					</h2>
					<div class="inner">
						<form id="FrmCurso" name="FrmCurso" action="CadastrarCurso" method="POST" class="form">
							<input type="hidden" id="id" name="id"
								<%/* Caso de edicão if (pergunta != null && pergunta.getId() != null ) { out.print(" value = '" + pergunta.getId() + "'"); } */%>>
							<div class="group">
								<label class="label"><%=Constantes.NOME%></label> <input
									type="text" id="nome" name="nome"
									<%/// if (pergunta != null && pergunta.getPergunta() != null ) { out.print(" value = '" + pergunta.getPergunta() + "'"); }%>
									class="text_field" />
							</div>
							<div class="group">
								<label class="label" for="post_title"><%=Constantes.SIGLA%></label>
								<input type="text" id="sigla" name="sigla"
									<%/// if (pergunta != null && pergunta.getPergunta() != null ) { out.print(" value = '" + pergunta.getPergunta() + "'"); }%>
									class="text_field" />
							</div>

							<div class="group">
								<label class="label" for="post_title"><%=Constantes.DEPTO%></label>
								<%
									/// Listar aqui, se quiser mando essa funcao combo out.print(new TipoDocumentoBll().comboHtml("turno", pergunta == null || pergunta.getTipoDocumento() == null || pergunta.getTipoDocumento().getId() == null  ? null : pergunta.getTipoDocumento().getId().toString(), "Selecione"));
								%>
								<select id="departamento" name="departamento">
									<%		
									if(departamentos != null){
									for(int i=0; i < departamentos.size(); i++){
									%>
									<option value="<%=departamentos.get(i).getNome()%>"><%=departamentos.get(i).getNome()%></option>
									<%
									}}
									%>
								</select>
							</div>
							
							<input type="submit" name="acao" value="<%=Constantes.SALVAR%>">
							<input type="submit" name="acao" value="<%=Constantes.CANCELAR%>">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>