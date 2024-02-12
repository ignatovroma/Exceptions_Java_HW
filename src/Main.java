import java.io.*;
import java.nio.file.FileSystemException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            inputData();
        } catch (FileSystemException e){
            System.out.println(e.getMessage());
        }

    }

    public static void inputData() throws Exception {
        System.out.println("Введите Фамилию Имя Отчество дату рождения(дд.мм.гггг) телефон(11ть цифр без + ) пол(m - мужской, f - женский)");

        String data;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            data = br.readLine();
        } catch (IOException e) {
            throw new Exception(">>> Ошибка ввода данных <<<");
        }

        String[] dataArr = data.split(" ");
        if (dataArr.length != 6) {
            throw new Exception(">>> Введены не все данные <<<");
        }

        String surname = dataArr[0];
        String name = dataArr[1];
        String lastname = dataArr[2];

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date birthdate;
        try {
            birthdate = dateFormat.parse(dataArr[3]);
        } catch (ParseException e) {
            throw new ParseException(">>> Неправильно введена дата рождения <<<", e.getErrorOffset());
        }

        String phone = dataArr[4];
        if (!phone.matches("\\d{11}")) {
            throw new RuntimeException(">>> Неправильно введен телефон <<<");
        }

//        try {
//            phone = Integer.parseInt(dataArr[4]);
//        } catch (NumberFormatException e) {
//            throw new NumberFormatException(">>> Неправильно введен телефон <<<");
//        }

        String gender = dataArr[5];
        if (!gender.matches("[mf]")) {
            throw new RuntimeException(">>> Неправильно введен пол <<<");
        }

        String fileName = surname.toUpperCase() + ".txt";
        File file = new File(fileName);
        try (FileWriter fw = new FileWriter(file, true)) {
            fw.write(String.format("%s %s %s %s %s %s\n", surname, name, lastname, dateFormat.format(birthdate), phone, gender));
        } catch (IOException e) {
            throw new IOException(">>> Ошибка записи в файл <<<");
        }
    }
}