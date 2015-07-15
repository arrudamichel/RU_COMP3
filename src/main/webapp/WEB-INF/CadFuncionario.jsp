<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="br.uffrj.comp3.rusys.model.Funcionario"%>
<%@page import="br.uffrj.comp3.rusys.model.Departamento"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
	//se tivesse verificacao de login, aqui que ele seria programado
    
	/* String mensagem = request.getAttribute("mensagem") == null ? "" : (String)request.getAttribute("mensagem");

	String acao = (String)request.getAttribute("acao"); */
	
	ArrayList<Funcionario> funcionarios = (ArrayList<Funcionario>)request.getAttribute("funcionarios");
	
	ArrayList<Departamento> departamentos = (ArrayList<Departamento>)request.getAttribute("departamentos");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title><%=Constantes.RU%></title>
  <%=Constantes.BASE_CSS%>
  <%=Constantes.ESTILO_CSS%>
  <%=Constantes.JQUERY_LINK%>
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
                <li class="active"><a href="#block-text"><%=Constantes.FUNCIONARIO%></a></li>
            </ul>
          </div>
          <div class="content">
            <h2 class="title"><%=Constantes.CADFUNC%> </h2>
            
            <table id="table-resultado" class="table">
                  <tr>
                    <th><%=Constantes.NOME%></th>
                    <th><%=Constantes.MATRICULA%></th>
                    <th><%=Constantes.ANOINGRESSO%></th>
                    <th><%=Constantes.DEPTO%></th>
                    <th><%=Constantes.CPF%></th>
                    <th class="last">&nbsp;</th>
                  </tr>
                  <% //for(Fundamento fundamento : new FundamentoBll().getLista()) { %>
                   <%
	                  
	                for(int i=0; i < funcionarios.size(); i++) {
	                	String urlEditar="GerirFuncionario?acao="+ Constantes.ACAO_EDITAR+ "&matricula="+funcionarios.get(i).getMatricula();
             		   	String urlDelete ="GerirFuncionario?acao="+ Constantes.ACAO_DELETAR+ "&matricula="+funcionarios.get(i).getMatricula();
	                  	if(i%2 == 0){ %>
	                  	<tr class="odd">	                  	                       
	                        <td><%=funcionarios.get(i).getNome()%></td>                                              
	                        <td><%=funcionarios.get(i).getMatricula()%></td>
	                        <td><%=funcionarios.get(i).getAnoDeIngresso()%></td>
	                        <td><%=funcionarios.get(i).getDepartamento().getSigla()%></td>
	                        <td><%=funcionarios.get(i).getCpf()%></td>
	                        <td class="last"><a href="<%=urlEditar %>"><%=Constantes.EDITAR%></a> </td>	
	                       	<td class="last"><a href="<%=urlDelete %>"><%=Constantes.DELETE%></a> </td>              
	                    </tr>
	                <% } else { %>
	                    <tr class="even">                      
							<td><%=funcionarios.get(i).getNome()%></td>                                              
	                        <td><%=funcionarios.get(i).getMatricula()%></td>
	                        <td><%=funcionarios.get(i).getAnoDeIngresso()%></td>
	                        <td><%=funcionarios.get(i).getDepartamento().getSigla()%></td>
	                        <td><%=funcionarios.get(i).getCpf()%></td>
	                        <td class="last"><a href="<%=urlEditar %>"><%=Constantes.EDITAR%></a> </td>	
	                       	<td class="last"><a href="<%=urlDelete %>"><%=Constantes.DELETE%></a> </td>                        
	                    </tr>                    
	   	    	  	<%}  
	          		}%> 
                </table>
            
            <div class="inner">
              <form id="Frmpergunta" name="FrmFuncionario" action="GerirFuncionario" method="POST" class="form">
        	  <input type = "hidden" id="id" name = "id" <% /* Caso de edicão if (pergunta != null && pergunta.getId() != null ) { out.print(" value = '" + pergunta.getId() + "'"); } */ %>>
                <div class="group">
                  <label class="label"><%=Constantes.NOME%></label>
                  <input type="text" id="nome" name="nome" <% /// if (pergunta != null && pergunta.getPergunta() != null ) { out.print(" value = '" + pergunta.getPergunta() + "'"); } %> class="text_field" />
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.MATRICULA%></label>
                   <input type="text" id="matricula" name="matricula" <% /// if (pergunta != null && pergunta.getPergunta() != null ) { out.print(" value = '" + pergunta.getPergunta() + "'"); } %> class="text_field" />
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.ANOINGRESSO%></label>
                  	<input type="text"  id="anoIngresso" name="anoIngresso" <% //if (pergunta != null && pergunta.getQtdRespostas() != null ) { out.print(" value = '" + pergunta.getQtdRespostas() + "'"); } %> class="text_field" />
                </div> 
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.SEXO%></label>
                    <input type="radio" id="sexo" name="sexo" value="M"> &nbsp;<%=Constantes.MASCULINO%> &nbsp;&nbsp;
                    <input type="radio" id="sexo" name="sexo" value="F"> &nbsp;<%=Constantes.FEMININO%>
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.TITULO%></label>
                    <select id ="titulo" name="titulo">
                        <option value="">Selecione</option>
                        <option value="ESPECIALIZACAO"><%=Constantes.ESPECIALIZACAO%></option>
                        <option value="MESTRADO"><%=Constantes.MESTRADO%></option>
                        <option value="DOUTORADO"><%=Constantes.DOUTORADO%></option>
                    </select>           
                </div>
                
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.CPF%></label>
                    <input type="text"  id="cpf" name="cpf" <% //if (pergunta != null && pergunta.getQtdRespostas() != null ) { out.print(" value = '" + pergunta.getQtdRespostas() + "'"); } %> class="text_field" onblur="valida<%=Constantes.CPF%>(this)"/>     
             	</div>
     	         <div class="group">
                    <label class="label" for="post_title"><%=Constantes.DEPTO%></label>
                    <select id ="departamento" name="departamento">
                    <% for(Departamento departamento : departamentos){ %>
                        <option value="<%=departamento.getIdentificador()%>"><%=departamento.getNome()%></option>
                    <% } %>                        
                    </select>
                </div>
                <div class="group navform wat-cf">
                  <button class="button" type="submit" id='salvar' name="acao" value="<%=Constantes.SALVAR%>"><%=Constantes.SALVAR%></button>
                  <span class="text_button_padding">Ou</span>
                  <a class="text_button_padding link_button" href="ListarFuncionarios.jsp"><%=Constantes.CANCELAR%></a>
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