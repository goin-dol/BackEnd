package chatRoom;

public class chatRoomDTO {
    private int chatRoom_id;
    private String chatRoomName;
    private int u_id;
    private String userId;
    private String nickName;
    private boolean isNoticeRead;

    public int getChatRoom_id() {
        return chatRoom_id;
    }
    public void setChatRoom_id(int chatRoom_id) {
        this.chatRoom_id = chatRoom_id;
    }
    public String getChatRoomName() {
        return chatRoomName;
    }
    public void setChatRoomName(String chatRoomName) {
        this.chatRoomName = chatRoomName;
    }
    public int getU_id() {
        return u_id;
    }
    public void setU_id(int u_id) {
        this.u_id = u_id;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public boolean isNoticeRead() {
        return isNoticeRead;
    }
    public void setNoticeRead(boolean isNoticeRead) {
        this.isNoticeRead = isNoticeRead;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
