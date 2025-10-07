import java.util.Scanner;

public class SwitchCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("KALKULATOR");
            System.out.print("Masukkan angka pertama: ");
            double a = sc.nextDouble();

            System.out.print("Masukkan operator: ");
            String op = sc.next();

            if (op.equalsIgnoreCase("q")) {
                System.out.println("Selesai. Terima kasih!");
                break;
            }

            System.out.print("Masukkan angka kedua: ");
            double b = sc.nextDouble();

            double result;
            switch (op) {
                case "+":
                    result = a + b;
                    System.out.println("Hasil: " + result);
                    break;
                case "-":
                    result = a - b;
                    System.out.println("Hasil: " + result);
                    break;
                case "*":
                    result = a * b;
                    System.out.println("Hasil: " + result);
                    break;
                case "/":
                    if (b == 0) System.out.println("Error: pembagi 0.");
                    else System.out.println("Hasil: " + (a / b));
                    break;
                case "%":
                    if (b == 0) System.out.println("Error: modulus 0.");
                    else System.out.println("Hasil: " + (a % b));
                    break;
                case "^": // pangkat sederhana
                    System.out.println("Hasil: " + Math.pow(a, b));
                    break;
                default:
                    System.out.println("Operator tidak dikenali.");
            }
        }
        sc.close();
    }
}
