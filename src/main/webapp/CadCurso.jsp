<%@page import="br.ufrrj.comp3.gateway.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
<%@page import="br.uffrj.comp3.model.Menu"%>
<%@page import="br.uffrj.comp3.model.Constantes"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="br.ufrrj.comp3.gateway.ConnectionFactory"%>
<%@page import="br.ufrrj.comp3.gateway.DepartamentoGateway"%>

<%@page contentType="text/html; charset=ISO-8859-1" language="java"
	pageEncoding="UTF-8"%>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
	//se tivesse verificacao de login, aqui que ele seria programado

	String mensagem = request.getAttribute("mensagem") == null ? "" : (String) request.getAttribute("mensagem");

	String acao = (String) request.getAttribute("acao");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=Constantes.RU%></title>
<%=Constantes.BASE_CSS%>
<%=Constantes.ESTILO_CSS%>
<%=Constantes.JQUERY_LINK%>
<script type='text/javascript' charset='utf-8'
	src='Javascripts/jsAlunos.js'></script>
<script type="text/javascript" charset="utf-8">
	// <![CDATA[
	$(document).ready(function() {
	});
	// ]]>
</script>
</head>
<div id="container">
	<%=new Menu().menu()%>
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
						<%=mensagem%>
						<form id="FrmCurso" name="FrmCurso" action="Curso" method="POST"
							class="form">
							<input type="hidden" id="acao" name="acao" value="<%=acao%>">
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
								<label class="label" for="post_title"><%=Constantes.TURNO%></label>
								<%
									/// Listar aqui, se quiser mando essa funcao combo out.print(new TipoDocumentoBll().comboHtml("turno", pergunta == null || pergunta.getTipoDocumento() == null || pergunta.getTipoDocumento().getId() == null  ? null : pergunta.getTipoDocumento().getId().toString(), "Selecione"));
								%>
								<select id="departamento" name="departamento">
									<%
									Connection conn = ConnectionFactory.getConnection(Constantes.DBPATH, Constantes.USER, Constantes.PASS);
									
									DepartamentoGateway deptGateway = new DepartamentoGateway(conn);
							//  iddepartamento  	nome  	sigla  
									ResultSet rs = deptGateway.selecionarDepartamentos();
									try {
										while (rs.next()) {
											int id = rs.getInt("iddepartamento");
											String nome = rs.getString("nome");
									    	String sigla = rs.getString("sigla");
									    	%>
									          <option value="<%=nome%>"><%=nome%></option>
											<%
									    	System.out.println("JSP == " + id + " " + nome + " " + sigla + " ");
										}
								    	conn.close();
									} catch (SQLException e) {
										e.printStackTrace();
									}
									
									%>
								</select>
							</div>

							<div class="group navform wat-cf">
								<button class="button" type="submit" id='salvar'>
									<img src="Images/icons/tick.png" alt="Save" />
									<%=Constantes.SALVAR%>
								</button>
								<span class="text_button_padding">Ou</span> <a
									class="text_button_padding link_button" href="listarCursos.jsp"><%=Constantes.CANCELAR%></a>
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