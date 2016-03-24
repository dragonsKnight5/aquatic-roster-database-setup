

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * @author james
 */
public class dbConnection
{
    private Connection conn;
    private String sql;
    private PreparedStatement ps1 = null;
    String table = null;
    Boolean isConnected = false;
    
    public dbConnection ()
    {
        
    }

    public boolean firstConnect(String username, String password, String address)
    {
        try
        {
            conn = DriverManager.getConnection ("jdbc:mysql://"+address+"/?user="+username+"&password="+password);
           isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Connection Failed,\nEither no database is available or login credentials are incorrect");
            isConnected = false;
        }
        return isConnected;
    }
    
    public boolean secondConnect(String username, String password, String address)
    {
        try
        {
            conn = DriverManager.getConnection("jdbc:mysql://"+address+"/staff?user="+username+"&password="+password);
           isConnected = true;
        }
        catch (SQLException ex)
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Connection Failed,\nEither no database is available or login credentials are incorrect");
            isConnected = false;
        }
        return isConnected;
    }
    
    public Boolean connected()
    {
        return isConnected;
    }
    
     public void close()
    {
        try
        {
            conn.close();
        }
        catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage() + ex.getSQLState() + ex.getErrorCode());
        }
    }
     
     public boolean createDatabase()
     {
         int count = 0;
         String command = "create database staff";
         try
         {
             ps1 = conn.prepareStatement(command);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, command + ex.getMessage() + ex.getSQLState() + ex.getErrorCode());
        }
         boolean result = false ;
         if (count ==1)
         {
             result = true;
         }
        return result;
     }
     
     public boolean createUserTable()
     {
         int count = 1;
         String command = "CREATE TABLE `users` (" +
        "  `ID` int(4) NOT NULL AUTO_INCREMENT," +
        "  `username` varchar(65) NOT NULL," +
        "  `first_name` varchar(65) NOT NULL," +
        "  `last_name` varchar(65) NOT NULL," +
        "  `department_1` varchar(25) NOT NULL," +
        "  `department_2` varchar(25) DEFAULT NULL," +
        "  `department_3` varchar(25) DEFAULT NULL," +
        "  `supervisor` tinyint(1) DEFAULT NULL," +
        "  `password` varchar(65) NOT NULL," +
        "  PRIMARY KEY (ID)," +
        "  UNIQUE (username)" +
        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
         
         try
         {
             ps1 = conn.prepareStatement(command);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, command + ex.getMessage() + ex.getSQLState() + ex.getErrorCode());
        }
         boolean result = false ;
         if (count == 0)
         {
             result = true;
         }
        return result;
     }
     
     public boolean createLifeguardTable()
     {
         int count = 1;
         String command = "CREATE TABLE `lifeguard` (" +
        "  `ID` int(4) NOT NULL AUTO_INCREMENT," +
        "  `shift_date` date NOT NULL," +
        "  `start_time` time NOT NULL," +
        "  `end_time` time NOT NULL," +
        "  `staff1` varchar(65) NOT NULL," +
        "  `staff2` varchar(65) NOT NULL," +
        "  `staff3` varchar(65) NOT NULL," +
        "  PRIMARY KEY (ID)" +
        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
         
         try
         {
             ps1 = conn.prepareStatement(command);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, command + ex.getMessage() + ex.getSQLState() + ex.getErrorCode());
        }
         boolean result = false ;
         if (count == 0)
         {
             result = true;
         }
        return result;
     }
     
     public boolean createLTSTable()
     {
         int count = 1;
         String command = "CREATE TABLE `LTS_Shift` (" +
        "  `ID` int(4) NOT NULL AUTO_INCREMENT," +
        "  `staff` varchar(65) NOT NULL," +
        "  `shift_day` varchar(10) NOT NULL," +
        "  `start_time` time NOT NULL," +
        "  `end_time` time NOT NULL," +
        "  `start_date` date NOT NULL," +
        "  `end_date` date NOT NULL," +
        "  PRIMARY KEY (ID)" +
        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
         
         try
         {
             ps1 = conn.prepareStatement(command);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, command + ex.getMessage() + ex.getSQLState() + ex.getErrorCode());
        }
         boolean result = false ;
         if (count == 0)
         {
             result = true;
         }
        return result;
     }
     
     public boolean createCoversTable()
     {
         int count = 1;
         String command = "CREATE TABLE `LTS_Covers` (" +
        "  `ID` int(4) NOT NULL AUTO_INCREMENT," +
        "  `cover_date` date NOT NULL," +
        "  `start_time` time NOT NULL," +
        "  `end_time` time NOT NULL," +
        "  `staff` varchar(65) NOT NULL," +
        "  `cover_for` varchar(65) NOT NULL," +
        "  PRIMARY KEY (ID)" +
        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
         
         try
         {
             ps1 = conn.prepareStatement(command);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, command + ex.getMessage() + ex.getSQLState() + ex.getErrorCode());
        }
         boolean result = false ;
         if (count == 0)
         {
             result = true;
         }
        return result;
     }
          
     public int addUser(String addCommand)
     {
         int count = 0;
         
         try
         {
             ps1 = conn.prepareStatement(addCommand);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, addCommand + ex.getMessage() + ex.getSQLState() + ex.getErrorCode());
        }
        return count;
     }
     
     public ResultSet lookup(String command)
     {
         ResultSet lookup = null;
         
         try
         {
             ps1 = conn.prepareStatement(command);
             lookup = ps1.executeQuery();
         }
         catch (SQLException ex)
         {
             JOptionPane.showMessageDialog(null, ex.getMessage() + ex.getSQLState() + ex.getErrorCode());
         }
         return lookup;
     }
}
