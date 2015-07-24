<%@page import="br.uffrj.comp3.rusys.model.Funcionario"%>
<%@page import="br.uffrj.comp3.rusys.model.Departamento"%>
<%@page import="br.uffrj.comp3.rusys.model.SexoEnum"%>
<%@page import="br.uffrj.comp3.rusys.model.TituloEnum"%>
<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8"%>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%@include file="messagePage.jsp" %>
<%
	ArrayList <Departamento> departamentos = (ArrayList<Departamento>) request.getAttribute("departamentos");
	Funcionario funcionario = (Funcionario) request.getAttribute("funcionario");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@include file="header.jspf" %>
<script type='text/javascript' charset='utf-8'  src='resources/Javascripts/jsAlunos.js'></script>
<div id="container">
	<%@include file="menu.jspf" %>
	<div id="wrapper" class="wat-cf">
		<div id="main">
			<div class="block" id="block-forms">
				<div class="secondary-navigation">
					<ul class="wat-cf">
						<li class="active"><a href="#block-text"><%=Constantes.FUNCIONARIO%></a></li>
					</ul>
				</div>
				<div class="content">
					<h2 class="title"><%=Constantes.CADFUNC%>
					</h2>
					<div class="inner">
						<form id="FrmAluno" name="FrmAluno" action="GerirFuncionario" method="POST" class="form">
							<input type="hidden" id="id" name="id" value="<%=(funcionario!=null) ? funcionario.getId() : "" %>"  />
							<div class="group">
								<label class="label"><%=Constantes.NOME%></label> 
								<input type="text" id="nome" name="nome" class="text_field" value="<%=(funcionario!=null) ? funcionario.getNome() : "" %>" />
							</div>
							<div class="group">
								<label class="label"><%=Constantes.MATRICULA%></label> 
								<input type="text" id="matricula" name="matricula" class="text_field" value="<%=(funcionario!=null) ? funcionario.getMatricula() : "" %>"  />
							</div>
							<div class="group">
								<label class="label"><%=Constantes.ANOINGRESSO%></label> 
								<input type="text" id="anoIngresso" name="anoIngresso" class="text_field" value="<%=(funcionario!=null) ? funcionario.getAnoDeIngresso() : "" %>"  />
							</div>
							<div class="group">
			                   <label class="label" for="post_title"><%=Constantes.SEXO%></label>
			                   <input type="radio" id="sexo" name="sexo" value="M" <%if (funcionario!=null && funcionario.getSexo().equals(SexoEnum.fromString("M"))) out.print("checked"); %>> &nbsp;<%=Constantes.MASCULINO%> &nbsp;&nbsp;
			                   <input type="radio" id="sexo" name="sexo" value="F" <%if (funcionario!=null && funcionario.getSexo().equals(SexoEnum.fromString("F"))) out.print("checked"); %> > &nbsp;<%=Constantes.FEMININO%>
			               </div>
							<div class="group">
			                    <label class="label" for="post_title"><%=Constantes.TITULO%></label>
			                    <select id ="titulo" name="titulo">
			                        <option value="">Selecione</option>
			                        <option value="ESPECIALIZACAO" <%=(funcionario!=null && funcionario.getTitulo().equals(TituloEnum.fromString("ESPECIALIZACAO"))) ? "SELECTED" : "" %>><%=Constantes.ESPECIALIZACAO%></option>
			                        <option value="MESTRADO" <%=(funcionario!=null && funcionario.getTitulo().equals(TituloEnum.fromString("MESTRADO"))) ? "SELECTED" : "" %>><%=Constantes.MESTRADO%></option>
			                        <option value="DOUTORADO" <%=(funcionario!=null && funcionario.getTitulo().equals(TituloEnum.fromString("DOUTORADO"))) ? "SELECTED" : "" %>><%=Constantes.DOUTORADO%></option>
			                    </select>           
			                </div>
			                <div class="group">
								<label class="label"><%=Constantes.CPF%></label> 
								<input type="text" id="cpf" name="cpf" class="text_field"  value="<%=(funcionario!=null) ? funcionario.getCpf() : "" %>"/>
							</div>
							<div class="group">
								<label class="label" for="post_title"><%=Constantes.DEPTO%></label>
								<select id="departamento" name="departamento">
									<%		
									if(departamentos != null){
										for(int i=0; i < departamentos.size(); i++){
									%>
											<option value="<%=departamentos.get(i).getIdentificador()%>" <%if (departamentos!=null && funcionario!=null  
											&& departamentos.get(i).getIdentificador()==funcionario.getDepartamento().getId()) out.print("selected"); %>>
											<%=departamentos.get(i).getNome()%></option>
									<%
										}
									}
									%>
								</select>
							</div>
								<input type="submit" name="acao" value="<%=Constantes.ACAO_EDITAR%>">
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