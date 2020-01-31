
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class Frame1 extends JFrame{

    JRadioButton rb1=new JRadioButton("Lecturer");
    JRadioButton rb2=new JRadioButton("Student");

    JLabel title=new JLabel("UNIVERSITY MANAGEMENT SYSTEM",JLabel.CENTER);
    JLabel logname=new JLabel("User Name :");
    JLabel logpass=new JLabel("PassWord :");
    JLabel signname=new JLabel("User Name :");
    JLabel signpass=new JLabel("PassWord :");
    JLabel signrepass=new JLabel("Re enter PassWord");


    JButton blog=new JButton("Log In");
    JButton bsign=new JButton("Sign Up");

    JTextField tlogname=new JTextField(20);
    JPasswordField tlogpass=new JPasswordField(20);
    JTextField tsignname=new JTextField(20);
    JPasswordField tsignpass=new JPasswordField(20);
    JPasswordField tsignrepass=new JPasswordField(20);

    JSeparator js=new JSeparator(SwingConstants.HORIZONTAL);



    Frame1(String suser,String spass){

        super("University Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane=this.getContentPane();
        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        JPanel panel3=new JPanel();
        JPanel panel0=new JPanel();

        ButtonGroup group=new ButtonGroup();

        group.add(rb1);
        group.add(rb2);



        rb2.setSelected(true);


        Box box=Box.createVerticalBox();
        panel0.add(box);
        box.add(Box.createVerticalStrut(20));
        box.add(panel1);
        box.add(Box.createVerticalStrut(20));
        box.add(js);
        box.add(Box.createVerticalStrut(12));
        box.add(rb1);
        box.add(rb2);
        box.add(Box.createVerticalStrut(20));
        box.add(panel2);
        box.add(Box.createVerticalStrut(40));
        box.add(panel3);
        box.add(Box.createVerticalStrut(20));

        panel2.setBorder(BorderFactory.createTitledBorder("Log In"));
        panel3.setBorder(BorderFactory.createTitledBorder("Sign Up"));

        panel1.setLayout(new BorderLayout());
        //title.setAlignmentX(CENTER_ALIGNMENT);
        panel1.add(title);
        //title.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        title.setFont(new Font("times new roman",Font.BOLD,18));

        panel2.setLayout(new GridBagLayout());
        GridBagConstraints gc=new GridBagConstraints();

        gc.gridx=0; gc.gridy=0; gc.insets=new Insets(10,10,10,10);


        panel2.add(logname,gc);
        gc.gridx=GridBagConstraints.RELATIVE; gc.gridy=0; gc.gridwidth=GridBagConstraints.REMAINDER;
        panel2.add(tlogname,gc);
        gc.gridx=0; gc.gridy=1; gc.gridwidth=1;
        panel2.add(logpass,gc);
        gc.gridx=GridBagConstraints.RELATIVE; gc.gridy=1; gc.gridwidth=GridBagConstraints.REMAINDER;
        panel2.add(tlogpass,gc);
        gc.gridx=0; gc.gridy=3; gc.fill=GridBagConstraints.BOTH;
        panel2.add(blog,gc);


        panel3.setLayout(new GridBagLayout());
        gc.gridx=0; gc.gridy=0; gc.insets=new Insets(10,10,10,10);
        panel3.add(signname,gc);
        gc.gridx=1; gc.gridy=0; gc.gridwidth=GridBagConstraints.REMAINDER;
        panel3.add(tsignname,gc);
        gc.gridx=0; gc.gridy=1; gc.gridwidth=1;
        panel3.add(signpass,gc);
        gc.gridx=GridBagConstraints.RELATIVE; gc.gridy=1; gc.gridwidth=GridBagConstraints.REMAINDER;
        panel3.add(tsignpass,gc);
        gc.gridx=0; gc.gridy=2; gc.gridwidth=1;
        panel3.add(signrepass,gc);
        gc.gridx=GridBagConstraints.RELATIVE; gc.gridy=2; gc.gridwidth=GridBagConstraints.REMAINDER;
        panel3.add(tsignrepass,gc);
        gc.gridx=0; gc.gridy=3; gc.fill=GridBagConstraints.BOTH;
        panel3.add(bsign,gc);

        JMenuBar jmb=new JMenuBar();
        //this.setJMenuBar(jmb);
        //jmb.setVisible(true);

        JMenu file=new JMenu("File");
        JMenu help=new JMenu("Help");
        JMenu about=new JMenu("About");

        jmb.add(file); jmb.add(help); jmb.add(about);

        pane.add(panel0);
        this.setLocation(500,100);
        this.setSize(800,600);
        this.pack();
        this.setResizable(false);
        this.setVisible(true);

        bsign.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){

                String str1; char[] str2,str3;
                str1=tsignname.getText();
                str2=tsignpass.getPassword();
                str3=tsignrepass.getPassword();
                if(String.valueOf(str2).compareTo(String.valueOf(str3))!=0||str2.length==0){
                    JOptionPane.showMessageDialog(null, "PassWord fields do not match");
                    return;
                }

                if(rb2.isSelected()){Frame3 frm3=new Frame3(str1,String.valueOf(str2),suser,spass);}
                else if(rb1.isSelected()){Frame32 frm32=new Frame32(str1,String.valueOf(str2),suser,spass);}

            }

        });

        blog.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                String str1,str2;
                str1=tlogname.getText();
                str2=String.valueOf(tlogpass.getPassword());

                if(str1.length()==0||str2.length()==0){JOptionPane.showMessageDialog(null, "Both Fields should be filled"); return;}
                try{

                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybase",suser,spass);
                    ResultSet rs;
                    if(rb1.isSelected()){
                        PreparedStatement ps=con.prepareStatement("select* from lecturer where username=? and pass=?");
                        ps.setString(1,str1);
                        ps.setString(2,str2);
                        rs=ps.executeQuery();
                        int num=0;
                        while(rs.next()){++num;}
                        if(num>0){new Frame22(str1,str2,suser,spass); return;}
                        JOptionPane.showMessageDialog(null, "Password or Username Wrong...");
                        return;
                    }
                    else{
                        PreparedStatement ps=con.prepareStatement("select* from student where username=? and pass=?");
                        ps.setString(1,str1);
                        ps.setString(2,str2);
                        rs=ps.executeQuery();
                        int num=0;
                        while(rs.next()){++num;}
                        if(num>0){new Frame2(str1,str2,suser,spass); return;}
                        JOptionPane.showMessageDialog(null, "Password or Username Wrong...");

                    }


                }catch(Exception en){System.out.println(en);}

            }
        });


    }





}

