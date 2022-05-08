import chatRoomList.chatRoomListDAO;
import chatRoomList.chatRoomListDTO;

import java.util.ArrayList;
import java.util.List;


public class chatRoomTest {
    static chatRoomListDAO chatRoomListDAO = chatRoomList.chatRoomListDAO.getInstance();
    public static void main(String[] args) {


        String chatRoomName = "Second ChatRoom";
        int u_id = 1;
        String userId = "zxz4641";
        String nickName = "김대현";
        List<String> userNick = new ArrayList<String>();


        userNick.add("김태현");
        userNick.add("sup");

        //makeRoom(chatRoomName, nickName);
        //inviteRoom(2, chatRoomName, userNick);
        //printChatRoomList(userId);
        getRoomName("김태현");
    }
    //최초 방 만들기 자기 자신 초대
    public static void makeRoom(String chatRoomName, String nickName) {
        chatRoomListDAO.createChatRoom(chatRoomName, nickName);
        int chatRoom_id = chatRoomListDAO.getChatRoomId(chatRoomName, nickName);
        System.out.println("id = " + chatRoom_id);
        chatRoomListDAO.inviteChatRoom(chatRoom_id, nickName);
    }
    
    //다른 인원 초대
    public static void inviteRoom(int chatRoom_id, String chatRoomName, List<String> userNick) {
        for(int i = 0; i < userNick.size(); i++) {
            if(chatRoomListDAO.inviteChatRoom(chatRoom_id, userNick.get(i)) == 2)
                System.out.println("이미 존재하는 사람");
            
        }
    }
    
    //채팅방 이름
    public static void getRoomName(String nickName) {
        List<String> name = chatRoomListDAO.getChatRoomName(nickName);
        for(int i = 0; i<name.size(); i++)
            System.out.println(name.get(i));
    }


}
