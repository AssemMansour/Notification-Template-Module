package lap;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class TemplateService {
    private final TemplateDao templateDao;
    private ArrayList<Notification> notificationQueue;

    public TemplateService(TemplateDao templateDao) {
        this.templateDao = templateDao;
    }
public void createTemplate(Template t1) throws SQLException {
    templateDao.createtemplate(t1);
}
public void updateTemplate(int id) throws SQLException {
    templateDao.updatetemplate(id);
}
public void deleteTemplate() throws SQLException {
    templateDao.deletetemplate();
}
public void readAll() throws SQLException
{
    templateDao.readall();
}
public void read (int id) throws SQLException {
    templateDao.readtemplate(id);
}

public void sendNotification(Notification toSend) {
    notificationQueue.add(toSend);
}

}
