package lap;

import java.sql.Connection;
import java.sql.SQLException;

public interface TemplateDao {
    public Connection connecttodb () throws SQLException;
    public void createtemplate (Template t1) throws SQLException;
    public void readtemplate (int id) throws SQLException;
    public void updatetemplate (int id) throws SQLException;
    public void deletetemplate () throws SQLException;
    public  void readall () throws SQLException;
}
