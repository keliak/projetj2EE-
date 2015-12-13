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
	    <p>This is <span class="orange">Free House Sale template</span> 
	    for your website design which is under Real Estate Template category. 
	    You can customize the layout of the html page any way you like. 
	    So to choose the best location or design as expected for their homes 
	    internet is the best option. </span>All these templates are designed to give 
	    a real life look of a home or property, with refreshing neighborhood.</p>
	    <div id="house_sales">
	      <table width="275" border="0" cellspacing="0" cellpadding="0" style="float:left;">
	        <tr>
	          <td align="center"><img src="images/appartement.jpg" alt="house_02" width="600" height="180"></td>
	        </tr>
	       
	      </table>
	    </div>
    </div>
	
	
 <div id="find">
    <h2>Find Your House</h2>
    <h3>Over 3 million properties</h3>
    <form>
    <table width="265" border="0" cellpadding="2" cellspacing="0" class="black">
  <tr>
    <td>City :</td>
    <td>:</td>
    <td>
      <input name="textfield" type="text" class="textbox" id="textfield">    </td>
  </tr>
  <tr>
    <td>State :</td>
    <td>:</td>
    <td>
      <select name="select" id="select">
        <option>Select a State</option>
        <option>Select a State</option>
      </select>    </td>
  </tr>
  <tr>
    <td>Zip:</td>
    <td>:</td>
    <td><input name="textfield2" type="text" class="textbox" id="textfield2"></td>
  </tr>
  <tr>
    <td>MLS# :</td>
    <td>:</td>
    <td><input name="textfield3" type="text" class="textbox" id="textfield3"></td>
  </tr>
  <tr>
    <td>Price :</td>
    <td>:</td>
    <td><select name="select2" id="select">
      <option>No minimum</option>
      <option>No minimum</option>
        </select></td>
  </tr>
  <tr>
    <td>to :</td>
    <td>:</td>
    <td><select name="select3" id="select">
      <option>No maximum</option>
      <option>No maximum</option>
        </select></td>
  </tr>
  <tr>
    <td>Beds :</td>
    <td>:</td>
    <td><select name="select4" id="select">
      <option>Any Number</option>
      <option>Any Number</option>
        </select></td>
  </tr>
  <tr>
    <td>Baths :</td>
    <td>:</td>
    <td><select name="select5" id="select">
      <option>Any Number</option>
      <option>Any Number</option>
        </select></td>
  </tr>
  <tr>
    <td>Radius :</td>
    <td>:</td>
    <td><select name="select6" id="select">
      <option>No Radius</option>
      <option>No Radius</option>
        </select></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>
      <input name="button" type="submit" class="find" id="button" value="">    </td>
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
			        <td align="center"><img src="images/${p.image}" alt="${p.image}" width="213" height="116"></td>
			     </tr>
			        <tr>
			          <td align="center"><div  class="house_sales">Montant : ${p.montant} euros</div></td>
			        </tr>
			        <tr>
			          <td align="center"><a href="#"><img src="images/consulter_annonce.jpg" alt="Consulter annonce" width="84" height="21"></a></td>
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