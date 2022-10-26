package dto;

import java.io.IOException;

//@author SE173593
public interface I_List {

    // Check ID đã tồn tại
    boolean checkExistId(String accountId);

    // Check name đã tồn tại
    boolean checkExistName(String accountName);

    //Tạo tài khoản
    void createAccount();

    // Thông tin tài khoản
    Account inputAccountData();

    // Đăng nhập
    void loginAccount();

    // Rút tiền
    void withdrawMoney();

    // Nạp tiền
    void depositMoney();

    // Chuyển tiền 
    void transferMoney();

    // Xóa tài khoản khỏi database
    void removeAccount();

    //Lưu dữ liệu tài khoản vào database
    void saveAccountToDatabase(String fileName);

    // Lưu dữ liệu money vào database
    void saveMoneyToDatabase(String fileName);

    // Lấy dữ liệu từ file
    void getDataFromDatabase(String firstFileName, String secondFileName) throws IOException;

    // Mã hóa chuỗi được truyền vào
    String encrypt(String text);

    // Giải mã chuỗi được truyền vào
    String decrypt(String text);

    // In tên của tài khoản đang đăng nhập
    void printCurrentUser();
}
