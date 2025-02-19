package chapter2;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Pro4AllNameOfNetworkAdapters {
    public static void main(String[] args) throws SocketException {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

        while (networkInterfaces.hasMoreElements()) {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            String name = networkInterface.getName();
            String displayName = networkInterface.getDisplayName();

            System.out.println("Name: " + name);
            System.out.println("Display Name: " + displayName);
            System.out.println("--------------");
        }
    }
}
/*
Output: -> Write any 2 to 3 output not all
Name: ethernet_0
Display Name: Hyper-V Virtual Switch Extension Adapter-Hyper-V Virtual Switch Extension Filter-0000
--------------
Name: ethernet_1
Display Name: Hyper-V Virtual Switch Extension Adapter-Virtual Filtering Platform VMSwitch Extension-0000
--------------
Name: ethernet_2
Display Name: Hyper-V Virtual Ethernet Adapter-WFP Native MAC Layer LightWeight Filter-0000
--------------
Name: ethernet_3
Display Name: Hyper-V Virtual Ethernet Adapter-Npcap Packet Driver (NPCAP)-0000
--------------
Name: ethernet_4
Display Name: Hyper-V Virtual Ethernet Adapter-QoS Packet Scheduler-0000
--------------
Name: ethernet_5
Display Name: Hyper-V Virtual Ethernet Adapter-WFP 802.3 MAC Layer LightWeight Filter-0000
--------------
Name: ethernet_6
Display Name: Bluetooth Device (Personal Area Network)-Npcap Packet Driver (NPCAP)-0000
--------------
Name: ethernet_7
Display Name: WAN Miniport (IP)-WFP Native MAC Layer LightWeight Filter-0000
--------------
Name: ethernet_8
Display Name: WAN Miniport (IP)-Npcap Packet Driver (NPCAP)-0000
--------------
Name: ethernet_9
Display Name: WAN Miniport (IP)-QoS Packet Scheduler-0000
--------------
Name: ethernet_10
Display Name: WAN Miniport (IPv6)-WFP Native MAC Layer LightWeight Filter-0000
--------------
Name: ethernet_11
Display Name: WAN Miniport (IPv6)-Npcap Packet Driver (NPCAP)-0000
--------------
Name: ethernet_12
Display Name: WAN Miniport (IPv6)-QoS Packet Scheduler-0000
--------------
Name: ethernet_13
Display Name: WAN Miniport (Network Monitor)-WFP Native MAC Layer LightWeight Filter-0000
--------------
Name: ethernet_14
Display Name: WAN Miniport (Network Monitor)-Npcap Packet Driver (NPCAP)-0000
--------------
Name: ethernet_15
Display Name: WAN Miniport (Network Monitor)-QoS Packet Scheduler-0000
--------------
Name: ethernet_32768
Display Name: Microsoft Kernel Debug Network Adapter
--------------
Name: ethernet_32769
Display Name: Bluetooth Device (Personal Area Network)
--------------
Name: ethernet_32770
Display Name: WAN Miniport (IP)
--------------
Name: ethernet_32771
Display Name: WAN Miniport (IPv6)
--------------
Name: ethernet_32772
Display Name: WAN Miniport (Network Monitor)
--------------
Name: ethernet_32773
Display Name: Hyper-V Virtual Switch Extension Adapter
--------------
Name: ethernet_32774
Display Name: Hyper-V Virtual Ethernet Adapter
--------------
Name: ppp_32768
Display Name: WAN Miniport (PPPOE)
--------------
Name: loopback_0
Display Name: Software Loopback Interface 1
--------------
Name: wireless_0
Display Name: Realtek 8821CE Wireless LAN 802.11ac PCI-E NIC-WFP Native MAC Layer LightWeight Filter-0000
--------------
Name: wireless_1
Display Name: Realtek 8821CE Wireless LAN 802.11ac PCI-E NIC-Virtual WiFi Filter Driver-0000
--------------
Name: wireless_2
Display Name: Realtek 8821CE Wireless LAN 802.11ac PCI-E NIC-Native WiFi Filter Driver-0000
--------------
Name: wireless_3
Display Name: Realtek 8821CE Wireless LAN 802.11ac PCI-E NIC-Npcap Packet Driver (NPCAP)-0000
--------------
Name: wireless_4
Display Name: Realtek 8821CE Wireless LAN 802.11ac PCI-E NIC-QoS Packet Scheduler-0000
--------------
Name: wireless_5
Display Name: Realtek 8821CE Wireless LAN 802.11ac PCI-E NIC-WFP 802.3 MAC Layer LightWeight Filter-0000
--------------
Name: wireless_6
Display Name: Microsoft Wi-Fi Direct Virtual Adapter-WFP Native MAC Layer LightWeight Filter-0000
--------------
Name: wireless_7
Display Name: Microsoft Wi-Fi Direct Virtual Adapter-Native WiFi Filter Driver-0000
--------------
Name: wireless_8
Display Name: Microsoft Wi-Fi Direct Virtual Adapter-Npcap Packet Driver (NPCAP)-0000
--------------
Name: wireless_9
Display Name: Microsoft Wi-Fi Direct Virtual Adapter-QoS Packet Scheduler-0000
--------------
Name: wireless_10
Display Name: Microsoft Wi-Fi Direct Virtual Adapter-WFP 802.3 MAC Layer LightWeight Filter-0000
--------------
Name: wireless_11
Display Name: Microsoft Wi-Fi Direct Virtual Adapter #2-WFP Native MAC Layer LightWeight Filter-0000
--------------
Name: wireless_12
Display Name: Microsoft Wi-Fi Direct Virtual Adapter #2-Native WiFi Filter Driver-0000
--------------
Name: wireless_13
Display Name: Microsoft Wi-Fi Direct Virtual Adapter #2-Npcap Packet Driver (NPCAP)-0000
--------------
Name: wireless_14
Display Name: Microsoft Wi-Fi Direct Virtual Adapter #2-QoS Packet Scheduler-0000
--------------
Name: wireless_15
Display Name: Microsoft Wi-Fi Direct Virtual Adapter #2-WFP 802.3 MAC Layer LightWeight Filter-0000
--------------
Name: wireless_32768
Display Name: Realtek 8811CU Wireless LAN 802.11ac USB NIC
--------------
Name: wireless_32769
Display Name: Microsoft Wi-Fi Direct Virtual Adapter
--------------
Name: wireless_32770
Display Name: Microsoft Wi-Fi Direct Virtual Adapter #2
--------------
Name: wireless_32771
Display Name: Realtek 8821CE Wireless LAN 802.11ac PCI-E NIC
--------------
Name: wireless_32772
Display Name: Microsoft Wi-Fi Direct Virtual Adapter #3
--------------
Name: wireless_32773
Display Name: Microsoft Wi-Fi Direct Virtual Adapter #4
--------------
Name: tunnel_32512
Display Name: Microsoft Teredo Tunneling Adapter
--------------
Name: tunnel_32513
Display Name: Microsoft IP-HTTPS Platform Adapter
--------------
Name: tunnel_32514
Display Name: Microsoft 6to4 Adapter
--------------
Name: tunnel_32768
Display Name: WAN Miniport (SSTP)
--------------
Name: tunnel_32769
Display Name: WAN Miniport (IKEv2)
--------------
Name: tunnel_32770
Display Name: WAN Miniport (L2TP)
--------------
Name: tunnel_32771
Display Name: WAN Miniport (PPTP)
--------------
 */
