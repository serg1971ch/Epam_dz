package main;

public class DecryptServiceImpl implements DecryptService {
    @Override
    public String decrypt(String cipher, int step) {
        char[] cipherInChars = cipher.toCharArray();
        StringBuilder decryptedCipher = new StringBuilder(" ");
        for (int i = 0; i < cipherInChars.length; i++) {
            if((cipherInChars[i]>= 'А')&&(cipherInChars[i]<='Я'-step)) {
                cipherInChars[i] += step;
            } else if ((cipherInChars[i]>= 'А')&&(cipherInChars[i])<='Я') {
                cipherInChars[i] = (char) ('А' - 1 +(step - ('Я' - cipherInChars[i])));
            } else if ((cipherInChars[i]>= 'а') && (cipherInChars[i] <= 'я'-step)) {
                cipherInChars[i] += step;
            }else if ((cipherInChars[i]>= 'а')&&(cipherInChars[i])<='я') {
                cipherInChars[i] = (char) ('а' - 1 + (step - ('я' - cipherInChars[i])));
            }
            decryptedCipher.append(cipherInChars[i]);
        }
        return decryptedCipher.toString();
    }

    @Override
    public String decrypt(String cipher) {
        String decryptedCipherVariations = "";
        for (int i = 0; i < 33; i++) {
            decryptedCipherVariations += decrypt(cipher, i);
            decryptedCipherVariations += "\n";
        }
        return decryptedCipherVariations;
    }

    @Override
    public void printDecrypted(String cipher, int step) {
        System.out.println(decrypt(cipher, step));
    }

    @Override
    public void printDecrypted(String cipher) {
        System.out.println(decrypt(cipher));
    }
}
