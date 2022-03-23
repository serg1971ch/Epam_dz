import main.DecryptService;
import main.DecryptServiceImpl;
import main.FileServiceImpl;

public class MainDecryptor {
    public static void main(String[] args) {
        System.out.println(new DecryptServiceImpl().decrypt(new FileServiceImpl().readTextFromFile("C:\\Users\\shiba\\epam_project\\Epam_dz\\CeasarCriph\\src\\java\\resource\\text.txt")));
    }
}
