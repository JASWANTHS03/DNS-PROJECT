//import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class DNS_Client 
//implements ActionListener
{ 
    public static String host; 
     public static int port;
    DNS_Client()
    {
        JFrame jfrm1=new JFrame("DNS Project");
        jfrm1.setSize(400,400);
        jfrm1.setLayout(null);
        jfrm1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JLabel L1=new JLabel("--- DNS Design ---");
        L1.setFont(new Font("Verdana", Font.BOLD, 25));
        L1.setBounds(58,100,300,40);
        JButton B1=new JButton("Start");
        B1.setBounds(100,200,150,40);

        jfrm1.add(B1);
        jfrm1.add(L1);
        jfrm1.setVisible(true);
        B1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
            try
            {
                jfrm1.dispose();
                DNS_LookUp();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            }
        });
    }
public void DNS_LookUp() 
{
    //frame -1
    JFrame jfrm2=new JFrame("DNS");
    jfrm2.setLayout(null);
    jfrm2.setSize(400,400);
    jfrm2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JTextField jtf1=new JTextField(100);
    jtf1.setBounds(70,150,250,40);
    jfrm2.add(jtf1);

    JLabel jl3=new JLabel("Domain Name ");;
    jl3.setFont(new Font("Serif", Font.BOLD, 25));

    jl3.setBounds(120,50,200,100);
    jfrm2.add(jl3);
    JButton jb1=new JButton("Find");
    jb1.setBounds(150,240,100,40);
    jfrm2.add(jb1);

    //frame-3
    JFrame jf3 = new JFrame("DNS");
    jf3.setSize(400, 300);

    JLabel jLabel11 = new javax.swing.JLabel();
    JLabel jLabel12 = new javax.swing.JLabel();
    JTextField jTextField11 = new javax.swing.JTextField();
    JButton jButton11 = new javax.swing.JButton();

    jf3.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
    jLabel11.setText("---Client---");

    jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel12.setText("IP address");
    jTextField11.setEditable(false);

    jButton11.setText("Exit");
    jButton11.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ae)
        {
        try
        {
            jf3.dispose();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    
        }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jf3.getContentPane());
    jf3.getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(139, 139, 139)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(128, 128, 128)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                    .addGap(146, 146, 146)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(83, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(32, 32, 32)
            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
            .addComponent(jButton11)
            .addGap(34, 34, 34))
    );

    jb1.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent ae)
        {
            jf3.setVisible(true);
            jfrm2.dispose();
            try
            {
                String s = DNS_Design(jtf1.getText());
                jTextField11.setText(s);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
    }
    });
 
 jfrm2.setVisible(true);
 }
public String DNS_Design(String s1) throws Exception
{
String s2;

 System.out.println("Reaching DNS Server at " +host+ " with port " + port + "..."); 
 
 System.out.print("Type a website: "); 
System.out.println(s1);
 String given_hostname = s1;
 
 DatagramSocket client_socket = new DatagramSocket();
 client_socket.setSoTimeout(3000);
 
 InetAddress IP_address = InetAddress.getByName(host);
 byte[] send_data = new byte[1024];
 byte[] receive_data = new byte[1024];
 
 send_data = given_hostname.getBytes();
 
 DatagramPacket send_packet = new DatagramPacket(send_data, send_data.length, IP_address, port);
 client_socket.send(send_packet);
 
 DatagramPacket receive_packet = new DatagramPacket(receive_data, receive_data.length);
 try
 {
 
 client_socket.receive(receive_packet);
 String server_response = new String(receive_packet.getData());
 
 String two_char_response = server_response.substring(0,2);
 s2=server_response;
 
 if(two_char_response.equals("-1"))
 {
 System.out.println("DNS server's response: NOT FOUND");
 s2="NOT FOUND";
 client_socket.close();
 return s2;
 }
 else
 {
 
 System.out.println("DNS server's response: " + server_response);

 s2=server_response;
 client_socket.close();
 return s2;
 }
 } 
 catch (SocketTimeoutException e) 
 {
System.out.println("Timeout reached. " + e);
client_socket.close();
s2="Timeout reached.";
 return s2;
 
 }
 
 }
 
 public static void main(String args[]) throws Exception
 {
if(args.length==0)
{
    host = "127.0.0.1";
	 port = 8888;
}
else
{
    host = args[0];
	port = Integer.parseInt(args[1]);
}
SwingUtilities.invokeLater(
    new Runnable()
    {
        public void run()
        {
            System.out.println("-----------------------------------------");
            System.out.println("Usage: java DNS_Client <host name> <port>"); 
            System.out.println("Default host name: 127.0.0.1");
            System.out.println("Default port : "+port);
            System.out.println("-----------------------------------------\n");
            new DNS_Client();
        }
    }
);
 } 
}
