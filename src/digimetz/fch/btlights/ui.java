/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digimetz.fch.btlights;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author saliya
 */
public class ui extends javax.swing.JFrame implements WindowListener {

    private boolean BT_SCAN_FINISHED = false;
    private String BT_DEVICE_ADDRESS = "";
    private String BT_URL = "btspp://" + BT_DEVICE_ADDRESS + ":1;authenticate=false;encrypt=false;master=false";
    private RemoteDevice BT_DEVICE;
    private String[] BT_SPLIT_STRING;

    private StreamConnection BT_STREAM_CONNECTION;
    private OutputStream BT_OUT_STREAM;
    private InputStream BT_IN_STREAM;
    private boolean BT_OUT_STREAM_OPEN = false;
    private boolean BT_IN_STREAM_OPEN = false;

    private boolean BT_CONNECTED = false;
    private Thread BT_SCAN_THREAD;
    private Thread BT_CONNECT_THREAD;
    private Thread BT_RECEIVE_THREAD;

    private JSONObject JSONOBJ;
    private JSONArray RECENT_DEVICE_LIST;
    private JSONParser PARSER;
    private final String RECENT_DEVICE_LIST_FILE = "./RecentDevicesList.json";
    String RX_STRING = "";

    /**
     * Creates new form ui
     */
    public ui() {
        initComponents();
        initx();
    }

