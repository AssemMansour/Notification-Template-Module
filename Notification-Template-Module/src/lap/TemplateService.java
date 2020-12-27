package lap;

import java.sql.Connection;
import java.sql.SQLException;

public class TemplateService {
    private final TemplateDao templateDao;

    public TemplateService(TemplateDao templateDao) {
        this.templateDao = templateDao;
    }
public void createtemplate (Template t1) throws SQLException {
    templateDao.createtemplate(t1);
}
public void updatetemplate (int id) throws SQLException {
    templateDao.updatetemplate(id);
}
public void deletetemplate () throws SQLException {
    templateDao.deletetemplate();
}
public void readall () throws SQLException
{
    templateDao.readall();
}
public void read (int id) throws SQLException {
    templateDao.readtemplate(id);
}
}
