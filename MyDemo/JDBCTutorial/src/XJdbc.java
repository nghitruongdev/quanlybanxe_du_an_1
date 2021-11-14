
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author nghipc
 */
public class XJdbc {

    private static Connection con;
    private static final String URL = "jdbc:sqlserver://localhost;databaseName=tutorial;username=sa;password=songlong";

    private static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        con = DriverManager.getConnection(URL);
        PreparedStatement pstmt;
        if (sql.trim().startsWith("{")) {
            pstmt = con.prepareCall(sql);
        } else {
            pstmt = con.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    public static ResultSet query(String sql, Object... args) throws SQLException {
        PreparedStatement pstmt = getStmt(sql, args);
        return pstmt.executeQuery();
    }

    public static Object value(String sql, Object... args) {
        Object value = null;
        try (ResultSet rs = query(sql, args)) {
            if (rs.next()) {
                value = rs.getObject(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeCon();
        }
        return value;
    }

    public static int update(String sql, Object... args) {
        int count = 0;
        try (PreparedStatement pstmt = getStmt(sql, args)) {
            count = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeCon();
        }
        return count;
    }

    public static void closeCon() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
