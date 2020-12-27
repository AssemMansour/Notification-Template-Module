package lap;

enum language
{
    ARABIC,
    ENGLISH
}
enum Type
{
    PASSWORD,
    VERIFICATION,
    LATE,
    ORDERS,
    ANNOUNCEMENT,
    PROMOTION
}
public class Template {
    String content;
    int numberofunknowns;
    language Language;
    Type type;

    public Template(String content, int numberofunknowns, language language, Type type, String subject) {
        this.content = content;
        this.numberofunknowns = numberofunknowns;
        Language = language;
        this.type = type;
        this.subject = subject;
    }

    String subject;

    public String getSubject() {
        return subject;
    }


    public String getContent() {
        return content;
    }

    public int getNumberofunknowns() {
        return numberofunknowns;
    }

    public language getLanguage() {
        return Language;
    }

    public Type getType() {
        return type;
    }
    public static Type processType(Integer choice) {
        switch (choice) {
            case (1):
                return Type.values()[0];
            case (2):
                return Type.values()[1];
            case (3):
                return Type.values()[2];
            case (4):
                return Type.values()[3];
            case (5):
                return Type.values()[4];
            case (6):
                return Type.values()[5];
        }
        return Type.values()[0];
    }

}
