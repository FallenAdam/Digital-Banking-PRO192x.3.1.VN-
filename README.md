
1. Chức năng và yêu cầu chi tiết

1.1. Mô tả ứng dụng

Phần mềm ngân hàng cho phép quản trị ngân hàng quản lý danh sách khách hàng, tạo tài khoản ATM cho từng khách hàng, cho phép chuyển tiền giữa các tài khoản và rút tiền, tra cứu lịch sử giao dịch.
Ứng dụng cần đảm bảo các chức năng và yêu cầu cơ bản tuy nhiên bạn có thêm các chức năng bổ sung theo ý thích.
1.2.  Thiết kế giao diện

Yêu cầu giao diện dạng console đơn giản.
Hiển thị thông tin thành khối rõ ràng và dễ nhìn.
1.3.  Yêu cầu chức năng cơ bản

Khi chương trình được khởi chạy đầu tiên sẽ hiển thị mô tả ngắn gọn về chương trình để người dùng hiểu được hệ thống này sẽ hoạt động thế nào, có chức năng gì ví dụ như hiển thị tên phần mềm viết in hoa: NGAN HANG SO
Hiển thị tên tác giả (mã số học viên) và phiên bản (version) phần mềm theo mẫu: FX123456@v4.0.0 (FX123456 là mã số học viên, 4.0.0 là phiên bản phần mềm).
Hiển thị đường phân cách giữa tiêu đề và nội dung chức năng.
Hiển thị menu cho người dùng chọn theo mẫu ví dụ.
1. Xem danh sách khách hàng

2. Nhập danh sách khách hàng

3. Thêm tài khoản ATM

4. Chuyển tiền

5. Rút tiền

6. Tra cứu lịch sử giao dịch

0. Thoát

Assigment 4 là cải tiến của assignment 3, thay vì thao tác trên 1 khách hàng cụ thể, thì tạo ra nhiều khách hàng.

Chức năng xem danh sách khách hàng sẽ hiển thị ra tất cả các khách hàng có trong ngân hàng bằng cách đọc danh sách khách hàng từ một tệp nhị phân (Ví dụ: customers.dat).
Chức năng nhập danh sách khách hàng sẽ đọc danh sách khách hàng từ tệp text (VD: customers.txt) sau đó kiểm tra các dữ liệu khách hàng trong file có hợp lệ hay không rồi thêm từng khách hàng vào danh sách khách hàng hiện có (lưu trong file customers.dat). Chức năng này giống với các chức năng import dữ liệu từ file vào hệ thống. Học viên có thể lưu dữ liệu khách hàng để import vào dưới dạng dữ liệu con người có thể đọc được ở định dạng bất kỳ (vd: txt, json, csv, …).
Chức năng thêm tài khoản ATM tương tự assignment 3 nhưng thay vì lưu tài khoản vào mảng thì sẽ lưu các tài khoản vào một tệp nhị phân (Ví dụ: accounts.dat).
Chức năng chuyển tiền, rút tiền sẽ tạo ra các giao dịch thêm tiền và trừ tiền, thay vì lưu vào mảng thì sẽ lưu tất cả các giao dịch vào một tệp nhị phân (Ví dụ: transactions.dat).
Chức năng rút tiền sẽ cập nhật số dư tài khoản và tạo ra một giao dịch rút tiền.
Chức năng chuyển tiền sẽ cập nhật số dư tài khoản và tạo giao dịch rút tiền cho tài khoản gửi và tài khoản nhận
Chức năng tra cứu lịch sử giao dịch sẽ hiển thị tất cả các giao dịch của tất cả các tài khoản của một khách hàng, từ mã số của khách hàng bạn sẽ phải đọc file và lấy ra những tài khoản và giao dịch tương ứng.
1.4. Yêu cầu chức năng lập trình nâng cao

Sử dụng multi thread cho các vòng lặp đọc ghi dữ liệu tốn thời gian.
Đối với công việc đọc ghi file (hàm AccountDAO.update()), mỗi lần kiểm tra 1 phần tử có trong mảng của file hay không ta lại phải chạy một vòng lặp và dùng câu lệnh if để kiểm tra một cách tuần tự, với những mảng có kích thước nhỏ thì không ảnh hưởng nhưng khi dữ liệu nhiều lên thì ta nên cân nhắc sử dụng multithread để thực hiện kiểm tra nhiều phần tử cùng một lúc sẽ đẩy nhanh quá trình.

