package messenger.utils;

import kong.unirest.json.JSONObject;

public class IdConverter {

    public static long convert(JSONObject jsonObj) {
        String link = jsonObj.getJSONObject("_links").getJSONObject("self")
                .getString("href");

        Character id_char = link.charAt(link.length() - 1);
        long id = Character.getNumericValue(id_char);

        return id;
    }

}
