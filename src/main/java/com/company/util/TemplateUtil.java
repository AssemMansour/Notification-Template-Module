package com.company.util;

import com.api.model.Notification;
import com.api.model.Template;
import com.company.Type;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TemplateUtil {

    private TemplateUtil() {
    }

    private static final String SERVICE_URL = "http://localhost:8080/templates/";


    public static ArrayList<Template> getAllTemplates() {
        String jsonResponse = DataUtil.getJsonResponse(SERVICE_URL);
        return getTemplateResults(jsonResponse);
    }

    public static Long createTemplate(Template template) {

        return DataUtil.handleWriteRequest(template.toString(), SERVICE_URL, "POST");
    }

    public static Long updateTemplate(Template template) {
        Long id = template.getId();
        return DataUtil.handleWriteRequest(template.toString(), SERVICE_URL + id, "PUT");
    }

    public static Long deleteTemplate(Template template) {
        Long id = template.getId();
        return DataUtil.handleWriteRequest(template.toString(), SERVICE_URL + id, "DELETE");
    }

    public static Template getTemplateById(Long id) {
        String jsonResponse = DataUtil.getJsonResponse(SERVICE_URL + id);
        return getTemplateResults(jsonResponse).get(0);
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

    public static void printTemplateInfo(Template template) {
        System.out.println("content:"+ template.getContent());
        System.out.println("number of unknowns:" +template.getNumberOfUnknowns());
        Type t1=processType(template.getTemplateType());
        System.out.println("type:"+t1.name());
        if (template.getLanguage())
            System.out.println("language: English");
        else
            System.out.println("language: Arabic");
    }

    private static ArrayList<Template> getTemplateResults(String jsonResponse) {

        ArrayList<Template> results = new ArrayList<>();

        if (jsonResponse.isEmpty())
            return null;

        try {
            JSONArray jsonArray = new JSONArray(jsonResponse);
            int length = jsonArray.length();

            for (int i = 0; i < length; i++) {
                JSONObject result = jsonArray.getJSONObject(i);

                Long id = result.getLong("id");
                String content = result.getString("content");
                int numberOfUnknowns = result.getInt("numberOfUnknowns");
                int templateType = result.getInt("templateType");
                boolean language = result.getBoolean("language");

                Template template = new Template(content, numberOfUnknowns, templateType, language);
                template.setId(id);

                results.add(template);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return results;
    }

}
