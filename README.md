# Dự án 1
Hệ thống quản lý bán xe thuộc môn Dự án 1 của nhóm `Vĩnh Nghi và Những người bạn`.
#Yêu cầu:
1. Cài đặt MSSQL (SQL Server)
2. Java JRE 8+
#Cài đặt dự án:
1. Cài đặt Database trên MSSQL với tài khoản sa, vào folder 'db':
  - Chạy file 'table.sql'
  - Chạy file 'data.sql'
  - Chạy file 'data_HD.sql'
2. Cấu hình kết nối database trong application.properties:
  - db_url=<mssql_url>
  - db_username=<mssql_username>
  - db_password=<mssql_password>
3. Tài khoản mặc định để đăng nhập ứng dụng:
  - username: nv01
  - password: 12345678
4. Để chuyển đổi giữa customer/ admin, vào file application.properties:
  - user=customer nếu là cusomer
  - user=admin nếu là admin
