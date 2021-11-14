
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nghipc
 */
public class NhanVien implements SQLData {

    private int manv;
    private String tennv;
    private String sql_type;

    public NhanVien(int manv, String tennv) {
        this.manv = manv;
        this.tennv = tennv;
    }

    
    @Override
    public String getSQLTypeName() throws SQLException {
        return sql_type;
    }

    @Override
    public void readSQL(SQLInput sqli, String type) throws SQLException {
        sql_type = type;
        manv = sqli.readInt();
        tennv = sqli.readString();
    }

    @Override
    public void writeSQL(SQLOutput sqlo) throws SQLException {
        sqlo.writeInt(manv);
        sqlo.writeString(tennv);
    }
}
