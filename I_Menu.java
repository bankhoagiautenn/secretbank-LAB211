package dto;

//@author SE173593
public interface I_Menu {

    // Thêm chuỗi vào danh sách
    void addItem(String s);

    // Lấy lựa chọn từ người dùng
    int getChoice(int min, int max);

    // In ra list những chuỗi đã thêm vào danh sách
    void showMenu();
}
