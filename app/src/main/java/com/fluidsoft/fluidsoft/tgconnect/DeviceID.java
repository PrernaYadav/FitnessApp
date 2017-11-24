package com.fluidsoft.fluidsoft.tgconnect;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by user on 03-May-17.
 */

public class DeviceID {

    private static String ID = null;
    static Context context;

    // return a cached unique ID for each device
    public static String getID() {

        // if the ID isn't cached inside the class itself
        if (ID == null) {
            //get it from database / settings table (implement your own method here)
//            ID = Settings.get("DeviceID");
            ID = Settings.System.ANDROID_ID;
        }

        // if the saved value was incorrect
        if (ID.equals("0")) {
            // generate a new ID
            ID = generateID(context);

            if (ID != null) {
                // save it to database / setting (implement your own method here)
//                Settings.set("DeviceID", ID);
            }
        }
        return ID;
    }
    // generate a unique ID for each device
    // use available schemes if possible / generate a random signature instead
    public static String generateID(Context context) {

        // use the ANDROID_ID constant, generated at the first device boot
        String deviceId = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        // in case known problems are occured
        if ("9774d56d682e549c".equals(deviceId) || deviceId == null) {

            // get a unique deviceID like IMEI for GSM or ESN for CDMA phones
            // don't forget:
            //
            deviceId = ((TelephonyManager) context
                    .getSystemService( Context.TELEPHONY_SERVICE ))
                    .getDeviceId();

            // if nothing else works, generate a random number
            if (deviceId == null) {

                Random tmpRand = new Random();
                deviceId = String.valueOf(tmpRand.nextLong());
            }

        }

        // any value is hashed to have consistent format
        return getHash(deviceId);
    }

    // generates a SHA-1 hash for any string
    public static String getHash(String stringToHash) {

        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] result = null;

        try {
            result = digest.digest(stringToHash.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        for (byte b : result)
        {
            sb.append(String.format("%02X", b));
        }

        String messageDigest = sb.toString();
        return messageDigest;
    }
}
