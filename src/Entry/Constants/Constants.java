package Entry.Constants;

import java.text.SimpleDateFormat;

/**
 * Class where all the constants live, for easier management
 */
public class Constants {

    /**
     * Identifier for the init panel
     */
    public static final String INIT = "Init_Panel";

    /**
     * Identifier for the reserve panel
     */
    public static final String RESERVE = "Reserve_Panel";

    /**
     * Identifier for the date picker panel
     */
    public static final String DATE_PICKER = "DatePicker_Panel";

    /**
     * Identifier for the landing panel
     */
    public static final String LANDING = "Landing_Panel";


    /**
     * Action command for the now button
     */
    public static final String NOW = "Button now";

    /**
     * Action command for the later button
     */
    public static final String LATER = "Button later";


    /**
     * Date format used in this client
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
}
