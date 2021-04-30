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
        return System.getenv("BASE_URL") + "/messageEntities/" + id;
    }

    public static String convertUserIdToHref(long id) {
        return System.getenv("BASE_URL") + "/userEntities/" + id;
    }

}
