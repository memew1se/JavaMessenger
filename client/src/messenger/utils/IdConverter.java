package messenger.utils;

import kong.unirest.json.JSONArray;

public class IdConverter {

    public static long convert(JSONArray jsonUser) {
        String link = jsonUser.getJSONObject(0).getJSONObject("_links").getJSONObject("self")
                .getString("href");

        Character id_char = link.charAt(link.length() - 1);
        long id = Character.getNumericValue(id_char);

        return id;
    }

}
