package plant.diseases.android.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

public class DeviceUtil {

	private static final String MODEL_V2 = "V2";

	private static final String MODEL_S1 = "S1";

	private static final String MODEL_S2 = "S2";

	/**
	 * 获取AndroidID
	 */
	public static String getAndroidID(final Context ctx) {
		String androidID = "";
		if (ctx != null) {
			androidID = android.provider.Settings.Secure.getString(ctx.getContentResolver(),
					android.provider.Settings.Secure.ANDROID_ID);
		}
		return androidID;
	}

	/**
	 * 唯一的设备ID号, IMEI不存在于SIM卡中，它是手机本身的串号 IMEI也是一串唯一的数字， 标识了 GSM 和 UMTS网络里的唯一一个手机.它通常被打印在手机里电池下面的那一面，拨 *#06# 也能看到它. IMEI
	 * 与 设备唯一对应. GSM手机的 IMEI号 和 CDMA手机的 MEID【全球唯一】 Return null if device ID is not available.
	 */
	public static String getDeviceId(final Context ctx) {
		String deviceId = null;
		TelephonyManager telManager = getTelephonyManager(ctx);
		if (telManager != null) {
			deviceId = telManager.getDeviceId();
		}
		return StringUtil.trimToEmpty(deviceId);
	}

	/**
	 * 获得电话管理器
	 * 
	 * @param ctx
	 * @return
	 */
	public static TelephonyManager getTelephonyManager(final Context ctx) {
		TelephonyManager telManager = null;
		if (ctx != null) {
			telManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
		}
		return telManager;
	}

	public static String InputSnCodeToSdcard() {
		try {
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec("/system/bin/rootcall.sh");
		} catch (Exception e) {
			e.printStackTrace();
			throw new SecurityException();
		}
		SystemClock.sleep(10);
		String result = "";
		String path = "/sdcard/sn.txt";
		File file = new File(path);
		InputStream in = null;
		try {
			in = new FileInputStream(file);
			byte[] re = new byte[1024];
			while (in.read(re) != -1) {
				String snCode = result + new String(re);
				result = snCode.trim();
			}
		} catch (Exception ex) {
			ex.getStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 获取Sim卡号
	 */
	public static String getSIM(final Context ctx) {
		String sim = null;
		TelephonyManager telManager = getTelephonyManager(ctx);
		if (telManager != null) {
			sim = telManager.getSimSerialNumber();
		}
		return StringUtil.trimToEmpty(sim);
	}

	/**
	 * 获取版本号
	 */
	public static String getVersion(Context context) {
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
			return pi.versionName;
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 获取设备sdcard卡路径
	 * @return sdcard 路径
	 */
	public static String getSDCardPath() {
		// 判断sd卡是否存在
		boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		if (sdCardExist) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		} else {
			return null;
		}
	}

	public static DisplayMetrics getDisplayMetrics(Context context) {
		DisplayMetrics dm2 = context.getResources().getDisplayMetrics();
		return dm2;
	}
}
