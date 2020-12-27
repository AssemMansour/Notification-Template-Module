package lap;

import java.sql.SQLException;

public class TemplateController {

    TemplateService t1;
    public TemplateController(TemplateService Service) {
        this.t1 = Service;
    }
    public void readAll() throws SQLException {
        t1.readAll();
    }
    public void read (int id) throws SQLException {
        t1.read(id);
    }
    public void create (String content, int numberOfUnknowns, Language language, Type type, String subject) throws SQLException {
        Template t2=new Template(content,numberOfUnknowns,language,type,subject);
        t1.createTemplate(t2);
    }
    public void delete () throws SQLException {
        t1.deleteTemplate();
    }
    public void update (int id) throws SQLException {
        t1.updateTemplate(id);
    }
    public void sendNotification(Notification toSend) {
        t1.sendNotification(toSend);
    }
}
