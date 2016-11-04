package bin;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IpAddress {


	public IpAddress() {

	}

	public String getIpAddress() {

		String currentHostIpAddress = null;
		String currentHostIpAddresses = "";
		Enumeration<NetworkInterface> netInterfaces = null;
		
        try {
            netInterfaces = NetworkInterface.getNetworkInterfaces();

            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    InetAddress addr = address.nextElement();
                    if (!addr.isLoopbackAddress() && addr.isSiteLocalAddress()
                            && !(addr.getHostAddress().indexOf(":") > -1)) {
                        currentHostIpAddress = addr.getHostAddress();
                        currentHostIpAddresses = currentHostIpAddresses + "/" + currentHostIpAddress;
                    }
                }
            }
            if (currentHostIpAddress == null) {
                currentHostIpAddress = "127.0.0.1";
            }

        } catch (SocketException e) {
            currentHostIpAddress = "127.0.0.1";
            LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ IpAddress.getIpAddress() ]");
        	LogWriter.write("[E] " + e.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        }
      
        currentHostIpAddresses = currentHostIpAddresses.substring(1,currentHostIpAddresses.length());
		return currentHostIpAddresses;

	}
	
	public String getHostName() {

		InetAddress ipaddress = null;
		
		try {
			
			ipaddress = InetAddress.getLocalHost();

			
		} catch (UnknownHostException e) {
			LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
        	LogWriter.write("[E] Errore in classe: [ IpAddress.getHostName() ]");
        	LogWriter.write("[E] " + e.getMessage());
        	LogWriter.write("[E] ----------------------------------------------------------------------------------------------------");
		}
		
		return ipaddress.getHostName();

	}

}
