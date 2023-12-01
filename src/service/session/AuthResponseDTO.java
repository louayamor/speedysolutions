package service.session;

public class AuthResponseDTO {
	
	private int id;
    private String username;  
    private String email;
    private String role;   
    private String password;
    private Boolean isBanned;
    private Boolean isVerified;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getIsBanned() {
		return isBanned;
	}
	public void setIsBanned(Boolean isBanned) {
		this.isBanned = isBanned;
	}
	public Boolean getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(Boolean isVerified) {
		this.isVerified = isVerified;
	}
	@Override
	public String toString() {
		return "AuthResponseDTO [id=" + id + ", username=" + username + ", email=" + email + ", role=" + role
				+ ", password=" + password + ", isBanned=" + isBanned + ", isVerified=" + isVerified + "]";
	}
    
    


}
