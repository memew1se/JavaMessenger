package messenger.utils;

import kong.unirest.json.JSONArray;
import messenger.entities.User;

public class UserConverter {

    public static User convert(JSONArray jsonUser) {
        String nickname = jsonUser.getJSONObject(0).getString("nickname").toString();
        String password = jsonUser.getJSONObject(0).getString("password").toString();

        String link = jsonUser.getJSONObject(0).getJSONObject("_links").getJSONObject("self")
                .getString("href");

        Character id_char = link.charAt(link.length() - 1);
        long id = Character.getNumericValue(id_char);

        return new User(nickname, password, id);
    }

}
