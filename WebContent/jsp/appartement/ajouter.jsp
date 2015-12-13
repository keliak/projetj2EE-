<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="content">
	<jsp:include page="../header/header.jsp" flush="true" />

	<table width="928" border="0" cellspacing="0" cellpadding="0">
	<form method="post" action="ajouterBien">
		<h2 style="color:red; font-weight: bold;">
							<% if (request.getAttribute("messageErreur") != null ) {
							out.print(request.getAttribute("messageErreur"));
						}
					%></h2>
					
		<h2 style="color:green; font-weight: bold;">
							<% if (request.getAttribute("messageSucces") != null ) {
							out.print(request.getAttribute("messageSucces"));
						}
					%></h2>		
			<table width="100%" cellpadding="10" cellspacing="10">
				<tr>
					<th scope="row"><table width="100%" border="1"
							cellpadding="10" cellspacing="10">
							<tr>
								<td colspan="2" class="txt-ttespace"><b><h1>Ajouter un bien</h1> </b>
								</td>

							</tr>

							<tr>
								<td>
									<table align="center">
										<tr>
											<td align="right" class="txtespace-orange">Adresse :</td>
											<td><input name="adresse" type="text" class="champstxtesp" />
											</td>
										</tr>
										<tr>
											<td align="right" class="txtespace-orange">Surface :</td>
											<td><input name="surface" type="text" class="champstxtesp" />
											</td>
										</tr>
										<tr>
											<td align="right" class="txtespace-orange">Montant :</td>
											<td><input name="montant" type="text" class="champstxtesp" />
											</td>
										</tr>
										<tr>
											<td align="right" class="txtespace-orange">Image :</td>
											<td><input name="image" type="text" class="champstxtesp" />
											</td>
										</tr>
										<tr>
											<td align="right" class="txtespace-orange">Type :</td>
										
											<td>
												<select name="type" size="1">
													<c:forEach var="type"  items="${types}">
														<option value="${type.identifiant}">${type.identifiant} - ${type.description}</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										<tr>
											<td align="right" class="txtespace-orange">Ville :</td>
										
											<td>
												<select name="ville" size="1">
													<c:forEach var="ville"  items="${villes}">
														<option value="${ville.nom}">${ville.codePostal} - ${ville.nom}</option>
													</c:forEach>
												</select>
											</td>
										</tr>
										
									</table></td>
							</tr>

							<tr>
								<td align="right" colspan="2"><input type="hidden"
									name="ajouterBien_proprietaire" value="ajouterBien"> <input
									name="imageField" type="image" src="images/valider.gif"
									border="0">
								</td>
							</tr>

						</table>
					</th>
				</tr>
			</table>

		</form>

</table>

</div>
<jsp:include page="../footer/footer.jsp" flush="true" />