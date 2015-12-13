package metier.form;

import metier.entity.Utilisateur;

public class SessionForm {
	
	private Utilisateur user;
	private String role;
	
	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
