import chatRoom.chatRoomDAO;
import user.userDTO;

import java.util.ArrayList;
import java.util.List;

public class chatRoomTest {
    static chatRoomDAO chatRoomDAO = chatRoom.chatRoomDAO.getInstance();
    public static void main(String[] args) {


        String chatRoomName = "First ChatRoom";
        int u_id = 1;
        String userId = "zxz4641";
        String nickName = "김대현";
        List<String> userIdList = new ArrayList<String>();
        List<String> userNick = new ArrayList<String>();

        userIdList.add("TEST");
        userIdList.add("ghktjq");
        userIdList.add("snowooden");

        userNick.add("김동준");
        userNick.add("sup");
        userNick.add("JungWoo");

        //makeRoom(chatRoomName, userId, nickName);
        inviteRoom(chatRoomName, userIdList, userNick);

    }
    //최초 방 만들기 자기 자신 초대
    public static void makeRoom(String chatRoomName, String userId, String nickName) {
        chatRoomDAO.inviteFriend(chatRoomName, userId, nickName);
    }
    
    //다른 인원 초대
    public static void inviteRoom(String chatRoomName, List<String> userIdList, List<String> userNick) {
        for(int i = 0; i < userIdList.size(); i++) {
            chatRoomDAO.inviteFriend(chatRoomName, userIdList.get(i), userNick.get(i));
        }
    }
}
