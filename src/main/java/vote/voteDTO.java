package vote;

public class voteDTO {
    private int vote_id;
    private int chatRoom_id;
    private String title;
    private boolean isAnonymous;
    private boolean isOverLap;

    public int getChatRoom_id() {
        return chatRoom_id;
    }

    public void setChatRoom_id(int chatRoom_id) {
        this.chatRoom_id = chatRoom_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public boolean isOverLap() {
        return isOverLap;
    }

    public void setOverLap(boolean overLap) {
        isOverLap = overLap;
    }


    public int getVote_id() {
        return vote_id;
    }

    public void setVote_id(int vote_id) {
        this.vote_id = vote_id;
    }
}
