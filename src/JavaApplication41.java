

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class JavaApplication41 {


    public static void main(String[] args) {

        try{
            String suser,spass;
            suser=JOptionPane.showInputDialog(null,"Please Enter MySql user name default \"root\"");
            spass=JOptionPane.showInputDialog(null,"Please enter MySql user password default \"root\"");
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/",suser,spass);
            Statement st=con.createStatement();
            ResultSet rs;
            rs=st.executeQuery("show databases");
            boolean flag=false;
            String base="mybase";
            while(rs.next()){

                if(base.compareToIgnoreCase(rs.getString(1))==0){flag=true; break;}

            }
            if(!flag){

                int res=JOptionPane.showConfirmDialog(null,"The database is not detected. Do you want to create the Database.","No database",
                        JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);

                if(res==JOptionPane.NO_OPTION||res==JOptionPane.CLOSED_OPTION){System.exit(0);}
                st.executeUpdate("create database mybase");
                st.executeUpdate("use mybase");
                st.executeUpdate("create table student(username "
                        + "varchar(40),pass varchar(40),name varchar(40),id varchar(40),age int(3),nic varchar(20))");

                st.executeUpdate("create table lecturer(username "
                        + "varchar(40),pass varchar(40),name varchar(40),id varchar(40),age int(3),nic varchar(20))");

                st.executeUpdate("create table subjects(id varchar(40),course1 int(10),course2 int(10),course3 int(10))");

            }

            Frame1 fr1=new Frame1(suser,spass);

        }catch(Exception e){JOptionPane.showMessageDialog(null,"error connection with database"); System.out.println(e);}



    }

}

