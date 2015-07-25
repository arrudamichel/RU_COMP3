<%@page import="br.uffrj.comp3.rusys.model.SexoEnum"%>
<%@page import="br.uffrj.comp3.rusys.model.TituloEnum"%>
<%@page import="br.uffrj.comp3.rusys.model.Curso"%>
<%@page import="br.uffrj.comp3.rusys.model.Aluno"%>
<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8"%>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
	ArrayList <Curso> cursos = (ArrayList<Curso>) request.getAttribute("cursos");
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
						<li class="active"><a href="#block-text"><%=Constantes.ALUNOS%></a></li>
					</ul>
				</div>
				<%@include file="messagePage.jsp" %>
				<div class="content">
					<h2 class="title"><%=Constantes.CADALUNOS%>
					</h2>
					<div class="inner">
						<form id="FrmAluno" name="FrmAluno" action="GerirAluno" method="POST" class="form">
							<input type="hidden" id="id" name="id" />
							<div class="group">
								<label class="label"><%=Constantes.NOME%></label> 
								<input type="text" id="nome" name="nome" class="text_field" />
							</div>
							<div class="group">
								<label class="label"><%=Constantes.MATRICULA%></label> 
								<input type="text" id="matricula" name="matricula" class="text_field" />
							</div>
							<div class="group">
								<label class="label"><%=Constantes.ANOINGRESSO%></label> 
								<input type="text" id="anoIngresso" name="anoIngresso" class="text_field"  />
							</div>
							<div class="group">
			                   <label class="label" for="post_title"><%=Constantes.SEXO%></label>
			                   <input type="radio" id="sexo" name="sexo" value="M" > &nbsp;<%=Constantes.MASCULINO%> &nbsp;&nbsp;
			                   <input type="radio" id="sexo" name="sexo" value="F" > &nbsp;<%=Constantes.FEMININO%>
			               </div>
							<div class="group">
			                    <label class="label" for="post_title"><%=Constantes.TITULO%></label>
			                    <select id ="titulo" name="titulo">
			                        <option value="">Selecione</option>
			                        <option value="ESPECIALIZACAO" ><%=Constantes.ESPECIALIZACAO%></option>
			                        <option value="MESTRADO" ><%=Constantes.MESTRADO%></option>
			                        <option value="DOUTORADO"><%=Constantes.DOUTORADO%></option>
			                    </select>           
			                </div>
			                <div class="group">
								<label class="label"><%=Constantes.CPF%></label> 
								<input type="text" id="cpf" name="cpf" class="text_field" />
							</div>
							<div class="group">
								<label class="label" for="post_title"><%=Constantes.CURSO%></label>
								<select id="curso" name="curso">
									<%		
									if(cursos != null){
										for(int i=0; i < cursos.size(); i++){
									%>
											<option value="<%=cursos.get(i).getId()%>" >
											<%=cursos.get(i).getNome()%></option>
									<%
										}
									}
									%>
								</select>
							</div>
								<input type="submit" name="acao" value="<%=Constantes.ACAO_SALVAR%>">
							<input type="submit" name="acao" value="<%=Constantes.ACAO_CANCELAR%>">
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>