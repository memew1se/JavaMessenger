package messenger.requests;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import messenger.exceptions.NonUniqueNickname;

public class ClientRequests {

    private static final Unirest unirest = new Unirest();


    private String nickname;
    private String password;
    private long id;

    public ClientRequests(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;

        unirest.config().defaultBaseUrl(System.getenv("BASE_URL"));
    }

    public JSONArray signIn() throws UnirestException {
        HttpResponse<JsonNode> response = unirest.get("/userEntities/search/nickpass")
                .queryString("nickname", this.nickname)
                .queryString("password", this.password)
                .asJson();

        JSONArray user = response.getBody().getObject().getJSONObject("_embedded").getJSONArray("userEntities");
        return user;
    }

    public JSONArray signUp() throws UnirestException, NonUniqueNickname {
        JSONObject body = new JSONObject();

        body.put("nickname", this.nickname);
        body.put("password", this.password);

        HttpResponse<JsonNode> response = unirest.post("/userEntities")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .asJson();

        if (response.getStatus() == 409) {
            throw new NonUniqueNickname(String.format("Nickname %s is already used", nickname));
        }

        return signIn();
    }
}
