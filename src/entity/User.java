package entity;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
	private int idUser;
    private String username, email,role;
    private char [] password;
    private int isVerified =0;
    private int isBanned =0;
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());
    private Timestamp updatedAt = new Timestamp(System.currentTimeMillis());
    
    
    
    public User(int id) {
        this.idUser = id;
    }
	
	public User(int id, String username, char[] password, String email, String role, int isVerified, int isBanned,
			Timestamp createdAt, Timestamp updatedAt) {
		super();
		this.idUser = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.isVerified = isVerified;
		this.isBanned = isBanned;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}


	

	public User(String username, char[] password, String email, String role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
	}



	public int getId() {
		return idUser;
	}
	public void setId(int id) {
		this.idUser = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
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
	public int getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}
	public int getIsBanned() {
		return isBanned;
	}
	public void setIsBanned(int isBanned) {
		this.isBanned = isBanned;
	}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
    
	
    @Override
	public int hashCode() {
		return Objects.hash(email, password, role, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password)
				&& Objects.equals(role, other.role) && Objects.equals(username, other.username);
	}

    @Override
    public String toString() {
        return "User{" + "id=" + idUser + ", username=" + username + ", password=" + password.toString() + ", email=" + email + ", role=" + role + '}';
    }

}
