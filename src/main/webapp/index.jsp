<%@page import="br.uffrj.comp3.model.Menu"%>
<%@page import="br.uffrj.comp3.model.Constantes"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8" %>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=Constantes.RU%></title>
        <%=Constantes.BASE_CSS%>
        <%=Constantes.ESTILO_CSS%>
        <%=Constantes.JQUERY_LINK%>
        <script type="text/javascript" charset="utf-8">
    // <![CDATA[
    $(document).ready(function() {
		
    	 $("#nova-refeicao").click(function(){
    			window.location.href="CadRefeicao.jsp";
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
                                <li class="active"><a href="#"><%=Constantes.REFEICAO%></a></li>
                            </ul>
                        </div>
                        <div class="content">
                            <h2 class="title">Lista de Refeições disponíveis</h2>
                            <div class="inner">
                                <form action="#" class="form">
                                    <!-- Rodar a listagem aqui, a primeira terá listagem automatica, aqui temos um exemplo de como isso é feito-->
                                    <table class="table">
                                        <tr>
                                            <th class="first"><input type="checkbox" class="checkbox toggle" /></th>
                                            <th>Turno</th>
                                            <th>Descrição</th>
                                            <th>Opção Vegetariana</th>
                                            <th class="last">&nbsp;</th>
                                        </tr>
                                        <tr class="odd">
                                            <td><input type="checkbox" class="checkbox" name="id" value="1" /></td><td>1</td><td>hulk</td><td>Hulk</td><td class="last"><a href="#">show</a> | <a href="#">edit</a> </td>
                                        </tr>
                                        <tr class="even">
                                            <td><input type="checkbox" class="checkbox" name="id" value="1" /></td><td>2</td><td>ultimate</td><td>Ultimate</td><td class="last"><a href="#">show</a> | <a href="#">edit</a> </td>
                                        </tr>
                                        <tr class="odd">
                                            <td><input type="checkbox" class="checkbox" name="id" value="1" /></td><td>3</td><td>andre</td><td>Andre</td><td class="last"><a href="#">show</a> | <a href="#">edit</a></td>
                                        </tr>
                                        <tr class="even">
                                            <td><input type="checkbox" class="checkbox" name="id" value="1" /></td><td>4</td><td>machoman</td><td>Macho Man</td><td class="last"><a href="#">show</a> | <a href="#">edit</a></td>
                                        </tr>
                                    </table>
                                    <div class="actions-bar wat-cf">
                                        <div class="actions">
                                            <button class="button" type="submit">
                                                <img src="Images/icons/cross.png" alt="Delete" /> Delete
                                            </button>
                                        </div>
                                        <div class="pagination">
                                            <span class="disabled prev_page">« Previous</span><span class="current">1</span><a rel="next" href="#">2</a><a rel="next" class="next_page" href="#">Next »</a>
                                        </div>
                                    </div>
                                  
                                    <div class="actions-bar wat-cf">
                                        <div class="actions">
                                            <button id="nova-refeicao" name="nova-refeicao" class="button" type="button">
                                                <img src="Images/icons/tick.png" alt="Novo" /> Nova <%=Constantes.REFEICAO%>
                                            </button>
                                        </div>
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