    private void initx() {
        addWindowListener(this);
        JSONOBJ = new JSONObject();
        RECENT_DEVICE_LIST = new JSONArray();
        PARSER = new JSONParser();
        getRecentDevices();
        if (BT_DEVICE_LIST_RECENT.getItemCount() == 0) {
            BT_DEVICE_LIST_RECENT.addItem("NO DEVICE SELECTED");
        }
        BTN_L1.setEnabled(false);
        BTN_L2.setEnabled(false);
        BTN_L3.setEnabled(false);
        BTN_L4.setEnabled(false);
        BTN_L5.setEnabled(false);
        BTN_A_ON.setEnabled(false);
        BTN_A_OFF.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel17 = new javax.swing.JLabel();
        BT_DEVICE_LIST = new javax.swing.JComboBox<>();
        BT_SCAN_BTN = new javax.swing.JButton();
        BT_CONNECT_BTN = new javax.swing.JButton();
        BTN_ABOUT = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        BT_DEVICE_LIST_RECENT = new javax.swing.JComboBox<>();
        BT_REF_BTN = new javax.swing.JButton();
        BT_CONNECT_RD_BTN = new javax.swing.JButton();
        BTN_L1 = new javax.swing.JToggleButton();
        BTN_L2 = new javax.swing.JToggleButton();
        BTN_L3 = new javax.swing.JToggleButton();
        BTN_L4 = new javax.swing.JToggleButton();
        BTN_L5 = new javax.swing.JToggleButton();
        BTN_A_ON = new javax.swing.JButton();
        BTN_A_OFF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("5 channel light automation development board : BT");
        setResizable(false);

        jLabel17.setText("Device :");

        BT_DEVICE_LIST.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO DEVICE SELECTED" }));
        BT_DEVICE_LIST.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BT_DEVICE_LISTItemStateChanged(evt);
            }
        });

        BT_SCAN_BTN.setText("Scan Bluetooth Devices");
        BT_SCAN_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_SCAN_BTNActionPerformed(evt);
            }
        });

        BT_CONNECT_BTN.setText("Connect");
        BT_CONNECT_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_CONNECT_BTNActionPerformed(evt);
            }
        });

        BTN_ABOUT.setText("about");
        BTN_ABOUT.setRequestFocusEnabled(false);
        BTN_ABOUT.setVerifyInputWhenFocusTarget(false);
        BTN_ABOUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ABOUTActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Recent Devices"));

        jLabel18.setText("Device :");

        BT_DEVICE_LIST_RECENT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO DEVICE SELECTED" }));

        BT_REF_BTN.setText("Refresh");
        BT_REF_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_REF_BTNActionPerformed(evt);
            }
        });

        BT_CONNECT_RD_BTN.setText("Connect");
        BT_CONNECT_RD_BTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BT_CONNECT_RD_BTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BT_DEVICE_LIST_RECENT, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BT_REF_BTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BT_CONNECT_RD_BTN)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BT_DEVICE_LIST_RECENT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BT_REF_BTN)
                    .addComponent(BT_CONNECT_RD_BTN))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BTN_L1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/digimetz/fch/btlights/res/lon.png"))); // NOI18N
        BTN_L1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_L1.setRequestFocusEnabled(false);
        BTN_L1.setRolloverEnabled(false);
        BTN_L1.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/digimetz/fch/btlights/res/loff.png"))); // NOI18N
        BTN_L1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_L1ActionPerformed(evt);
            }
        });

        BTN_L2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/digimetz/fch/btlights/res/lon.png"))); // NOI18N
        BTN_L2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_L2.setRequestFocusEnabled(false);
        BTN_L2.setRolloverEnabled(false);
        BTN_L2.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/digimetz/fch/btlights/res/loff.png"))); // NOI18N
        BTN_L2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_L2ActionPerformed(evt);
            }
        });

        BTN_L3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/digimetz/fch/btlights/res/lon.png"))); // NOI18N
        BTN_L3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_L3.setRequestFocusEnabled(false);
        BTN_L3.setRolloverEnabled(false);
        BTN_L3.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/digimetz/fch/btlights/res/loff.png"))); // NOI18N
        BTN_L3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_L3ActionPerformed(evt);
            }
        });

        BTN_L4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/digimetz/fch/btlights/res/lon.png"))); // NOI18N
        BTN_L4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_L4.setRequestFocusEnabled(false);
        BTN_L4.setRolloverEnabled(false);
        BTN_L4.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/digimetz/fch/btlights/res/loff.png"))); // NOI18N
        BTN_L4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_L4ActionPerformed(evt);
            }
        });

        BTN_L5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/digimetz/fch/btlights/res/lon.png"))); // NOI18N
        BTN_L5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BTN_L5.setRequestFocusEnabled(false);
        BTN_L5.setRolloverEnabled(false);
        BTN_L5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/digimetz/fch/btlights/res/loff.png"))); // NOI18N
        BTN_L5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_L5ActionPerformed(evt);
            }
        });

        BTN_A_ON.setText("all - on");
        BTN_A_ON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_A_ONActionPerformed(evt);
            }
        });

        BTN_A_OFF.setText("all - off");
        BTN_A_OFF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_A_OFFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BTN_A_ON)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_A_OFF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BTN_ABOUT))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(BTN_L1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BTN_L2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_L3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_L4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BTN_L5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BT_DEVICE_LIST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BT_SCAN_BTN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BT_CONNECT_BTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BT_DEVICE_LIST, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BT_CONNECT_BTN)
                    .addComponent(BT_SCAN_BTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BTN_L1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BTN_L2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BTN_L3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BTN_L4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BTN_L5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BTN_ABOUT)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BTN_A_ON)
                        .addComponent(BTN_A_OFF)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BT_DEVICE_LISTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BT_DEVICE_LISTItemStateChanged

    }//GEN-LAST:event_BT_DEVICE_LISTItemStateChanged

    private void BT_SCAN_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_SCAN_BTNActionPerformed
        BT_DEVICE_LIST.removeAllItems();
        RECENT_DEVICE_LIST.clear();
        lockScanning(true);
        BT_SCAN_THREAD = new Thread(() -> {
            try {
                BT_SCAN_FINISHED = false;
                LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, new DiscoveryListener() {
                    @Override
                    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
                        try {
                            String name = btDevice.getFriendlyName(false);
                            BT_DEVICE = btDevice;
                            BT_DEVICE_LIST.addItem(name.trim() + "::" + BT_DEVICE.getBluetoothAddress().trim());
                            RECENT_DEVICE_LIST.add(name.trim() + "::" + BT_DEVICE.getBluetoothAddress().trim());
                        } catch (IOException e) {
                        }
                        lockScanning(true);
                    }

                    @Override
                    public void inquiryCompleted(int discType) {
                        BT_SCAN_FINISHED = true;
                    }

                    @Override
                    public void serviceSearchCompleted(int transID, int respCode) {
                    }

                    @Override
                    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
                    }
                });
                while (!BT_SCAN_FINISHED) {
                    Thread.sleep(500);
                }
                UUID uuid = new UUID(0x1101);
                UUID[] searchUuidSet = new UUID[]{uuid};
                int[] attrIDs = new int[]{
                    0x0100
                };
                BT_SCAN_FINISHED = false;

                LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrIDs, searchUuidSet, BT_DEVICE, new DiscoveryListener() {
                    @Override
                    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
                    }

                    @Override
                    public void inquiryCompleted(int discType) {
                    }

                    @Override
                    public void serviceSearchCompleted(int transID, int respCode) {
                        BT_SCAN_FINISHED = true;
                        BT_DEVICE_LIST.setSelectedIndex(0);
                        lockScanning(false);
                        writeRecentDevicesList();
                        getRecentDevices();
                    }

                    @Override
                    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
                        for (ServiceRecord servRecord1 : servRecord) {
                            BT_URL = servRecord1.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
                            if (BT_URL != null) {
                                break;
                            }
                        }
                    }
                });
            } catch (InterruptedException | BluetoothStateException e) {
                System.out.println(e);
            }
        });

        BT_SCAN_THREAD.start();
    }//GEN-LAST:event_BT_SCAN_BTNActionPerformed

    private void BT_CONNECT_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_CONNECT_BTNActionPerformed
        if (BT_DEVICE_LIST.getSelectedIndex() == -1 || BT_DEVICE_LIST.getSelectedItem() == "NO DEVICE SELECTED") {
        } else {
            BT_CONNECT_THREAD = new Thread(() -> {
                String dd = BT_DEVICE_LIST.getSelectedItem().toString();
                BT_SPLIT_STRING = dd.split("::");
                BT_DEVICE_ADDRESS = BT_SPLIT_STRING[1];
                BT_URL = "btspp://" + BT_DEVICE_ADDRESS + ":1;authenticate=false;encrypt=false;master=false";
                if ("Disconnect".equals(BT_CONNECT_BTN.getText()) && BT_CONNECTED) {
                    try {
                        BT_OUT_STREAM.close();
                        BT_IN_STREAM.close();
                        BT_STREAM_CONNECTION.close();
                        BT_OUT_STREAM_OPEN = false;
                        BT_IN_STREAM_OPEN = false;
                        BT_CONNECTED = false;
                        lockScanning(true);
                        BT_CONNECT_BTN.setText("Connect");
                        lockScanning(false);
                    } catch (IOException e) {
                    }
                } else if (!BT_CONNECTED) {
                    try {
                        lockScanning(true);
                        BT_STREAM_CONNECTION = (StreamConnection) Connector.open(BT_URL);
                        BT_OUT_STREAM = BT_STREAM_CONNECTION.openOutputStream();
                        BT_IN_STREAM = BT_STREAM_CONNECTION.openInputStream();
                        BT_OUT_STREAM_OPEN = false;
                        BT_IN_STREAM_OPEN = false;
                    } catch (IOException e) {
                        BT_CONNECTED = false;
                        BT_OUT_STREAM_OPEN = false;
                        BT_IN_STREAM_OPEN = false;
                        lockScanning(false);
                        return;
                    }
                    BT_CONNECTED = true;
                    BT_CONNECT_BTN.setEnabled(true);
                }
                if (BT_CONNECTED) {
                    BT_CONNECT_BTN.setText("Disconnect");
                    BT_DEVICE_LIST.setEnabled(false);
                    BT_OUT_STREAM_OPEN = true;
                    BT_IN_STREAM_OPEN = true;
                    ReceiveData();
                } else {
                }

            });
            BT_CONNECT_THREAD.start();
        }
    }//GEN-LAST:event_BT_CONNECT_BTNActionPerformed

    private void BTN_ABOUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ABOUTActionPerformed
        JDialog dd = new about(this, true);
        dd.setVisible(true);
    }//GEN-LAST:event_BTN_ABOUTActionPerformed

    private void BT_REF_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_REF_BTNActionPerformed
        getRecentDevices();
    }//GEN-LAST:event_BT_REF_BTNActionPerformed

    private void BT_CONNECT_RD_BTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BT_CONNECT_RD_BTNActionPerformed
        if (BT_DEVICE_LIST_RECENT.getSelectedIndex() == -1 || BT_DEVICE_LIST_RECENT.getSelectedItem() == "NO DEVICE SELECTED") {
        } else {
            BT_CONNECT_THREAD = new Thread(() -> {
                String dd = BT_DEVICE_LIST_RECENT.getSelectedItem().toString();
                BT_SPLIT_STRING = dd.split("::");
                BT_DEVICE_ADDRESS = BT_SPLIT_STRING[1];
                BT_URL = "btspp://" + BT_DEVICE_ADDRESS + ":1;authenticate=false;encrypt=false;master=false";
                if ("Disconnect".equals(BT_CONNECT_RD_BTN.getText()) && BT_CONNECTED) {
                    try {
                        BT_OUT_STREAM.close();
                        BT_IN_STREAM.close();
                        BT_STREAM_CONNECTION.close();
                        BT_OUT_STREAM_OPEN = false;
                        BT_IN_STREAM_OPEN = false;
                        BT_CONNECTED = false;
                        lockScanning(true);
                        BT_CONNECT_RD_BTN.setText("Connect");
                        lockScanning(false);
                    } catch (IOException e) {
                    }
                } else if (!BT_CONNECTED) {
                    try {
                        lockScanning(true);
                        BT_STREAM_CONNECTION = (StreamConnection) Connector.open(BT_URL);
                        BT_OUT_STREAM = BT_STREAM_CONNECTION.openOutputStream();
                        BT_IN_STREAM = BT_STREAM_CONNECTION.openInputStream();
                        BT_OUT_STREAM_OPEN = false;
                        BT_IN_STREAM_OPEN = false;
                    } catch (IOException e) {
                        BT_CONNECTED = false;
                        BT_OUT_STREAM_OPEN = false;
                        BT_IN_STREAM_OPEN = false;
                        lockScanning(false);
                        return;
                    }
                    BT_CONNECTED = true;
                    BT_CONNECT_RD_BTN.setEnabled(true);
                }
                if (BT_CONNECTED) {
                    BT_CONNECT_RD_BTN.setText("Disconnect");
                    BT_DEVICE_LIST_RECENT.setEnabled(false);
                    BT_OUT_STREAM_OPEN = true;
                    BT_IN_STREAM_OPEN = true;
                    BT_DEVICE_LIST.setEnabled(false);
                    BT_SCAN_BTN.setEnabled(false);
                    BT_CONNECT_BTN.setEnabled(false);
                    BT_DEVICE_LIST.setEnabled(false);
                    BT_SCAN_BTN.setEnabled(false);
                    BT_CONNECT_BTN.setEnabled(false);
                    BTN_L1.setEnabled(true);
                    BTN_L2.setEnabled(true);
                    BTN_L3.setEnabled(true);
                    BTN_L4.setEnabled(true);
                    BTN_L5.setEnabled(true);
                    BTN_A_ON.setEnabled(true);
                    BTN_A_OFF.setEnabled(true);
                    ReceiveData();
                } else {
                }

            });
            BT_CONNECT_THREAD.start();
        }
    }//GEN-LAST:event_BT_CONNECT_RD_BTNActionPerformed

    private void BTN_L1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_L1ActionPerformed
        if (BTN_L1.isSelected()) {
            try {
                SendData("a");
            } catch (Exception e) {
            }
        } else {
            try {
                SendData("A");
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_BTN_L1ActionPerformed

    private void BTN_L2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_L2ActionPerformed
        if (BTN_L2.isSelected()) {
            try {
                SendData("b");
            } catch (Exception e) {
            }
        } else {
            try {
                SendData("B");
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_BTN_L2ActionPerformed

    private void BTN_L3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_L3ActionPerformed
        if (BTN_L3.isSelected()) {
            try {
                SendData("c");
            } catch (Exception e) {
            }
        } else {
            try {
                SendData("C");
            } catch (Exception e) {
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_L3ActionPerformed

    private void BTN_L4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_L4ActionPerformed
        if (BTN_L4.isSelected()) {
            try {
                SendData("d");
            } catch (Exception e) {
            }
        } else {
            try {
                SendData("D");
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_BTN_L4ActionPerformed

    private void BTN_L5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_L5ActionPerformed
        if (BTN_L5.isSelected()) {
            try {
                SendData("e");
            } catch (Exception e) {
            }
        } else {
            try {
                SendData("E");
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_BTN_L5ActionPerformed

    private void BTN_A_ONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_A_ONActionPerformed
        try {
            SendData("a");
            SendData("b");
            SendData("c");
            SendData("d");
            SendData("e");
            BTN_L1.setSelected(true);
            BTN_L2.setSelected(true);
            BTN_L3.setSelected(true);
            BTN_L4.setSelected(true);
            BTN_L5.setSelected(true);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_BTN_A_ONActionPerformed

    private void BTN_A_OFFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_A_OFFActionPerformed
        try {
            SendData("A");
            SendData("B");
            SendData("C");
            SendData("D");
            SendData("E");
            BTN_L1.setSelected(false);
            BTN_L2.setSelected(false);
            BTN_L3.setSelected(false);
            BTN_L4.setSelected(false);
            BTN_L5.setSelected(false);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_BTN_A_OFFActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new ui().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_ABOUT;
    private javax.swing.JButton BTN_A_OFF;
    private javax.swing.JButton BTN_A_ON;
    private javax.swing.JToggleButton BTN_L1;
    private javax.swing.JToggleButton BTN_L2;
    private javax.swing.JToggleButton BTN_L3;
    private javax.swing.JToggleButton BTN_L4;
    private javax.swing.JToggleButton BTN_L5;
    private javax.swing.JButton BT_CONNECT_BTN;
    private javax.swing.JButton BT_CONNECT_RD_BTN;
    private javax.swing.JComboBox<String> BT_DEVICE_LIST;
    private javax.swing.JComboBox<String> BT_DEVICE_LIST_RECENT;
    private javax.swing.JButton BT_REF_BTN;
    private javax.swing.JButton BT_SCAN_BTN;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to quit now ?", "Quit", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    private void lockScanning(boolean state) {
        if (state) {
            BT_DEVICE_LIST.setEnabled(false);
            BT_SCAN_BTN.setEnabled(false);
            BT_CONNECT_BTN.setEnabled(false);
            BT_DEVICE_LIST_RECENT.setEnabled(false);
            BT_CONNECT_RD_BTN.setEnabled(false);
            BT_REF_BTN.setEnabled(false);

            BTN_L1.setEnabled(false);
            BTN_L2.setEnabled(false);
            BTN_L3.setEnabled(false);
            BTN_L4.setEnabled(false);
            BTN_L5.setEnabled(false);
            BTN_A_ON.setEnabled(false);
            BTN_A_OFF.setEnabled(false);

        } else {
            BT_SCAN_BTN.setEnabled(true);
            BT_DEVICE_LIST.setEnabled(true);
            BT_CONNECT_BTN.setEnabled(true);
            BT_DEVICE_LIST_RECENT.setEnabled(true);
            BT_CONNECT_RD_BTN.setEnabled(true);
            BT_REF_BTN.setEnabled(true);
        }
    }

    private void SendData(String x) throws Exception {
        if (x == null || x.isEmpty()) {
            return;
        }
        if (BT_CONNECTED && BT_OUT_STREAM_OPEN) {
            BT_OUT_STREAM.write(x.getBytes());
            BT_OUT_STREAM.flush();
        }
    }

    private void ReceiveData() {

        BT_RECEIVE_THREAD = new Thread(() -> {
            byte[] BUFFER = new byte[8];
            if (BT_CONNECTED && BT_IN_STREAM_OPEN) {
                try {
                    while (BUFFER.length != 0 && BT_IN_STREAM_OPEN) {
                        BT_IN_STREAM.read(BUFFER);
                        for (byte b : BUFFER) {
                            RX_STRING = RX_STRING + (char) b;
                        }

                        //System.out.println(RX_STRING.trim());
                        RX_STRING = RX_STRING.trim();

                        if ("a".equals(RX_STRING)) {
                            BTN_L1.setSelected(true);
                        } else if ("A".equals(RX_STRING)) {
                            BTN_L1.setSelected(false);
                        }

                        if ("b".equals(RX_STRING)) {
                            BTN_L2.setSelected(true);
                        } else if ("B".equals(RX_STRING)) {
                            BTN_L2.setSelected(false);
                        }

                        if ("c".equals(RX_STRING)) {
                            BTN_L3.setSelected(true);
                        } else if ("C".equals(RX_STRING)) {
                            BTN_L3.setSelected(false);
                        }

                        if ("d".equals(RX_STRING)) {
                            BTN_L4.setSelected(true);
                        } else if ("D".equals(RX_STRING)) {
                            BTN_L4.setSelected(false);
                        }

                        if ("e".equals(RX_STRING)) {
                            BTN_L5.setSelected(true);
                        } else if ("E".equals(RX_STRING)) {
                            BTN_L5.setSelected(false);
                        }

                        RX_STRING = "";
                    }
                } catch (IOException ex) {

                }
            }
        });
        BT_RECEIVE_THREAD.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {

        }
    }

    private void getRecentDevices() {
        try {

            BT_DEVICE_LIST_RECENT.removeAllItems();
            Object obj = PARSER.parse(new FileReader(RECENT_DEVICE_LIST_FILE));
            JSONOBJ = (JSONObject) obj;
            RECENT_DEVICE_LIST = (JSONArray) JSONOBJ.get("RecentDevices");
            Iterator<String> iterator = RECENT_DEVICE_LIST.iterator();
            while (iterator.hasNext()) {
                BT_DEVICE_LIST_RECENT.addItem(iterator.next());
            }

        } catch (FileNotFoundException e) {
            BT_DEVICE_LIST_RECENT.addItem("NO DEVICE SELECTED");
        } catch (IOException | ParseException e) {
            BT_DEVICE_LIST_RECENT.addItem("NO DEVICE SELECTED");
        }
    }

    private void writeRecentDevicesList() {
        JSONOBJ.put("RecentDevices", RECENT_DEVICE_LIST);
        try {
            try (PrintWriter writer = new PrintWriter(RECENT_DEVICE_LIST_FILE)) {
                writer.print("");
            }
        } catch (FileNotFoundException ex) {
        }
        try {
            FileWriter file = new FileWriter(RECENT_DEVICE_LIST_FILE);
            file.write(JSONOBJ.toJSONString());
            file.flush();
        } catch (IOException e) {
        }
    }

}
