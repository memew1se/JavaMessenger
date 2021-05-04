package messenger.requests;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

import messenger.entities.Chat;
import messenger.entities.Message;
import messenger.exceptions.ChatAlreadyExistsException;
import messenger.exceptions.NoSuchUserException;
import messenger.exceptions.NonUniqueNicknameException;
import messenger.utils.IdConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * API and session for the user
 */
public class ClientRequests {

    private static final Unirest unirest = new Unirest();


    private String nickname;
    private String password;
    private long id;

    /**
     * ClientRequests constructor
     *
     * @param nickname
     * @param password
     */
    public ClientRequests(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;

        // Setting server url for the session
        unirest.config().defaultBaseUrl(System.getenv("BASE_URL"));
    }

    /**
     * User authorization
     *
     * @return user info in JSON
     * @throws UnirestException - if there are connection problems
     * @throws JSONException - if user not exist
     */
    public JSONObject signIn() throws UnirestException, JSONException {
        HttpResponse<JsonNode> response = unirest.get("/userEntities/search/nickpass")
                .queryString("nickname", nickname)
                .queryString("password", password)
                .asJson();

        JSONObject user = response.getBody().getObject()
                .getJSONObject("_embedded").getJSONArray("userEntities").getJSONObject(0);
        return user;
    }

    /**
     * User registration
     *
     * @return user info in JSON
     * @throws UnirestException - if there are connection problems
     * @throws NonUniqueNicknameException - if user nickname already taken
     */
    public JSONObject signUp() throws UnirestException, NonUniqueNicknameException {
        JSONObject body = new JSONObject();

        body.put("nickname", nickname);
        body.put("password", password);

        HttpResponse<JsonNode> response = unirest.post("/userEntities")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .asJson();

        if (response.getStatus() == 409) {
            throw new NonUniqueNicknameException(String.format("Nickname %s is already used", nickname));
        }

        return signIn();
    }

    /**
     * Gets user chats from server
     *
     * @return list of chats
     */
    public ObservableList<Chat> getChats() {
        HttpResponse<JsonNode> response = unirest.get("/chatEntities/search/userin")
                .queryString("id", id)
                .asJson();

        JSONArray chats = response.getBody().getObject()
                .getJSONObject("_embedded").getJSONArray("chatEntities");

        ObservableList<Chat> chatList = FXCollections.observableArrayList();

        for(int i = 0; i < chats.length(); i++) {
            JSONObject chat = chats.getJSONObject(i);

            long id = IdConverter.convertHrefIdToLong(chat);
            String name = chat.getString("name");
            List<Long> users_id = chat.getJSONArray("users").toList();

            Chat chatEntity = new Chat(id, name, users_id);
            chatList.add(chatEntity);
        }

        return chatList;
    }

    /**
     * Gets users messages from chat
     *
     * @param chatId the chat id
     * @return list of messages
     */
    public ObservableList<Message> getMessages(long chatId) {
        HttpResponse<JsonNode> response = unirest.get("/messageEntities/search/chatid")
                .queryString("id", IdConverter.convertChatIdToHref(chatId))
                .asJson();

        JSONArray messages = response.getBody().getObject()
                .getJSONObject("_embedded").getJSONArray("messageEntities");

        ObservableList<Message> messageList = FXCollections.observableArrayList();

        for(int i = 0; i < messages.length(); i++) {
            JSONObject message = messages.getJSONObject(i);

            long id = IdConverter.convertHrefIdToLong(message);
            long fromUserId = getIdFromUrl(message.getJSONObject("_links").getJSONObject("fromId").getString("href"));
            String fromNickname = message.getString("fromName");
            String content = message.getString("content");

            messageList.add(new Message(id, fromUserId, fromNickname, chatId, content));
        }
        return messageList;
    }

    /**
     * Gets id from link
     *
     * @param url the url of object
     * @return the object id
     */
    public long getIdFromUrl(String url) {
        HttpResponse<JsonNode> response = Unirest.get(url).asJson();

        JSONObject objectWithId = response.getBody().getObject();

        return IdConverter.convertHrefIdToLong(objectWithId);
    }

    /**
     * Sends user message
     *
     * @param chatId the chat id
     * @param message the text of message
     */
    public void sendMessage(long chatId, String message) {
        JSONObject body = new JSONObject();

        body.put("chat", IdConverter.convertChatIdToHref(chatId));
        body.put("fromName", nickname);
        body.put("fromId", IdConverter.convertUserIdToHref(id));
        body.put("content", message);

        HttpResponse<JsonNode> response = unirest.post("/messageEntities")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .asJson();
    }

    /**
     * Creates new chat
     *
     * @param nick the user nickname
     * @param chatName the chat name
     * @throws NoSuchUserException - if there is no user
     * @throws ChatAlreadyExistsException - if chat already exists
     */
    public void createChat(String nick, String chatName) throws NoSuchUserException, ChatAlreadyExistsException {
        HttpResponse<JsonNode> userResponse = unirest.get("/userEntities/search/nickname")
                .queryString("nickname", nick)
                .asJson();

        JSONObject jsonUser = userResponse.getBody().getObject()
                .getJSONObject("_embedded").getJSONArray("userEntities").getJSONObject(0);

        if (jsonUser.isEmpty()) {
            throw new NoSuchUserException("User not found");
        }

        long userId = IdConverter.convertHrefIdToLong(jsonUser);
        String userName = jsonUser.getString("nickname");

        HttpResponse<JsonNode> chatsResponse = unirest.get("/chatEntities/search/userin")
                .queryString("id", userId)
                .asJson();

        JSONArray chats = chatsResponse.getBody().getObject()
                .getJSONObject("_embedded").getJSONArray("chatEntities");

        for(int i = 0; i < chats.length(); i++) {
            JSONObject chat = chats.getJSONObject(i);

            List<Integer> usersInChatInteger = chat.getJSONArray("users").toList();

            List<Long> usersInChat = new ArrayList<Long>(usersInChatInteger.size());
            for (int j = 0; j < usersInChatInteger.size(); j ++) {
                usersInChat.add(usersInChatInteger.get(j).longValue());
            }

            if(usersInChat.contains(id) && usersInChat.contains(userId)) {
                throw new ChatAlreadyExistsException("You already have chat with this person!");
            }
        }

        JSONObject body = new JSONObject();

        List<Long> usersList = new ArrayList<Long>();
        usersList.add(id);
        usersList.add(userId);

        body.put("name", chatName);
        body.put("users", usersList);

        HttpResponse<JsonNode> createChatResponse = unirest.post("/chatEntities/")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body(body)
                .asJson();

    }

    /**
     * Sets user id
     *
     * @param id the user id
     */
    public void setId(long id) {
        this.id = id;
    }
}
