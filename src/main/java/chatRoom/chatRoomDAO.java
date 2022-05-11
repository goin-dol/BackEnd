package chatRoom;

import DB.DBDAO;
import user.userDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class chatRoomDAO {
    private static chatRoomDAO instance = null;

    private static DBDAO DB = DBDAO.getInstance();

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public static chatRoomDAO getInstance() {
        if(instance == null)
            instance = new chatRoomDAO();
        return instance;
    }

    //최초 방 생성시 자기 자신 아이디 정보 입력
    //여러명 초대시 해당 매개변수 리스트에 담아서 반복 실행
    public void inviteFriend(String chatRoomName, String userId, String nickName) {
        String query =
                "INSERT INTO `DB_ppick`.`chatRoom`" +
                        "(" +
                        "`chatRoomName`," +
                        "`userId`," +
                        "`nickName`," +
                        "`isNoticeRead`" +
                        ")" +
                        "VALUES" +
                        "(" +
                        "?," +
                        "?," +
                        "?," +
                        "?" +
                        ")";
        try {
            conn = DB.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, chatRoomName);
            pstmt.setString(2, userId);
            pstmt.setString(3, nickName);
            pstmt.setBoolean(4, false);

            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) try {rs.close();}catch(SQLException ex ) {}
            if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
            if(conn != null) try {conn.close();}catch(SQLException ex) {}
        }
    }

    public List<chatRoomDTO> getChatRoomList(String userId) {
        List<chatRoomDTO> chatRoomList = null;
        String query =
                "SELECT " +
                        "`chatRoom`.`chatRoom_id`," +
                        "`chatRoom`.`chatRoomName`," +
                        "`chatRoom`.`userId`," +
                        "`chatRoom`.`nickName`," +
                        "`chatRoom`.`isNoticeRead`" +
                        "FROM `DB_ppick`.`chatRoom` WHERE userId = ?";
        try {
            conn = DB.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                chatRoomList = new ArrayList<chatRoomDTO>();
                do {
                    chatRoomDTO chatRoom = new chatRoomDTO();
                    chatRoom.setChatRoom_id(rs.getInt("chatRoom_id"));
                    chatRoom.setChatRoomName(rs.getString("chatRoomName"));
                    chatRoom.setUserId(rs.getString(rs.getString("userId")));
                    chatRoom.setNickName(rs.getString("nickName"));
                    chatRoom.setNoticeRead(rs.getBoolean("isNoticeRead"));

                    chatRoomList.add(chatRoom);
                }while(rs.next());
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) try {rs.close();}catch(SQLException ex ) {}
            if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
            if(conn != null) try {conn.close();}catch(SQLException ex) {}
        }
        return chatRoomList;
    }

    //EnrtyChatRoom(int chatRoom_id) ChatLog 엔티티 불러와서 출력


    //현재 채팅방안에 유저목록
    public List<userDTO> getUserList(int chatRoom_id) {
        List<userDTO> userList = null;
        String query =
                "SELECT " +
                        "`chatRoom`.`userId`," +
                        "`chatRoom`.`nickName`," +
                        "FROM `DB_ppick`.`chatRoom` WHERE chatRoom_id = ?";
        try {
            conn = DB.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, chatRoom_id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                userList = new ArrayList<userDTO>();
                do {
                    userDTO user = new userDTO();
                    user.setUserId(rs.getString("userId"));
                    user.setNickName(rs.getString("nickName"));
                    userList.add(user);
                }while(rs.next());
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) try {rs.close();}catch(SQLException ex ) {}
            if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
            if(conn != null) try {conn.close();}catch(SQLException ex) {}
        }

        return userList;
    }

}
