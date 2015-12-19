<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="metier.entity.BienImmo" %>
<%@page import="metier.entity.Appartement" %>

<div id="content">
	<jsp:include page="../header/header.jsp" flush="true" />

  <table width="900" border="0" align="center" cellpadding="0" cellspacing="0">
  <form method="post" action="ajouterBien">
  <h2>Mes biens !</h2>
  <br/>
  <tr>

    <% int i = 0; %>
        	
    	<c:forEach items="${apparts}" var="p">
    	   <% if (i%3 == 0) out.print(" <tr><td>"); %> 
			<table width="275" border="0" cellspacing="0" cellpadding="0" style="float:left;">
				<tr>
			        <td align="center"><div  class="house_sales">Numéro de l'annonce : ${p.id}</div></td>
			        </tr>
				 <tr>
			        <td align="center"><img src="images/${p.image}" alt="${p.image}" width="213" height="116"></td>
			     </tr>
			     	<tr>
			          <td align="center"><div>Adresse du bien : ${p.adresse}</div></td>
			        </tr>
			        <tr>
			          <td align="center"><div  class="">Montant : ${p.montant} euros</div></td>
			        </tr>
			        <tr>
			          <td align="center"><div>Disponible : ${p.isDisponible() ? "Oui" : "Non"}</div></td>
			        </tr>
    	       </table>
    	     <% if (i%3 == 2) out.print("</td></tr><tr><td>&nbsp;</td></tr>"); 
    	     i++;%>    
   			 
    	</c:forEach>
    	
    	<% i--; 
    	   if(i%3 != 2) out.print("</td></tr>"); %>

  </tr>
  </form>
</table>		

</div>
<jsp:include page="../footer/footer.jsp" flush="true" />