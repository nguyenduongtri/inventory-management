package inventory.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss:mm");
		return sdf.format(date);
	}

}
