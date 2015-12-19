<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="../header/header.jsp" flush="true" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="content">
	<table width="928" border="0" cellspacing="0" cellpadding="0">
		<form action="connexion_employe" method="post">
			<div id="compte02" style="margin-top: auto;">
				<div id="compte02Title">
					<h2>Identifiez-vous</h2>
					<h3>Pour vous connecter, veuillez saisir votre identifiant et
						votre mot de passe.</h3>
						
					<span style="color:red; font-weight: bold;">
					<% if (request.getAttribute("message") != null ) {
							out.print(request.getAttribute("message"));
							request.setAttribute("message", null);
						}
					%></span>		
				</div>
				<div id="compte02Cont">
					<div id="form00">
						<div class="form-item" id="edit-name-wrapper">
							<label for="edit-name">Identifiant <span
								class="form-required" title="Ce champ est obligatoire.">*</span></label>
							<input name="login" type="text" id="LoginMember_UserName"
								class="form-text required" style="width: 240px;">
						</div>
						<br>
						<div class="form-item" id="edit-pass-wrapper">
							<label for="edit-pass">Mot de passe <span
								class="form-required" title="Ce champ est obligatoire.">*</span></label>
							<input name="pwd" type="password" id="LoginMember_Password"
								style="width: 240px;">
						</div>
						<br>
						<p class="compte">
							<input type="submit" name="LoginMember$login_btn"
								value="J'accède à mon compte" id="LoginMember_login_btn"
								class="form-submit btnConnexion" style="width: 240px;">
							<input type="hidden" name="connexion_employe"
								value="connexion" />
						</p>
					</div>
				</div>
			</div>
		</form>
</div>
<br/>
<br/>

		<jsp:include page="../footer/footer.jsp" flush="true" />