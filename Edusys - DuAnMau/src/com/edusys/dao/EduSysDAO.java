package com.edusys.dao;

import java.util.List;

/**
 *
 * @author nghipc
 * @param <Entity> Entity in Database
 * @param <Key> Primary key of the entity
 */
public abstract class EduSysDAO<Entity, Key> {

    public static final EduSysDAO CHUYEN_DE_DAO = new ChuyenDeDAO();
    public static final EduSysDAO KHOA_HOC_DAO = new KhoaHocDAO();
    public static final EduSysDAO NGUOI_HOC_DAO = new NguoiHocDAO();
    public static final EduSysDAO HOC_VIEN_DAO = new HocVienDAO();
    public static final EduSysDAO NHAN_VIEN_DAO = new NhanVienDAO();
    public static final ThongKeDAO THONG_KE_DAO = new ThongKeDAO();
    
    abstract public int insert(Entity e); //thêm entity vào CSDL

    abstract public int update(Entity e); //cập nhật entity trong CSDL

    abstract public int delete(Key id); //xoá entity khỏi CSDL

    abstract public Entity selectById(Key id); //tìm entity theo id

    abstract public List<Entity> selectAll(); //tìm tất cả entity

    abstract protected List<Entity> selectBySQL(String sql, Object... args); //tìm entity theo SQL
    
}