(Mặc dù trong thực tế, việc kiểm tra phần tử trong vòng lặp for không tốn nhiều thời gian để sử dụng multithread nhưng trong bài tập này yêu cầu học viên vận dụng các kiến thức đã học để thực hiện).

Gợi ý có thể sử dụng:

ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);

Viết unit test cho các phương thức trả về giá trị có trong bài.
Viết unit test cho các phương thức quy định trong asm3, bổ sung thêm:

 Phương thức transfer
Phương thức đọc ghi file (bắt exception đúng)
1.5. Hướng dẫn

Chức năng thêm khách hàng

Tạo mới một thư mục ví dụ đặt ở store/customers.txt như dưới hình:

File preview

Sau khi hỏi chức năng sẽ nhập đường dẫn đến tệp:

File preview

Trường hợp khách hàng đã tồn tại thì hiển thị thông báo, khách hàng chưa tồn tại thì thêm mới vào danh sách.

File preview

Hiển thị thông báo nếu tệp không tồn tại (ở bước này cần bắt các exception một cách hợp lý để hiển thị được thông báo).

Chức năng xem danh sách khách hàng

Sau khi thêm khách hàng thành công, ta có thể kiểm tra danh sách khách hàng, màn hình hiển thị như sau:

File preview

Chức năng thêm tài khoản ATM

Giao diện thêm tài khoản thành công:

File preview

Các trường hợp thêm tài khoản thành công:

File preview

Các trường hợp tài khoản và số tiền không hợp lệ sẽ yêu cầu nhập lại:

File preview

Xem danh sách khách hàng để kiểm tra tài khoản tạo thành công hay không:

File preview

Chức năng chuyển tiền

Luồng chính của chức năng chuyển tiền:

1. Nhập mã số khách hàng (Nhập mã số ID của khách hàng, thông báo lỗi nếu mã số không đúng hoặc không tồn tại).

2. Nhập số tài khoản gửi (Nhập số tài khoản gồm 6 chữ số, thông báo lỗi nếu người dùng nhập không đúng hoặc tài khoản chưa tồn tại).

3. Nhập số tài khoản nhận (Nhập số tài khoản gồm 6 chữ số, thông báo lỗi nếu người dùng nhập không đúng, trùng với tài khoản gửi hoặc tài khoản chưa tồn tại).

4. Nhập số tiền chuyển (Đảm bảo số dư >= 50.000 nếu không sẽ báo lỗi)

5. Xác nhận chuyển tiền: Chọn Y để tiếp tục, nếu không chương trình sẽ dừng lại.

6. Hiển thị biên lai giao dịch theo mẫu bên dưới.

File preview

Kiểm tra lại chuyển tiền có thực sự thành công và lưu vào file hay không thì có thể check số dư của mỗi tài khoản và xem lịch sử giao dịch.

Ví dụ:

Tài khoản 222222: + 600,000

File preview

Tài khoản 444444: - 600,000

File preview

Chức năng rút tiền

Luồng chính của chức năng rút tiền:

1. Nhập mã số khách hàng (Nhập mã số ID của khách hàng, thông báo lỗi nếu mã số không đúng hoặc không tồn tại).

2. Nhập số tài khoản (Nhập số tài khoản gồm 6 chữ số, thông báo lỗi nếu người dùng nhập không đúng hoặc tài khoản chưa tồn tại).

3. Nhập số tiền rút (Đảm bảo số dư >= 50.000 nếu không sẽ báo lỗi).

4. Thông báo thành công và biên lai giao dịch.

File preview

Kiểm tra lịch sử giao dịch:

File preview

Chức năng tra cứu lịch sử giao dịch

File preview

Hiển thị thông tin khách hàng, thông tin tài khoản và thông tin giao dịch của từng tài khoản như hình trên.

Thông báo lỗi nếu nhập mã số khách hàng không đúng hoặc không tồn tại.

2. Tổ chức code

Dùng lại folder PRO192x_ASM_<YourID> của ASM03 để lưu trữ toàn bộ kết quả làm bài của bạn (Ví dụ: PRO192x_ASM_ FX00000).
File preview

Tạo package asm04 dùng để chứa source code của bài tập 04, theo hình trên.
a)     Main

File Asm04.java chứa mã nguồn hàm main, để khởi chạy chức năng.

b)     BinaryFileService

