import friend.friendDAO;
import friend.friendDTO;
import user.userDAO;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String userId = "abc";
        String userPassword = "abc";
        String nickName="CP6";

        userDAO userdao = userDAO.getInstance();

        friendDAO frienddao = friendDAO.getInstance();

//        userdao.login(userId, userPassword);

//        List<friendDTO> friends = userdao.getFriendList("zxz4641");

//        frienddao.InviteFriend(1,"zxz4641","snowooden");

        int cnt = userdao.getFriendCnt("zxz4641");
        System.out.println(cnt);

    }
}
