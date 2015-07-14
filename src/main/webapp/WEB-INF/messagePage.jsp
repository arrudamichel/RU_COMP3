<%	
	String mensagem = request.getAttribute("mensagem") == null ? "" : (String) request.getAttribute("mensagem");
%>

<div class="inner">
<%if (mensagem.contains("Erro")) {%>
	<div style="background-color: #FF9999; padding: 4px 0; margin: 2px; width: auto; overflow: visible; text-align: center; border: 1px solid #bfbfbf;">
		<%=mensagem%>
	</div>
<%} else if (mensagem.contains("Sucesso")) {%>
	<div style="background-color: #CCFFCC; padding: 4px 0; margin: 2px; width: auto; overflow: visible; text-align: center; border: 1px solid #bfbfbf;">
		<%=mensagem%>
	</div>
<%}%>
</div>