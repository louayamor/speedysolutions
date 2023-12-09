package controller;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JList;

import dao.ServiceDAO;
import entity.Service;
import util.DBConnection;


public class ServiceController implements ServiceDAO{
    
    private static ServiceController instance;
    private Statement st;
    private ResultSet rs;
   
  
    
    private ServiceController() {
        DBConnection db= DBConnection.getInstance();
        try {
            st=db.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ServiceController getInstance(){
        if(instance==null) 
            instance=new ServiceController();
        return instance;
    }

    @Override
    public void insert(Service s) {
        String req="insert into Service (name,descr,prix,file,cat) values ('"+s.getName()+"','"+s.getDesc()+"','"+s.getPrix()+"','"+s.getFile()+"','"+s.getCat()+"')";
        try {
           st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    @Override
    public void delete(Service s) {
        String req="delete from service where id="+s.getId();
        Service p=displayById(s.getId());
        
          if(s!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("doesn't exist");
    }
  
    @Override
    public JList<Service> displayAll() {
        String req = "select * from service";
        List<Service> serviceList = new ArrayList<>();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                Service s = new Service();
                s.setId(rs.getInt(1));
                s.setName(rs.getString("name"));
                s.setDesc(rs.getString("descr"));
                s.setPrix(rs.getInt("prix"));
                s.setFile(rs.getString("file"));
                s.setCat(rs.getString("cat"));
                serviceList.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Service[] serviceArray = serviceList.toArray(new Service[0]);
        JList<Service> serviceJList = new JList<>(serviceArray);

        return serviceJList;
    }

    public List<Service> displayAllList() {
        String req="select * from service";
        List<Service> list=new ArrayList<>();
        
       try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Service s= new Service();
                s.setId(rs.getInt(1));
                s.setName(rs.getString("name"));
                s.setDesc(rs.getString("descr"));
                 s.setPrix(rs.getInt("prix"));
                 s.setFile(rs.getString("file"));
                 s.setCat(rs.getString("cat"));
                list.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Service displayById(int id) {
           String req="select * from service where id ="+id;
           Service s=new Service();
        try {
            rs=st.executeQuery(req);
           while(rs.next()){
           
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDesc(rs.getString("descr"));
                s.setPrix(rs.getInt("prix"));
                s.setFile(rs.getString("file"));
                s.setCat(rs.getString("cat"));
              
          }  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    return s;
    }

    @Override
     public boolean update(int id,String n,String m,int p,String f) {
        String qry = "UPDATE service SET name = '"+n+"', descr = '"+m+"',prix ='"+p+"',file ='"+f+"'  WHERE id = " +id ;
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    @Override
    public int getSId() {
    
        String req="SELECT LAST_INSERT_ID()" ;
      
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                 return rs.getInt(1);   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
     public ArrayList<String> getCategories(){

    
         String req="select cat FROM service";
   ArrayList<String> categories = new ArrayList<>();

        try {
            rs =  st.executeQuery(req);
            while (rs.next()) {
               categories.add(rs.getString("cat"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
     }
}
  
    


   
    

