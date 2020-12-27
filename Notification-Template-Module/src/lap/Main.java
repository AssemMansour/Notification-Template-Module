package lap;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
	// write your code here
        Templatedataaccesslayer t1=new Templatedataaccesslayer();

        if (t1.connecttodb()==null)
            System.out.println("not connected");
        else
            System.out.println("connected");
    }
}
