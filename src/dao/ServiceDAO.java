package dao;


import javax.swing.JList;

import entity.Service;

/**
*
* @param <Service>
*/

public interface ServiceDAO {
	
	public void insert(Service s);
    public int getSId();
    public void delete(Service s);
    public JList<Service> displayAll();
    public Service displayById(int id_p);
    
    public boolean update(int id ,String n , String d,int p,String f);
}
