<%@page import="javax.servlet.http.HttpServletRequest" %>
<%@page import="metier.form.SessionForm" %>
<%@page import="metier.DAO.Metier.UtilisateurDAO" %>

<%
SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");

String role = "";
if (sessionForm != null && sessionForm.getRole().equalsIgnoreCase("proprietaire")) {
	role = "proprietaire";
} else if (sessionForm != null && sessionForm.getRole().equalsIgnoreCase("employe")) {
	role = "employe";	
} else {
	role = "non connecte";	
}

%>

<%if (role.equalsIgnoreCase("proprietaire")) {
	%><jsp:include page="proprio_header.jsp" flush="true" />
	<p>Bienvenue M. ou Mme <% out.print(sessionForm.getUser().getNom()); %>, vous êtes connecté en tant que <% out.print(role); %>.</p>
	<%
} else if (role.equalsIgnoreCase("employe")) {
	%><jsp:include page="employe_header.jsp" flush="true" />
	<p>Bienvenue M. ou Mme <% out.print(sessionForm.getUser().getNom()); %>, vous êtes connecté en tant que <% out.print(role); %>.</p>
	<%
} else {
	%><jsp:include page="header_utilisateur.jsp" flush="true" /><%
} %>