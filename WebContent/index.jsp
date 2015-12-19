<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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


<div id="content"><table width="928" border="0" cellspacing="0" cellpadding="0">
<jsp:include page="jsp/header/header.jsp" flush="true" />

  <tr>
    <td>
    <div id="content_top">
    <div id="main_content">
	    <h1>Les biens immobiliers en ventes !</h1>
	    <h3>Nos meilleurs propositions</h3><br>
	    <p>BIENVENUE SUR <span class="orange">K-A-K IMMOBILIER</span> 
	    KAK Immobilier : les annonces immobilières de particulier à particulier.
	    C'est le premier site immobilier français à proposer des annonces de location et de 
	    vente immobilières entre particuliers. Vous trouverez sur KAK de nombreuses annonces :
	     des locations d'appartements et dans un futur des locations de maisons, des locations 
	     de lofts, des maisons à vendre, des annonces de ventes d'appartement. Ces annonces 
	     immobilières sont mises à jour régulièrement afin de vous apporter un service optimum. </p>
	    <div id="house_sales">
	      <table width="275" border="0" cellspacing="0" cellpadding="0" style="float:left;">
	        <tr>
	          <td align="center"><img src="images/appartement.jpg" alt="house_02" width="600" height="180"></td>
	        </tr>
	       
	      </table>
	    </div>
    </div>
	
	
 <div id="find">
    <h2>Trouver votre bien</h2>
    <form  method="post" action="rechercher">
    <table width="265" border="0" cellpadding="2" cellspacing="0" class="black">
    <tr>
    <td>N° Annonce </td>
    <td>:</td>
    <td>
      <input name="id_annonce" type="text" class="textbox" id="textfield">    </td>
  </tr>
  <tr>
    <td>Ville </td>
    <td>:</td>
    <td>
      <input name="ville" type="text" class="textbox" id="textfield">    </td>
  </tr>
  <tr>
    <td>Surface Min.</td>
    <td>:</td>
    <td><input name="surfaceMin" type="text" class="textbox" id="textfield3"></td>
  </tr>
 
  <tr>
    <td>Surface Max.</td>
    <td>:</td>
    <td><input name="surfaceMax" type="text" class="textbox" id="textfield3"></td>
  </tr>
  
   <tr>
    <td>Prix Min.</td>
    <td>:</td>
    <td><input name="prixMin" type="text" class="textbox" id="textfield3"></td>
  </tr>
  
   <tr>
    <td>Prix Max.</td>
    <td>:</td>
    <td><input name="prixMax" type="text" class="textbox" id="textfield3"></td>
  </tr>

  <tr>
    <td>Type de bien :</td>
    <td>:</td>
    <td><select name="type" id="select" multiple>
	        <c:forEach items="${types}" var="t">
				<option value="${t.identifiant}" selected>${t.identifiant} - ${t.description}</option>
	        </c:forEach>
     	</select></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td></td>
    <td></td>
  </tr>

  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>
      <input name="rechercher" type="submit" class="search" id="button" value="RECHERCHER">    </td>
  </tr>
    <tr>
    <td>&nbsp;</td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td></td>
    <td></td>
  </tr>
  

</table>
</form>
    </div> <!-- recherche -->
    </div>
  
<br/>
<br/>
  <div id="content_bottom"><table width="900" border="0" align="center" cellpadding="0" cellspacing="0">
  <h2>Les annonces en cours !</h2>
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
			          <td align="center"><div>Ville : ${p.ville.nom}</div></td>
			        </tr>
			        <tr>
										
					<td align="center"><div>Type : ${p.type.identifiant} - ${p.type.description}</div>
					</td>

					</tr>				
			        <tr>
			          <td align="center"><div  class="">Montant : ${p.montant} euros</div></td>
			          <tr>
			          <td align="center"><div>Surface : ${p.surface} m²</div></td>
			        </tr>
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
</table>
    </div>
    </td>
  </tr>
</table>
</div>


<jsp:include page="jsp/footer/footer.jsp" flush="true" />