package messenger.requests;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import messenger.entities.Chat;
import messenger.exceptions.NonUniqueNickname;
import messenger.utils.IdConverter;

import java.util.ArrayList;
import java.util.List;

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

    public void setId(long id) {
        this.id = id;
    }

    public JSONObject signIn() throws UnirestException {
        HttpResponse<JsonNode> response = unirest.get("/userEntities/search/nickpass")
                .queryString("nickname", nickname)
                .queryString("password", password)
                .asJson();

        JSONObject user = response.getBody().getObject().getJSONObject("_embedded").getJSONArray("userEntities").getJSONObject(0);
        return user;
    }

    public JSONObject signUp() throws UnirestException, NonUniqueNickname {
        JSONObject body = new JSONObject();

        body.put("nickname", nickname);
        body.put("password", password);

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

    public ObservableList<Chat> getChats() {
        HttpResponse<JsonNode> response = unirest.get("/chatEntities/search/userin")
                .queryString("id", id)
                .asJson();

        JSONArray chats = response.getBody().getObject().getJSONObject("_embedded").getJSONArray("chatEntities");

        ObservableList<Chat> chatList = FXCollections.observableArrayList();

        for(int i = 0; i < chats.length(); i++) {
            JSONObject chat = chats.getJSONObject(i);

            long id = IdConverter.convert(chat);
            String name = chat.getString("name");
            List<Long> users_id = chat.getJSONArray("users").toList();

            Chat chatEntity = new Chat(id, name, users_id);
            chatList.add(chatEntity);
        }
        
        return chatList;
    }
}
