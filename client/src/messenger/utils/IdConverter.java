package messenger.utils;

import kong.unirest.json.JSONObject;

public class IdConverter {

    public static long convertHrefIdToLong(JSONObject jsonObj) {
        String link = jsonObj.getJSONObject("_links").getJSONObject("self")
                .getString("href");

        String[] linkArray = link.split("/");
        long id = Long.parseLong(linkArray[linkArray.length - 1]);

        return id;
    }

    public static String convertChatIdToHref(long id) {
        return System.getenv("BASE_URL") + "/messageEntities/" + id;
    }

    public static String convertUserIdToHref(long id) {
        return System.getenv("BASE_URL") + "/userEntities/" + id;
    }

}
