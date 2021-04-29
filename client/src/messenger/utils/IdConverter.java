package messenger.utils;

import kong.unirest.json.JSONObject;

public class IdConverter {

    public static long convertIdToLong(JSONObject jsonObj) {
        String link = jsonObj.getJSONObject("_links").getJSONObject("self")
                .getString("href");

        Character id_char = link.charAt(link.length() - 1);
        long id = Character.getNumericValue(id_char);

        return id;
    }

    public static String convertChatIdToHref(long id) {
        return "http://127.0.0.1:8080/messageEntities/" + id;
    }

}
