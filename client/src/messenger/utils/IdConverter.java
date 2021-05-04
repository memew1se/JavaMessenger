package messenger.utils;

import kong.unirest.json.JSONObject;

/**
 * Converter from id and to id
 */
public class IdConverter {

    /**
     * Converts href to id
     *
     * @param jsonObj the object with id
     * @return the id
     */
    public static long convertHrefIdToLong(JSONObject jsonObj) {
        String link = jsonObj.getJSONObject("_links").getJSONObject("self")
                .getString("href");

        String[] linkArray = link.split("/");
        long id = Long.parseLong(linkArray[linkArray.length - 1]);

        return id;
    }

    /**
     * Converts chat id to href
     *
     * @param id the chat id
     * @return the chat href
     */
    public static String convertChatIdToHref(long id) {
        return System.getenv("BASE_URL") + "/messageEntities/" + id;
    }

    /**
     * Converts user id to href
     *
     * @param id the user id
     * @return the user href
     */
    public static String convertUserIdToHref(long id) {
        return System.getenv("BASE_URL") + "/userEntities/" + id;
    }

}
