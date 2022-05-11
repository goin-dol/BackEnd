package notice;

import DB.DBDAO;

import java.sql.*;

public class NoticeDAO {

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    private static NoticeDAO instance = null;

    private static DBDAO DB = DBDAO.getInstance();

    //싱글톤 패턴(객체를 단 1개만 생성)
    public static NoticeDAO getInstance() {
        if(instance == null)
            instance = new NoticeDAO();
        return instance;
    }

    //공지 생성
    public int InsertNotice(String userId,int chatRoom_id,String title,String content){

        // 예외흐름 2 사용자가 공지를 다 읽었을 경우
        if(AllReadNotice(title)==1) {
            // 예외흐름 1
            int status = -1;
            if (content == "") {
                // 예외흐름 1 공지 내용을 입력하지 않았을 때 1을 호출
                status = 1;
                return status;
            }
            String query =
                    "INSERT INTO `DB_ppick`.`notice`" +
                            "(" +
                            "`userId`," +
                            "`chatRoom_id`," +
                            "`title`," +
                            "`content`" +
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
                pstmt.setString(1, userId);
                pstmt.setInt(2, chatRoom_id);
                pstmt.setString(3, title);
                pstmt.setString(4, content);

                pstmt.executeUpdate();
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (rs != null) try {
                    rs.close();
                } catch (SQLException ex) {
                }
                if (pstmt != null) try {
                    pstmt.close();
                } catch (SQLException ex) {
                }
                if (conn != null) try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
            //공지가 잘 생셩 됐을 때 -1 호출
            return status;
        }
        // 예외 2에서 사용자가 공지를 다 안읽었을 떄 처리
        else{
            //시스템이 오류 메시지 생성 ("아직 공지를 확인하지 않은 인원이 있습니다. 공지 생성을 진행하시겠습니까?")
            return 2;
        }
    }

    //공지 생성에서 예외 3의 경우 -
    public void AllNotReadNotice(){
    }

    //공지 확인 (읽음 처리)
    public void ReadNotice(String nickname,String chatRoom_id){

        //예외 흐름 : 공지가 없을 때 -> NullPointException
        String query =
                "SELECT * FROM `DB_ppick`.`notice` WHERE `chatRoom_id`=? ";

        String update =
                "UPDATE `DB_ppick`.`chatRoomUserList`" +
                        "SET" +
                        "`isNoticeRead` = true," +
                        "WHERE `nickName` = ?";
        try {
            conn = DB.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,chatRoom_id);
            pstmt.executeQuery();

            pstmt = conn.prepareStatement(update);
            pstmt.setString(1,nickname);
            pstmt.executeUpdate();
        } catch(Exception e) {
            //시스템이 오류 메시지 출력
            e.printStackTrace();
        } finally {
            if(rs != null) try {rs.close();}catch(SQLException ex ) {}
            if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
            if(conn != null) try {conn.close();}catch(SQLException ex) {}
        }
    }

    //공지 전부 읽었는지 확인
    public int AllReadNotice(String chatRoom_id){
        int cnt = 0;
        int cnt1=0;

        // 해당 채팅방에서 공지를 읽은 인원
        String query =
                "SELECT count(*) FROM DB_ppick.chatRoomUserList" +
                        " where isNoticeRead = ? and chatRoom_id = ? ";

        // 해당 채팅방의 인원
        String query1=
                "SELECT count(*) FROM DB_ppick.chatRoomList where chatRoom_id = ?";

        try {
            conn = DB.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setBoolean(1, true);
            pstmt.setString(2,chatRoom_id);
            rs = pstmt.executeQuery();
            if(rs.next())
                cnt = rs.getInt(1);

            pstmt=conn.prepareStatement(query1);
            pstmt.setString(1,chatRoom_id);
            rs = pstmt.executeQuery();
            if(rs.next())
                cnt1 = rs.getInt(1);

            // 공지 읽은 유저의 수와 해당 채팅방의 유저의 수를 비교
            if(cnt==cnt1){
                return 1;
            }else{
                return 0;
            }

        } catch(Exception e) {
            e.printStackTrace();
        } finally{
            if(conn != null) try{conn.close();}catch(SQLException ex){}
            if(pstmt != null) try{pstmt.close();}catch(SQLException ex){}
            if(rs != null) try{rs.close();}catch(SQLException ex){}
        }
        return cnt;
    }
}
