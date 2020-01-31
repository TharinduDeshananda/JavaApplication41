
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;




public class Frame32 extends JFrame{
    Frame32(String str1,String str2,String suser,String spass){

        super("University Management System");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Container pane=this.getContentPane();
        JPanel panel0=new JPanel();
        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        JPanel panel3=new JPanel();

        String arr[]={"Lecturer Name ","Lecturer ID ","Age ","N.I.C ","SENG 12223 Database Design and Development","SENG 12213 Data Structures and Algorithms","SENG 12233 Object Oriented Programming"};

        JLabel title=new JLabel("Lecturer Registration");
        title.setFont(new Font("times new roman",Font.BOLD,25));
        JLabel[] lbarr=new JLabel[7];
        for(int i=0;i<7;++i){
            lbarr[i]=new JLabel(arr[i],JLabel.LEFT);
        }

        JCheckBox ch1=new JCheckBox("Enroll");
        JCheckBox ch2=new JCheckBox("Enroll");
        JCheckBox ch3=new JCheckBox("Enroll");

        JTextField tx1=new JTextField(20);
        JTextField tx2=new JTextField(20);
        JTextField tx3=new JTextField(5);
        JTextField tx4=new JTextField(20);

        Box box1=Box.createVerticalBox();
        JButton reg=new JButton("Register");
        box1.add(panel1);
        box1.add(Box.createVerticalStrut(30));
        box1.add(panel2);
        box1.add(Box.createVerticalStrut(20));
        box1.add(panel3);
        box1.add(Box.createVerticalStrut(40));
        box1.add(reg);

        panel1.add(title,new BorderLayout());

        GridBagConstraints gc=new GridBagConstraints();
        GridBagConstraints gc2=new GridBagConstraints();

        Insets in=new Insets(10,10,10,10);
        gc.insets=in; gc.fill=GridBagConstraints.HORIZONTAL;
        panel2.setLayout(new GridBagLayout());
        gc.gridx=0; gc.gridy=0;
        panel2.add(lbarr[0],gc);
        gc.gridx=1; gc.gridy=0; gc.gridwidth=GridBagConstraints.REMAINDER;
        panel2.add(tx1,gc);
        gc.gridx=0; gc.gridy=1; gc.gridwidth=1;
        panel2.add(lbarr[1],gc);
        gc.gridx=1; gc.gridy=1; gc.gridwidth=GridBagConstraints.REMAINDER;
        panel2.add(tx2,gc);
        gc.gridx=0; gc.gridy=2; gc.gridwidth=1;
        panel2.add(lbarr[2],gc);
        gc.gridx=1; gc.gridy=2; gc.gridwidth=GridBagConstraints.REMAINDER; gc.fill=GridBagConstraints.NONE; gc.anchor=GridBagConstraints.WEST;
        panel2.add(tx3,gc);
        gc.gridx=0; gc.gridy=3; gc.gridwidth=1; gc.fill=GridBagConstraints.HORIZONTAL; gc.anchor=GridBagConstraints.CENTER;
        panel2.add(lbarr[3],gc);
        gc.gridx=1; gc.gridy=3; gc.gridwidth=GridBagConstraints.REMAINDER;
        panel2.add(tx4,gc);


        panel3.setLayout(new GridBagLayout());
        Insets in2=new Insets(10,10,10,10); gc2.insets=in2;
        gc2.gridx=0; gc2.gridy=0; gc2.gridwidth=1; gc2.fill=GridBagConstraints.HORIZONTAL;
        panel3.add(lbarr[4],gc2);
        gc2.gridx=1; gc2.gridy=0; gc2.gridwidth=GridBagConstraints.REMAINDER;
        panel3.add(ch1,gc2);
        gc2.gridx=0; gc2.gridy=1; gc2.gridwidth=1;
        panel3.add(lbarr[5],gc2);
        gc2.gridx=1; gc2.gridy=1; gc2.gridwidth=GridBagConstraints.REMAINDER;
        panel3.add(ch2,gc2);
        gc2.gridx=0; gc2.gridy=2; gc2.gridwidth=1;
        panel3.add(lbarr[6],gc2);
        gc2.gridx=1; gc2.gridy=2; gc2.gridwidth=GridBagConstraints.REMAINDER;
        panel3.add(ch3,gc2);

        panel2.setBorder(BorderFactory.createTitledBorder("Enter your Personal Information"));
        panel3.setBorder(BorderFactory.createTitledBorder("Choose the Subjects to Register"));


        panel0.add(box1);
        pane.add(panel0);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocation(300,100);
        this.pack();

        reg.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                String str3,str4,str5; int idnum=0;
                try{

                    DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mybase",suser,spass);
                    PreparedStatement ps=con.prepareStatement("insert into lecturer values(?,?,?,?,?,?)");
                    PreparedStatement ps2=con.prepareStatement("insert into subjects values(?,?,?,?)");

                    str3=tx1.getText();
                    str4=tx2.getText();
                    idnum=Integer.valueOf(tx3.getText());
                    str5=tx4.getText();

                    ps.setString(1, str1);
                    ps.setString(2, str2);
                    ps.setString(3, str3);
                    ps.setString(4, str4);
                    ps.setInt(5, idnum);
                    ps.setString(6, str5);

                    ps2.setString(1, str4);
                    ps2.setInt(2, ch1.isSelected() ? 1 : 0);
                    ps2.setInt(3, ch2.isSelected() ? 1 : 0);
                    ps2.setInt(4, ch3.isSelected() ? 1 : 0);

                    ps.executeUpdate();
                    ps2.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Done");
                    reg.setEnabled(false);
                }catch(Exception ex){System.out.println(ex);}


            }
        });


    }
}
