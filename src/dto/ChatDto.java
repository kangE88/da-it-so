package dto;

import java.io.Serializable;

public class ChatDto implements Serializable {
    private int seq;
    private int room_id;
    private String user_id;
    private String content;

    public ChatDto() {
    }

    public ChatDto(int room_id, String user_id, String content) {
        this.room_id = room_id;
        this.user_id = user_id;
        this.content = content;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ChatDto{" +
                "seq=" + seq +
                ", room_id=" + room_id +
                ", user_id='" + user_id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
