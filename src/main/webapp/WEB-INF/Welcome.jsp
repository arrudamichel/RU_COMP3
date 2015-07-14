<%@page import="br.uffrj.comp3.rusys.util.Constantes"%>
<%@page contentType="text/html; charset=ISO-8859-1" language="java" pageEncoding="UTF-8"%>
<!-- Nao deixa o JSP criar sessoes -->
<%@page session="false"%>
<%@include file="messagePage.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
	});
</script>
<%@include file="header.jspf" %>
<div id="container">
	<%@include file="menu.jspf" %>
	<div id="wrapper" class="wat-cf">
		<div id="main">
			<div class="block" id="block-forms">
				<div class="content">
					<h2 class="title"><%=Constantes.BEMVINDO%></h2>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>