File BinaryFileService định nghĩa lớp có chức năng cung cấp các dịch vụ đọc/ghi file nhị phân. Bao gồm 2 phương thức static chính:

Phương thức readFile(filePath). Đầu vào là đường dẫn đến thư mục, đầu ra là danh sách đối tượng.
File preview

Ví dụ:

File preview

Phương thức writeFile(filePath, objects) là phương thức void nhận đầu vào là đường dẫn đến thư mục và danh sách đối tượng cần lưu.
Ví dụ:

File preview

c)     CustomerDao, AccountDao, TransactionDao

File CustomerDao định nghĩa lớp trung gian thao tác với file để lấy dữ liệu và thêm mới, cập nhật dữ liệu
Tạo một biến final static là FILE_PATH để quy định tên file dùng để lưu dữ liệu của customers.
Ví dụ:

File preview

Tạo phương thức void static save() để lưu danh sách khách hàng vào file. Input là danh sách khách hàng.
Tạo phương thức static list() không có tham số đầu vào để lấy ra danh sách khách hàng từ file. Output là danh sách khách hàng.
Ví dụ:

File preview

Tạo tương tự 2 class là AccountDao và TransactionDao tương tự như CustomerDao.
Bổ sung thêm phương thức AccountDao.update(account) để cập nhật số dư cho tài khoản:
File preview

d)     ITransfer

File ITransfer định nghĩa interface xử lý nghiệp vụ chuyển tiền, bao gồm phương thức transfer(receiveAccount, amount).

e)     TextFileService

File TextFileService định nghĩa lớp thao tác với file text, người dùng có thể hiển thị và sửa đổi dữ liệu file text này.
Biến static final String COMMA_DELIMITER định nghĩa dấu phân cách giữa các phần tử trong file.
Ví dụ:

File preview

Ở phạm vi assignment này chỉ cần cung cấp phương thức static List<List<String>> readFile(String fileName).

Cấu trúc file ví dụ cho khách hàng ví dụ như là:

File preview

f)      Bank

Lớp Bank chỉ gồm 2 thuộc tính là bankId và bankName. Các thuộc tính list không cần sử dụng vì giờ đã lưu trữ list vào file và lấy ra trực tiếp từ file. Bạn vẫn có thể tái sử dụng nếu vẫn đáp ứng được yêu cầu.

g)     DigitalBank

Lớp DigitalBank lấy từ assignment 3 có bổ sung và sửa đổi thêm một số file sau:

 Phương thức showCustomers() sẽ đọc dữ liệu từ file sử dụng hàm CustomerDao.list() để lấy danh sách khách hàng và hiển thị thông qua hàm displayInformation. Nếu không có khách hàng nào trong danh sách thì hiển thị “Chưa có khách hàng nào trong danh sách!”.
Phương thức addCustomers(fileName) sẽ đọc dữ liệu từ file, dữ liệu từ file bao gồm nhiều khách hàng, kiểm tra dữ liệu từng khách hàng có số ID hợp lệ hay không, nếu hợp lệ thì thêm vào danh sách, nếu không hợp lệ hoặc số ID đã tồn tại thì hiển thị đoạn thông báo. Sau đó lưu dữ liệu customer vào file.
Phương thức addSavingAccount(Scanner scanner, String customerId) để tạo mới một tài khoản ATM cho một khách hàng và lưu tài khoản vào file. Phương thức này cần kiểm tra customerId hợp lệ, sau đó gọi phương thức thêm tài khoản mới của đối tượng customer.
Phương thức withdraw(Scanner scanner, String customerId) để rút tiền. Phương thức này cần kiểm tra customerId hợp lệ, sau đó hiển thị các thông tin tài khoản của khách hàng rồi gọi đến phương thức rút tiền của đối tượng customer.
Phương thức tranfers(Scanner scanner, String customerId) để chuyển tiền giữa 2 tài khoản. Phương thức này cần kiểm tra customerId hợp lệ, sau đó hiển thị các thông tin tài khoản của khách hàng rồi gọi đến phương thức chuyển tiền của đối tượng customer.
Phương thức isAccountExisted(List<Account> accountsList, Account newAccount) kiểm tra một account đã tồn tại trong mảng không.
Phương thức isCustomerExisted(List<Customer> customers, Customer newCustomer) kiểm tra một customer có tồn tại trong mảng hay không.
Phương thức getCustomerById(List<Customer> customerList, String customerId) lấy ra một customer có id bằng id cho trước.
h)     Transaction

