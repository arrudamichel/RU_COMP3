<%@page import="java.util.ArrayList"%>
<%@page import="br.uffrj.comp3.rusys.view.Menu"%>
<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page import="br.uffrj.comp3.rusys.model.TurnoEnum"%>
<%@page import="br.uffrj.comp3.rusys.controller.ListarTurno"%>
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
    	$(document).ready(function() {
    	$("#table-resultado tr:odd").addClass("odd");
    	$("#table-resultado tr:even").addClass("even");
    	}
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
                                <li class="active"><a href="#"><%=Constantes.TURNO %></a></li>
                            </ul>
                        </div>
                        <div class="content">
                            <h2 class="title"><%=Constantes.LISTTURNO %></h2>
                            <div class="inner">
                                <form  id="FrmTurno" name="FrmTurno" action="#" method="POST" class="form">
                <table id="table-resultado" class="table">
                  <tr>                  
                    <th><%=Constantes.DESCRICAO%></th>
                  </tr>
                <% 
                ArrayList<Turno> turnos;
                ListarTurno lt = new ListarTurno();
                turnos = lt.listar();
                  
                for(int i=0; i < turnos.size(); i++) {
                  	if(i%2 == 0){ %>
                  	<tr class="odd">                        
                        <td><%=turnos.get(i)%></td>                                              
                    </tr>
                <% } else { %>
                    <tr class="even">                        
						<td><%=turnos.get(i)%></td>                        
                    </tr>                    
            	<%}  
           		}%>                
                </table>
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
