# Yêu cầu

### Tạo ứng dụng Java daily import thông tin trừ tiền thành công của user từ file CSV vào DB.
- Thời gian import : 5h AM
- User đại diện bởi số điện thoại
- Nếu user đã đăng ký trong hệ thống rồi, thì update ngày giao dịch gần nhất và có trạng thái (status =1)
- Nếu user chưa đăng ký trong hệ thống thì tạo mới và update ngày giao dịch gần nhất ( status =1).
- Mỗi file CSV tối đa 2000 lines.
- Số lượng file CSV mỗi lần imports khoảng 30 files
- Một user chỉ trừ tiền một lần trong ngày (nếu gọi nhiều lần sẽ lấy thời gian update gần nhất)
- Phải lưu lại lịch sử tất cả các bản ghi CSV vào DB

### Daily update status của user từ 1 về 0 nếu ngày charge thành công < ngày hiên tại 30 ngày.
- (Ngày hiên tại - Ngày charge thành công cuối cùng) > 30
- Thời gian update status : 4h AM
  
# Môi trường
- Server: CentOS 7
- Java : 8.x
- DB: MySQL >= 5.5
  
# Others
- Import file history : 
       Số đt,line,update-time 
- Import xong thì move file kết quả sang folder khác 
- Nếu có lỗi thì dừng tại dòng bị lỗi ( có log exception) và chuyển sang file tiếp theo. Move file bị lỗi sang folder error.
- Deploy trên Centos7


# Sample CSV content

```
84123456789,1000,2018-07-25 10-01-01
84123456790,1000,2018-07-25 10-01-01
84123456791,1000,2018-07-25 10-01-01
```
# Develop flow
- Create  SRS
- Design 
++ Design DB
++ Class Design ( Layers)
++ Process flow
- Coding 
+ Coding & UT
+ Code review (peer)
- Test
+ Create TC
+ Test & Fix ( peer)
