<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<%@include file="messagePage.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@page import="br.uffrj.comp3.rusys.model.Departamento"%>

<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
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
                <li class="active"><a href="#block-text"><%=Constantes.DEPTO%></a></li>
            </ul>
          </div>
          <div class="content">
          	<% 	String urlInserir="GerirDepartamento?acao="+ Constantes.ACAO_SALVAR; %>          	
            <h2 class="title"><%=Constantes.CADDEPTO%> </h2>
            <h3><a href="<%=urlInserir%>"><%=Constantes.ACAO_SALVAR%></a></h3>
            
            <table id="table-resultado" class="table">
              <tr>
                <th><%=Constantes.NOME%></th>
                <th><%=Constantes.SIGLA%></th>
                <th class="last">&nbsp;</th>
              </tr>                 
              <%               
             for(int i=0; i < departamentos.size(); i++) {
            	 String urlEditar="GerirDepartamento?acao="+ Constantes.ACAO_EDITAR +"&departamentoId="+departamentos.get(i).getId();
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
            </div>
          </div>
        </div>
      </div>
    </div>
</div>
</body>
</html>