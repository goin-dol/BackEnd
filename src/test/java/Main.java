import friend.friendDAO;
import friend.friendDTO;
import user.userDAO;

import java.util.List;
import java.sql.SQLIntegrityConstraintViolationException;

public class Main {

    public static void main(String[] args) {

        userDAO userdao = userDAO.getInstance();

        friendDAO frienddao = friendDAO.getInstance();

//        userdao.login(userId, userPassword);

//        List<friendDTO> friends = userdao.getFriendList("zxz4641");

//        frienddao.InviteFriend(1,"zxz4641","snowooden");

        int cnt = userdao.getFriendCnt("zxz4641");
        System.out.println(cnt);
        String userId = "zxz4641";
        String userPassword = "3249";
        String nickName="";


        //회원가입
        //userdao.signUp(userId,userPassword,nickName);

        //로그인
        //userdao.login(userId, userPassword, "127.0.0.1");

        //로그아웃(프로그램 닫을시 호출)
        userdao.logout(userId);
    }
}
