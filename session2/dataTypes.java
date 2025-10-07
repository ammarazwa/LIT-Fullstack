public class dataTypes{
    public static void main (String[] args){
        String peserta1 = "Martha";
        String peserta2 = "Martha";
        String peserta3 = new String ("Martha");

        System.out.println(peserta1 == peserta2);
        System.out.println(peserta1 == peserta3);
        System.out.println(peserta1.equals(peserta3));
    }
}