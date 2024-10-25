import project.quanlykhutro.dao.DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        SimpleDateFormat ft
                = new SimpleDateFormat("yyyy-MM-dd");

        String str = ft.format(new Date());

        // Printing the formatted date
        System.out.println("Formatted Date : " + str);


    }
}