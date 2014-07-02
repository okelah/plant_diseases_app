package plant.diseases.android.util;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.net.*;
import java.util.Enumeration;

public class NetworkUtil {

	public static boolean isNetworkAvailable(Context ctx) {
		ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService("connectivity");
		if (cm == null) {
			return false;
		}
		NetworkInfo[] netinfo = cm.getAllNetworkInfo();
		if (netinfo == null) {
			return false;
		}
		for (int i = 0; i < netinfo.length; i++) {
			if (netinfo[i].isConnected()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断WIFI是否打开
	 * 
	 * @param ctx
	 * @return
	 */
	public static boolean isOpenWifi(final Context ctx) {
		final WifiManager wifi = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
		return wifi.isWifiEnabled();
	}

	public static boolean is3GDataActive(final Context ctx) {
		final TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
		int state = tm.getDataState();
		switch (state) {
		case TelephonyManager.DATA_CONNECTING:
		case TelephonyManager.DATA_CONNECTED:
			return true;
		case TelephonyManager.DATA_DISCONNECTED:
			return false;
		}
		return false;
	}

	/**
	 * PING内网地址
	 * 
	 * @return
	 */
	public static boolean ping(final Context ctx, String ip) {
		// 使用Session连接一下服务端，如果能连上表示OK
		URL url = null;
		int code = 0;
		try {
			url = new URL(ip);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(3 * 1000);
			conn.connect();
			code = conn.getResponseCode();
			Log.e("HTTP", code + "");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			return false;
		}
		return code != 0;
	}

	/**
	 * 获取 3G 或 WIFI 地址.
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e(DeviceUtil.class.getCanonicalName(), "get local ip address got error: ", ex);
		}
		return StringUtil.EMPTY;
	}

    /**
     * GPRS是否可用
     *
     * @param context
     * @return
     */
    public static boolean isGPRSAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState();
        if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
            return true;
        }
        return false;
    }


}
