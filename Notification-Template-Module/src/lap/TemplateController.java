package lap;

import java.sql.SQLException;

public class TemplateController {
TemplateService t1;
    public TemplateController(TemplateService Service) {
        this.t1 = Service;
    }
    public void readall () throws SQLException {
        t1.readall();
    }
    public void read (int id) throws SQLException {
        t1.read(id);
    }
    public void create (String content, int numberofunknowns, language language, Type type, String subject) throws SQLException {
        Template t2=new Template(content,numberofunknowns,language,type,subject);
        t1.createtemplate(t2);
    }
    public void delete () throws SQLException {
        t1.deletetemplate();
    }
    public void update (int id) throws SQLException {
        t1.updatetemplate(id);
    }
}
