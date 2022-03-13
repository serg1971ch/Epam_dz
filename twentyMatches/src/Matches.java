import java.util.Scanner;

public class Matches {
    private static int sum = 17;
    private static int countMatches = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("На столе осталось 20 спичек");
        System.out.println("- Количество выбранных компьютером спичек = 3. \n" +
                "На столе осталось 17 спичек");
        System.out.print("- Ход игрока. Введите количество спичек: ");

        while (sum > 1) {
            int player = scanner.nextInt();
            if (player == 1) {
                sum -= player;
                System.out.println("На столе осталось " + sum + " спичек");
                countMatches = 3;
                sum -= countMatches;
                System.out.println("- Количество выбранных компьютером спичек = " + countMatches + ".");
                if (sum == 1) {
                    System.out.println("Для игрока осталась последняя спичка. Игрок проиграл!");
                    return;
                }
                System.out.println("На столе осталось " + sum + " спичек");
                System.out.print("- Ход игрока. Введите ещё раз количество спичек: ");
            } else if (player == 2) {
                sum -= player;
                System.out.println("На столе осталось " + sum + " спичек");
                countMatches = 2;
                sum -= countMatches;
                System.out.println("- Количество выбранных компьютером спичек = " + countMatches + ".");
                if (sum == 1) {
                    System.out.println("Для игрока осталась последняя спичка. Игрок проиграл!");
                    return;
                }
                System.out.println("На столе осталось " + sum + " спичек");
                System.out.print("- Ход игрока. Введите ещё раз количество спичек: ");
            } else if (player == 3) {
                sum -= player;
                System.out.println("На столе осталось " + sum + " спичек");
                countMatches = 1;
                sum -= countMatches;
                System.out.println("- Количество выбранных компьютером спичек = " + countMatches + ".");
                if (sum == 1) {
                    System.out.println("Для игрока осталась последняя спичка. Игрок проиграл!");
                    return;
                }
                System.out.println("На столе осталось " + sum + " спичек");
                System.out.print("- Ход игрока. Введите количество спичек: ");
            } else {
                System.out.println("Некорректное количество спичек!");
                System.out.println("На столе осталось " + sum + " спичек");
                System.out.print("- Ход игрока. Введите количество спичек: ");
            }
        }
    }
}