Lớp Transaction lấy từ assignment 3 có bổ sung và sửa đổi một số file sau:

Implements Serializable và thuộc tính static final long serialVersionUID để hỗ trợ đọc/ghi object.
Thêm thuộc tính type để đánh dấu giao dịch là nhận tiền, rút tiền hay chuyển tiền. Type có thể là String hoặc Enum: DEPOSIT/WITHDRAW/TRANSFER.
Hàm toString() hiển thị thêm trường type.
File preview

i)      Account

Lớp Account lấy từ assignment 3 có bổ sung và sửa đổi một số file sau:

Implements Serializable và thuộc tính static final long serialVersionUID để hỗ trợ đọc/ghi object.
Thêm thuộc tính customerId, để sau khi lưu vào file thì sẽ xác định được account thuộc customer nào.
Phương thức getTransactions lấy ra các giao dịch thuộc account này. Phương thức này sẽ lấy ra tất cả các giao dịch và lọc trong danh sách các giao dịch có accountNumber bằng với accountNumber hiện tại.
Phương thức getCustomer().
Phương thức displayTransactionsList() – lấy ra danh sách transaction từ hàm getTransaction rồi hiển thị ra màn hình.
Phương thức createTransaction(double amount, String time, boolean status, TransactionType type) tạo ra thêm một giao dịch cho account và cập nhật số dư tài khoản.
Phương thức input(Scanner scanner) để thêm tài khoản mới vào danh sách. Yêu cầu người dùng nhập số tài khoản và số tiền ban đầu. Kiểm tra tính hợp lệ của giao dịch ban đầu sau đó gọi hàm createTransaction() để tạo ra một giao dịch thêm tiền (DEPOSIT) và thay đổi số dư tài khoản.
j)      IReport

Hàm log(double amount, TransactionType type, String receiveAccount) thêm trường type để hiển thị biên lai giao dịch theo từng trường hợp rút tiền/chuyển tiền.

k)     SavingAccount

Phương thức withdraw(double amount) kiểm tra điều kiện rút tiền (quy định ở các asm trước), nếu hợp lệ thì gọi phương thức tạo mới giao dịch và cập nhật số dư của tài khoản, nếu không hợp lệ thì trả về thông báo.
Phương thức transfers(Account receiveAccount, double amount) kiểm tra điều kiện chuyển tiền (giống điều kiện rút tiền), nếu hợp lệ thì tạo một giao dịch trừ tiền của người gửi và giao dịch cộng tiền cho người nhận, nếu không hợp lệ thì trả về lỗi.
l)      Customer

Lớp Customer lấy từ assignment 2 có bổ sung và sửa đổi một số chức năng sau:

Tạo một hàm khởi tạo Customer(List<String> values) để phục vụ việc đọc dữ liệu từ file text.
File preview

Implements Serializable và thuộc tính static final long serialVersionUID để hỗ trợ đọc/ghi object.
Phương thức getAccounts() lấy ra những account có customerId bằng customerId hiện tại. Phương thức này sử dụng stream.filter để lọc từ danh sách account lấy từ file.
Phương thức displayInformation() Hiển thị thông tin của customer, thông tin các tài khoản của customer (lấy tài khoản từ phương thức getAccount).
Phương thức getAccountByAccountNumber(List<Account> accounts, String accountNumber) lấy ra account từ trong danh sách.
Phương thức displayTransactionInformation() hiển thị thông tin customer, thông tin các tài khoản và thông tin các giao dịch của khách hàng hiện tại.
Phương thức withdraw(Scanner scanner) yêu cầu nhập số tài khoản (lấy danh sách accounts từ getAccounts() để kiểm tra xem tài khoản có tồn tại hay không), nhập số tiền rút sau đó gọi hàm rút tiền của account.
File preview

Phương thức tranfers(Scanner scanner) yêu cầu nhập tài khoản dùng để chuyển tiền, nhập tài khoản nhận tiền (kiểm tra tính hợp lệ của từng tài khoản), sau đó yêu cầu nhập số tiền rút, xác nhận việc chuyển tiền và sau khi thỏa mãn hết các điều kiện sẽ gọi hàm transfer của account gửi.
m) CustomerIdNotValidException

Định nghĩa exception khi kiểm tra customerId không hợp lệ.
