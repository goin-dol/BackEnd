package friend;

import DB.DBDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class friendDAO {

    private static friendDAO instance = null;

    private static DBDAO DB = DBDAO.getInstance();

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    public static friendDAO getInstance(){
        if(instance==null)
            instance = new friendDAO();
        return instance;
    }

    public void InviteFriend(int u_id,String userId,String friendId){
        String query =
                "INSERT INTO `DB_ppick`.`friendInfo`" +
                        "(" +
                        "`u_id`," +
                        "`userId`," +
                        "`friendId`" +
                        ")" +
                        "VALUES" +
                        "(" +
                        "?," +
                        "?," +
                        "?" +
                        ")";
        try {
            conn = DB.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, u_id);
            pstmt.setString(2, userId);
            pstmt.setString(3, friendId);
            pstmt.executeUpdate();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) try {rs.close();}catch(SQLException ex ) {}
            if(pstmt != null) try {pstmt.close();}catch(SQLException ex) {}
            if(conn != null) try {conn.close();}catch(SQLException ex) {}
        }
    }
}
