<%@page import="br.uffrj.comp3.model.Menu"%>
<%@page import="br.uffrj.comp3.model.Constantes"%>
<%@page import="br.uffrj.comp3.model.Aluno"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.uffrj.comp3.controller.ListarAluno"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=Constantes.RU %></title>
        <%=Constantes.BASE_CSS%>
        <%=Constantes.ESTILO_CSS%>
        <%=Constantes.JQUERY_LINK%>
        <script type="text/javascript" charset="utf-8">
    // <![CDATA[
    $(document).ready(function() {
    	$("#table-resultado tr:odd").addClass("odd");
    	$("#table-resultado tr:even").addClass("even");
    	
		$("#novo-aluno").click(function(){
			window.location.href="CadAlunos.jsp";
		});
		
		$("#check_todos").click(function() {
			if ($(this).attr("checked")) {
				$('.checkbox').each(function() {
			    	$(this).attr("checked", true);
			    });
			} else {
				$('.checkbox').each(function() {
			    	$(this).attr("checked", false);
			    });
			}
		});
		
		$("#excluir-aluno").click(function(e) {
        	$.each(($("input[type=checkbox]:checked")), function(index, obj) {
        		if(obj.value != "on") {
        			$.post("Aluno",{id:obj.value, acao:"D"},function(responseText) { 
                        var ret = responseText.split("|");
                        $('#resultado').html(ret[0]);
                        if(ret[1]==="S") {
                        	var tr = "#tr"+obj.value;
                        	$(tr).fadeOut(400, function(){ 
                        		$(this).remove(); 
                        	}); 
                        }
                    });
        		}
			});
        });			
    });
    // ]]>
  </script>
    </head>
    <body>
        <div id="container">
            <%= new Menu().menu()%>
            <div id="wrapper" class="wat-cf">
                <div id="main">
                    <div class="block" id="block-tables">
                        <div class="secondary-navigation">
                            <ul class="wat-cf">
                                <li class="active"><a href="#"><%=Constantes.ALUNOS %></a></li>
                            </ul>
                        </div>
                        <div class="content">
                            <h2 class="title"><%=Constantes.LISTALUNOS %></h2>
                            <div class="inner">
                                <form  id="FrmListaAlunos" name="FrmListaAlunos" action="#"  method="POST" class="form">
                <table id="table-resultado" class="table">
                  <tr>
                    <th class="first"><input type="checkbox" id="check_todos" class="checkbox toggle" /></th>
                    <th><%=Constantes.NOME%></th>
                    <th><%=Constantes.MATRICULA%></th>
                    <th><%=Constantes.ANOINGRESSO%></th>
                    <th><%=Constantes.CURSO%></th>
                    <th><%=Constantes.CPF%></th>
                    <th class="last">&nbsp;</th>
                  </tr>
                  <% 	                
	                ListarAluno la = new ListarAluno();
                  	ArrayList<Aluno> alunos = la.listar();
	                  
	                for(int i=0; i < alunos.size(); i++) {
	                  	if(i%2 == 0){ %>
	                  	<tr class="odd">
	                  	    <td>
                    			<input type="checkbox" class="checkbox" name="id" value=<%=alunos.get(i).getMatricula()%> />
                    		</td>                   
	                        <td><%=alunos.get(i).getNome()%></td>
	                        <td><%=alunos.get(i).getMatricula()%></td>
	                        <td><%=alunos.get(i).getAnoDeIngresso()%></td>                                              
	                        <td><%=alunos.get(i).getCurso().getNome()%></td>
	                        <td><%=alunos.get(i).getCpf().getCpf()%></td>
	                        <td class="last"><a href="#"><%=Constantes.EDITAR%></a> </td>	                        
	                    </tr>
	                <% } else { %>
	                    <tr class="even">
	                  	    <td>
                    			<input type="checkbox" class="checkbox" name="id" value=<%=alunos.get(i).getMatricula()%> />
                    		</td>                   
	                        <td><%=alunos.get(i).getNome()%></td>
	                        <td><%=alunos.get(i).getMatricula()%></td>
	                        <td><%=alunos.get(i).getAnoDeIngresso()%></td>                                              
	                        <td><%=alunos.get(i).getCurso().getNome()%></td>
	                        <td><%=alunos.get(i).getCpf().getCpf()%></td>
	                        <td class="last"><a href="#"><%=Constantes.EDITAR%></a> </td>	                        
	                    </tr>                    
	            	<%}  
	           		}%>
                </table>
                <div class="actions-bar wat-cf">
                  <div class="actions">
                  	<button id="novo-aluno" name="novo-aluno" class="button" type="button">
                		<img src="Images/icons/tick.png" alt="<%=Constantes.NOVO%>" /> <%=Constantes.NOVO%>
              		</button>
                    <button  id="excluir-aluno" name="excluir-aluno" class="button" type="button">
                      <img src="Images/icons/cross.png" alt="delete" /> <%=Constantes.DELETE%>
                    </button>
                  </div>
                  <!--<div class="pagination">
                    <span class="disabled prev_page">« Previous</span><span class="current">1</span><a rel="next" href="#">2</a><a href="#">3</a><a href="#">4</a><a href="#">5</a><a href="#">6</a><a href="#">7</a><a href="#">8</a><a href="#">9</a><a href="#">10</a><a href="#">11</a><a rel="next" class="next_page" href="#">Next »</a>
                  </div>-->
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

</body>
Ï
