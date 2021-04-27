package messenger.entities;

public class User {

    private String nickname;
    private String password;
    private long id;

    public User(String nickname, String password, long id) {
        this.nickname = nickname;
        this.password = password;
        this.id = id;
    }
}
