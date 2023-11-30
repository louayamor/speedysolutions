package dao;

public interface WrapperDAO {
	public boolean isAdmin(int id);

    public boolean isFreelancer(int id);

    public boolean isBusinessOwner(int id);
}
