
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class Frame2 extends JFrame{

    Frame2(String str1,String str2,String suser,String spass){
        super("University Management System");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container pane=this.getContentPane();
        JPanel panel0=new JPanel();
        JTabbedPane jtp=new JTabbedPane();
        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        JLabel title=new JLabel("Student Profile",JLabel.CENTER);
        title.setFont(new Font("times new roman",Font.BOLD,22));
        JPanel panel3=new JPanel();


        Box box1=Box.createVerticalBox();
        jtp.addTab("Profile",panel3);
        jtp.addTab("Subject Enrolement", panel2);


        panel1.setLayout(new BorderLayout());
        panel1.add(title);

        box1.add(Box.createVerticalStrut(20));
        box1.add(panel1); panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        box1.add(Box.createVerticalStrut(20));
        box1.add(jtp);
        box1.add(Box.createVerticalStrut(40));

        panel2.setLayout(new GridBagLayout());
        GridBagConstraints gc=new GridBagConstraints();

        JLabel sub=new JLabel("Subject"); sub.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel sta=new JLabel("Status");  sta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel sub1=new JLabel("SENG 12223 Database Design and Development",JLabel.LEFT);
        JLabel sub2=new JLabel("SENG 12213 Data Structures and Algorithms",JLabel.LEFT);
        JLabel sub3=new JLabel("SENG 12233 Object Oriented Programming",JLabel.LEFT);

        JCheckBox en1=new JCheckBox("Enrolled");
        JCheckBox en2=new JCheckBox("Enrolled");
        JCheckBox en3=new JCheckBox("Enrolled");

        Insets in=new Insets(0,0,0,0); gc.ipadx=10; gc.ipady=10;
        gc.gridx=0; gc.gridy=0; gc.insets=in; gc.fill=GridBagConstraints.BOTH;
        panel2.add(sub,gc);
        gc.gridx=GridBagConstraints.RELATIVE; gc.gridy=0; gc.gridwidth=GridBagConstraints.REMAINDER;
        panel2.add(sta,gc);
        gc.gridx=0; gc.gridy=1; gc.gridwidth=1;
        panel2.add(sub1,gc);
        gc.gridx=GridBagConstraints.RELATIVE; gc.gridy=1; gc.gridwidth=GridBagConstraints.REMAINDER;
        panel2.add(en1,gc);

        gc.gridx=0; gc.gridy=2; gc.gridwidth=1;
        panel2.add(sub2,gc);
        gc.gridx=GridBagConstraints.RELATIVE; gc.gridy=2; gc.gridwidth=GridBagConstraints.REMAINDER;
        panel2.add(en2,gc);

        gc.gridx=0; gc.gridy=3; gc.gridwidth=1;
        panel2.add(sub3,gc);
        gc.gridx=GridBagConstraints.RELATIVE; gc.gridy=3; gc.gridwidth=GridBagConstraints.REMAINDER;
        panel2.add(en3,gc);




        //BorderLayout bd;
        //panel0.setLayout(bd=new BorderLayout());
        //bd.setHgap(200); bd.setVgap(20);
        panel0.add(box1);
        pane.add(panel0);
        this.pack();
        //this.setSize(500,400);
        this.setLocation(200,200);
        this.setVisible(true);


        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybase",suser,spass);
            PreparedStatement ps=con.prepareStatement("select* from student where username=? and pass=?");
            ps.setString(1, str1); ps.setString(2,str2);
            String sid;
            ResultSet rs=ps.executeQuery();
            rs.next();
            sid=rs.getString(4);
            PreparedStatement ps2=con.prepareStatement("select* from subjects where id=?");
            ps2.setString(1,sid);
            ResultSet rs2=ps2.executeQuery();
            rs2.next();
            if(rs2.getInt(2)==1){en1.setSelected(true);}
            if(rs2.getInt(3)==1){en2.setSelected(true);}
            if(rs2.getInt(4)==1){en3.setSelected(true);}

            JLabel title2=new JLabel("Student Profile");
            title2.setFont(new Font("times new roman",Font.BOLD,22));
            JPanel panel5=new JPanel(new BorderLayout());
            panel5.add(title2);
            JLabel plb1=new JLabel("Student name"); JLabel p2lb1=new JLabel();
            JLabel plb2=new JLabel("Student ID");   JLabel p2lb2=new JLabel();
            JLabel plb3=new JLabel("Student Age");  JLabel p2lb3=new JLabel();
            JLabel plb4=new JLabel("Student N.I.C");    JLabel p2lb4=new JLabel();

            JPanel panel4=new JPanel();
            panel4.setLayout(new GridBagLayout());
            panel3.setLayout(new FlowLayout());
            GridBagConstraints gc3=new GridBagConstraints();

            Box box2=Box.createVerticalBox();
            box2.add(Box.createVerticalStrut(20));
            box2.add(panel5);
            box2.add(Box.createVerticalStrut(20));
            box2.add(panel4);
            box2.add(Box.createVerticalStrut(20));

            Insets in2=new Insets(10,10,10,10);
            gc3.gridx=0; gc3.gridy=0; gc3.insets=in2;
            panel4.add(plb1,gc3);
            gc3.gridx=1; gc3.gridy=0; gc3.gridwidth=GridBagConstraints.REMAINDER;
            panel4.add(p2lb1,gc3);
            gc3.gridx=0; gc3.gridy=1; gc3.gridwidth=1;
            panel4.add(plb2,gc3);
            gc3.gridx=1; gc3.gridy=1; gc3.gridwidth=GridBagConstraints.REMAINDER;
            panel4.add(p2lb2,gc3);
            gc3.gridx=0; gc3.gridy=2; gc3.gridwidth=1;
            panel4.add(plb3,gc3);
            gc3.gridx=1; gc3.gridy=2; gc3.gridwidth=GridBagConstraints.REMAINDER;
            panel4.add(p2lb3,gc3);
            gc3.gridx=0; gc3.gridy=3; gc3.gridwidth=1;
            panel4.add(plb4,gc3);
            gc3.gridx=1; gc3.gridy=3; gc3.gridwidth=GridBagConstraints.REMAINDER;
            panel4.add(p2lb4,gc3);

            panel3.setLayout(new FlowLayout());
            panel3.add(box2);

            p2lb1.setText(rs.getString(3));
            p2lb2.setText(rs.getString(4));
            p2lb3.setText(String.valueOf(rs.getInt(5)));
            p2lb4.setText(rs.getString(6));
            this.pack();
        }catch(Exception en){System.out.println(en);}







    }


}
