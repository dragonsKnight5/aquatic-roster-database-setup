/*
 * Copyright 2016 james.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
            JOptionPane.showMessageDialog(null, ex);
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
            JOptionPane.showMessageDialog(null, command + ex);
        }
         boolean result = false ;
         if (count == 1)
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
            JOptionPane.showMessageDialog(null, command + ex);
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
         String command = "CREATE TABLE `lifeguard` ("
                 + "  `ID` int(4) NOT NULL AUTO_INCREMENT,"
                 + "  `shift_date` date NOT NULL,"
                 + "  `start_time` time NOT NULL,"
                 + "  `end_time` time NOT NULL,"
                 + "  `location` varchar(65) NOT NULL,"
                 + "  `staff1` varchar(65) NOT NULL,"
                 + "  `staff2` varchar(65) NOT NULL,"
                 + "  `staff3` varchar(65) NOT NULL,"
                 + "  PRIMARY KEY (ID)"
                 + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
         
         try
         {
             ps1 = conn.prepareStatement(command);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, command + ex);
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
         String command = "CREATE TABLE `LTS_Shift` ("
                 + "  `ID` int(4) NOT NULL AUTO_INCREMENT,"
                 + "  `staff` varchar(65) NOT NULL,"
                 + "  `shift_day` varchar(10) NOT NULL,"
                 + "  `location` varchar(65) NOT NULL,"
                 + "  `start_time` time NOT NULL,"
                 + "  `end_time` time NOT NULL,"
                 + "  `start_date` date NOT NULL,"
                 + "  `end_date` date NOT NULL,"
                 + "  PRIMARY KEY (ID)"
                 + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
         
         try
         {
             ps1 = conn.prepareStatement(command);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, command + ex);
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
         String command = "CREATE TABLE `LTS_Covers` ("
                 + "  `ID` int(4) NOT NULL AUTO_INCREMENT,"
                 + "  `cover_date` date NOT NULL,"
                 + "  `start_time` time NOT NULL,"
                 + "  `end_time` time NOT NULL,"
                 + "  `location` varchar(65) NOT NULL,"
                 + "  `staff` varchar(65) NOT NULL,"
                 + "  `cover_for` varchar(65) NOT NULL,"
                 + "  PRIMARY KEY (ID)"
                 + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
         
         try
         {
             ps1 = conn.prepareStatement(command);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, command + ex);
        }
         boolean result = false ;
         if (count == 0)
         {
             result = true;
         }
        return result;
     }
          
     public boolean  addUser(String username, String firstName, String lastName, String department1, String department2, String department3, boolean supervisor, String password)
     {
         int count = 0;
         String command = "insert into users (username, first_name, last_name, department_1, department_2, department_3, supervisor, password) values  " 
                +"(\"" + username + "\", \"" + firstName + "\", \"" + lastName + "\", \"" + department1 + "\", \"" + department2 + "\", \"" + department3 + "\", " + supervisor + ", \"" + password + "\")";
         
         try
         {
             ps1 = conn.prepareStatement(command);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, command + ex);
        }
         boolean result = false;
         if (count == 1)
         {
             result = true;
         }
        return result;
     }
     
     public boolean alterLifeguardTable()
     {
         int count = 1;
         String command = "ALTER TABLE `lifeguard`" +
        "  ADD KEY `staff1` (`staff1`)," +
        "  ADD CONSTRAINT `staffFK` FOREIGN KEY (`staff1`) REFERENCES `users` (`username`)";
         
         try
         {
             ps1 = conn.prepareStatement(command);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, command + ex);
        }
         boolean result = false ;
         if (count == 0)
         {
             result = true;
         }
        return result;
     }
     
     public boolean createLocationTable()
     {
         int count = 1;
         String command = "CREATE TABLE `location` ("
                 + "  `ID` int(4) NOT NULL AUTO_INCREMENT,"
                 + "  `Location` varchar(65) NOT NULL,"
                 + "  `lifeguard` tinyint(1) DEFAULT NULL,"
                 + "  `lts` tinyint(1) DEFAULT NULL,"
                 + "  `gym` tinyint(1) DEFAULT NULL,"
                 + "  PRIMARY KEY (ID)"
                 + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
         
         try
         {
             ps1 = conn.prepareStatement(command);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, command + ex);
        }
         boolean result = false ;
         if (count == 0)
         {
             result = true;
         }
        return result;
     }
     
     public boolean insertLocationEntry()
     {
         int count = 1;
         String command = "INSERT INTO `location` (`ID`, `Location`, `lifeguard`, `lts`, `gym`) VALUES (1, 'None', 1, 1, 1)";
         
         try
         {
             ps1 = conn.prepareStatement(command);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, command + ex);
        }
         boolean result = false ;
         if (count == 0)
         {
             result = true;
         }
        return result;
     }
     
     public boolean databaseUser(String databaseUser, String network, String databasePassword)
     {
         int count = 1;
         String command = "create user \'" +databaseUser + "\'@\'" + network + "\' identified by \'" + databasePassword +"\'";
         System.out.println(command);
         try
         {
             ps1 = conn.prepareStatement(command);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, command + ex);
            System.out.println(ex);
        }
         boolean result = false ;
         if (count == 0)
         {
             result = true;
         }
        return result;
     }
     
     public boolean databaseGrants(String network)
     {
         int count = 1;
         String command = "grant select, insert, delete, alter on staff.* to \'staff\'@\'" + network +"\'";
         System.out.println(command);
         try
         {
             ps1 = conn.prepareStatement(command);
             count = ps1.executeUpdate();
         }
         catch (SQLException ex)
        {
            JOptionPane.showMessageDialog(null, command + ex);
            System.out.println(ex);
        }
         boolean result = false ;
         if (count == 0)
         {
             result = true;
         }
        return result;
     }
}
