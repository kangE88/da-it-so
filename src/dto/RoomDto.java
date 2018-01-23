package dto;

import java.io.Serializable;

public class RoomDto implements Serializable {
    private int seq;
    private String user_id;
    private String target_id;

    public RoomDto() {
    }

    public RoomDto(String user_id, String target_id) {
        this.user_id = user_id;
        this.target_id = target_id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTarget_id() {
        return target_id;
    }

    public void setTarget_id(String target_id) {
        this.target_id = target_id;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "seq=" + seq +
                ", user_id='" + user_id + '\'' +
                ", target_id='" + target_id + '\'' +
                '}';
    }
}
