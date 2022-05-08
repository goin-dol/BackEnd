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
        List<Integer> u_idList = new ArrayList<Integer>();
        List<String> userIdList = new ArrayList<String>();
        List<String> userNick = new ArrayList<String>();
        u_idList.add(2);
        u_idList.add(3);
        u_idList.add(14);

        userIdList.add("TEST");
        userIdList.add("ghktjq");
        userIdList.add("snowooden");

        userNick.add("김동준");
        userNick.add("sup");
        userNick.add("JungWoo");

        inviteRoom(chatRoomName, u_idList, userIdList, userNick);

    }
    //최초 방 만들기 자기 자신 초대
    public static void makeRoom(String chatRoomName, int u_id, String userId, String nickName) {
        chatRoomDAO.inviteFriend(chatRoomName, u_id, userId, nickName);
    }
    
    //다른 인원 초대
    public static void inviteRoom(String chatRoomName, List<Integer> u_idList, List<String> userIdList, List<String> userNick) {
        for(int i = 0; i < u_idList.size(); i++) {
            chatRoomDAO.inviteFriend(chatRoomName, u_idList.get(i), userIdList.get(i), userNick.get(i));
        }
    }
}
