package lap;

import java.sql.*;
import java.util.Scanner;
public class Templatedataaccesslayer  implements TemplateDao {
    Connection connection = null;
    @Override
    public Connection connecttodb() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Notification";
        String user = "root";
        String password = "01206693913";
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    @Override
    public void createtemplate(Template t1) throws SQLException {
        Statement st=connection.createStatement();
        ResultSet r1=st.executeQuery("insert into Notification (content,numberofunknowns,typpe,languagee,subjecte) values ("+"'"+t1.getContent()+"',"+"'"+t1.getNumberOfUnknowns()+"',"+"'"+t1.getType()+"',"+"'"+t1.getLanguage()+"',"+"'"+t1.getSubject()+"')");
    }
    @Override
    public void readtemplate(int id) throws SQLException {
        Statement st=connection.createStatement();
        ResultSet r1=st.executeQuery("select * from template");
        boolean flag=false;
        while ((r1.next()))
        {

        if (r1.getInt("templateID")==id)
        {
            System.out.println("Content: "+r1.getString("content"));
            System.out.println("ID: "+r1.getString("templateID"));
            System.out.println("language: "+r1.getString("languagee"));
            System.out.println("type: "+r1.getString("typpe"));
            System.out.println("numberofunknowns: "+r1.getString("numberofunknowns"));
            System.out.println("subject: "+r1.getString("subjecte"));
            flag=true;
            break;
        }
        }
        if (!flag)
            System.out.println("This Template isn't exist");
    }

    @Override
    public void updatetemplate(int id) throws SQLException {
        Statement st=connection.createStatement();
        ResultSet r1=st.executeQuery("select * from template");
        boolean flag=false;
        while ((r1.next()))
        {
            if (r1.getInt("templateID")==id)
            {
                System.out.println("Content: "+r1.getString("content"));
                System.out.println("ID: "+r1.getString("templateID"));
                System.out.println("language: "+r1.getString("languagee"));
                System.out.println("type: "+r1.getString("typpe"));
                System.out.println("numberofunknowns: "+r1.getString("numberofunknowns"));
                System.out.println("subject: "+r1.getString("subjecte"));
                flag=true;
                break;
            }
        }
        if (!flag)
        {
            System.out.println("This Template isn't exist");
            return;
        }
    }

    @Override
    public void deletetemplate() throws SQLException {
        readall();
        Scanner sc=new Scanner(System.in);
        System.out.println("enter template ID");
        int id;
        id=sc.nextInt();
        Statement st=connection.createStatement();
        ResultSet r1=st.executeQuery("select * from template");
        boolean flag=false;
        while ((r1.next()))
        {
            if (r1.getInt("templateID")==id)
            {
                System.out.println("Content: "+r1.getString("content"));
                System.out.println("ID: "+r1.getString("templateID"));
                System.out.println("language: "+r1.getString("languagee"));
                System.out.println("type: "+r1.getString("typpe"));
                System.out.println("numberofunknowns: "+r1.getString("numberofunknowns"));
                System.out.println("subject: "+r1.getString("subjecte"));
                flag=true;
                r1=st.executeQuery("Delete from template where template.templateID="+id);
                break;
            }
        }
        if (!flag)
            System.out.println("This Template isn't exist");
    }

    @Override
    public void readall() throws SQLException {
        Statement st=connection.createStatement();
        ResultSet r1=st.executeQuery("select * from template");
        boolean flag=false;
        while ((r1.next()))
        {
                System.out.println("Content: "+r1.getString("content"));
                System.out.println("ID: "+r1.getString("templateID"));
                System.out.println("language: "+r1.getString("languagee"));
                System.out.println("type: "+r1.getString("typpe"));
                System.out.println("numberofunknowns: "+r1.getString("numberofunknowns"));
                System.out.println("subject: "+r1.getString("subjecte"));
                flag=true;
        }
        if (!flag)
            System.out.println("NO Templates");

    }
}