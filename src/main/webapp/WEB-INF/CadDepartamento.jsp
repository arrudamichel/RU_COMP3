<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<%@include file="messagePage.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@page import="br.uffrj.comp3.rusys.model.Departamento"%>

<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%
	//se tivesse verificacao de login, aqui que ele seria programado
    
/* 	String mensagem = request.getAttribute("mensagem") == null ? "" : (String)request.getAttribute("mensagem");

	String acao = (String)request.getAttribute("acao");
	String id = (String)request.getAttribute("id"); */
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
                <li class="active"><a href="#block-text"><%=Constantes.DEPTO%></a></li>
            </ul>
          </div>
          <div class="content">
            <h2 class="title"><%=Constantes.CADDEPTO%> </h2>
            
            <table id="table-resultado" class="table">
              <tr>
                <th><%=Constantes.NOME%></th>
                <th><%=Constantes.SIGLA%></th>
                <th class="last">&nbsp;</th>
              </tr>                 
              <%               
             for(int i=0; i < departamentos.size(); i++) {
            	 String urlEditar="CadastrarDepartamento?acao="+ Constantes.ACAO_EDITAR +"&departamentoId="+departamentos.get(i).getIdentificador();
               	if(i%2 == 0){ %>
               	<tr class="odd">                  
                     <td><%=departamentos.get(i).getNome()%></td>                                              
                     <td><%=departamentos.get(i).getSigla()%></td>
                     <td class="last"><a href="<%=urlEditar%>"><%=Constantes.EDITAR%></a> </td>
                 </tr>
             <% } else { %>
                 <tr class="even">                      
					 <td><%=departamentos.get(i).getNome()%></td>                                              
                     <td><%=departamentos.get(i).getSigla()%></td>  
                     <td class="last"><a href="<%=urlEditar%>"><%=Constantes.EDITAR%></a> </td>
                 </tr>                    
         	<%}  
        		}%>                   	
              <%// } %>
            </table>
            
            <div class="inner">
            	<% if(mensagem.contains("Erro")){ %>
            		<div style="background-color:#FF9999; padding: 4px 0; margin:2px;width:auto;overflow:visible;text-align:center;border:1px solid #bfbfbf;" >
            			<%=mensagem%>
            		</div>
            	<%}else if(mensagem.contains("Sucesso")){%>
            		<div style="background-color:#CCFFCC; padding: 4px 0; margin:2px;width:auto;overflow:visible;text-align:center;border:1px solid #bfbfbf;" >
    					<%=mensagem%>
    				</div>
    			<%} %>
    			
    			
              <form id="FrmDepartamento" name="FrmDeparmento" action="CadastrarDepartamento" method="POST" class="form">              
                <div class="group">
                  <label class="label"><%=Constantes.NOME%></label>
                  <input type="text" id="nome" name="nome" <% /// if (pergunta != null && pergunta.getPergunta() != null ) { out.print(" value = '" + pergunta.getPergunta() + "'"); } %> class="text_field" />
                </div>
                <div class="group">
                    <label class="label" for="post_title"><%=Constantes.SIGLA%></label>
                   <input type="text" id="sigla" name="sigla" <% /// if (pergunta != null && pergunta.getPergunta() != null ) { out.print(" value = '" + pergunta.getPergunta() + "'"); } %> class="text_field" />
                </div>
                <div class="group navform wat-cf">                
                  <button class="button" type="submit" id='salvar' name="acao" value="<%=Constantes.SALVAR%>">Salvar</button>
                 <!--  <span class="text_button_padding">Ou</span>
                  <a class="text_button_padding link_button" href="listarDepartamentos.jsp"><%=Constantes.CANCELAR%></a> -->
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