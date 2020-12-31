package com.company.util;

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

    public static void createTemplate(Template template) {

        DataUtil.handleWriteRequest(template.toString(), SERVICE_URL, "POST");
    }

    public static void updateTemplate(Template template) {
        Long id = template.getId();
        DataUtil.handleWriteRequest(template.toString(), SERVICE_URL + id, "PUT");
    }

    public static void deleteTemplate(Template template) {
        Long id = template.getId();
        DataUtil.handleWriteRequest(template.toString(), SERVICE_URL + id, "DELETE");
    }

    public static Template getTemplateById(Long id) {
        String jsonResponse = DataUtil.getJsonResponse(SERVICE_URL + id);

        if (jsonResponse.isEmpty())
            return null;

        JSONObject object = null;
        try {
            object = new JSONObject(jsonResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return getTemplateFromJSONObject(object);
    }

    public static ArrayList<Template> getAllTemplates() {
        String jsonResponse = DataUtil.getJsonResponse(SERVICE_URL);
        return getTemplateResults(jsonResponse);
    }

    public static Type processType(Integer choice) {
        switch (choice) {
            case (1):
            default:
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
    }

    public static void printTemplateInfo(Template template) {
        System.out.println("id:" + template.getId());
        System.out.println("content:" + template.getContent());
        System.out.println("number of unknowns:" + template.getNumberOfUnknowns());
        Type type = processType(template.getTemplateType());
        System.out.println("type:" + type.name());
        if (template.getLanguage())
            System.out.println("language: English");
        else
            System.out.println("language: Arabic");
    }

    private static ArrayList<Template> getTemplateResults(String jsonResponse) {

        ArrayList<Template> results = new ArrayList<>();

        if (!jsonResponse.isEmpty()) {
            try {
                JSONArray jsonArray = new JSONArray(jsonResponse);
                int length = jsonArray.length();

                for (int i = 0; i < length; i++) {
                    JSONObject result = jsonArray.getJSONObject(i);
                    Template template = getTemplateFromJSONObject(result);
                    results.add(template);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

    private static Template getTemplateFromJSONObject(JSONObject jsonObject) {
        try {

            Long id = jsonObject.getLong("id");
            String content = jsonObject.getString("content");
            int numberOfUnknowns = jsonObject.getInt("numberOfUnknowns");
            int templateType = jsonObject.getInt("templateType");
            boolean language = jsonObject.getBoolean("language");

            Template template = new Template(content, numberOfUnknowns, templateType, language);
            template.setId(id);

            return template;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}
