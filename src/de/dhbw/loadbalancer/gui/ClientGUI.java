package de.dhbw.loadbalancer.gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ClientGUI implements StringEvent {

	private String name;

	public ClientGUI(String name, StringEvent inputEvent) {
		this.inputEvent = inputEvent;
		this.name = name;
	}

	private final StringEvent inputEvent;

	private JTextField inputField;
	private JTextField fieldResponse;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("ClientGUI " + name);
		frame.setBounds(100, 230, 1250, 144);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		inputField = new JTextField();
		inputField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		inputField.setBounds(10, 57, 1089, 35);
		frame.getContentPane().add(inputField);
		inputField.setColumns(10);

		JButton buttonSend = new JButton("SENDEN");
		buttonSend.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonSend.setBounds(1109, 57, 126, 35);
		buttonSend.addActionListener(e -> buttonSendClicked());
		frame.getContentPane().add(buttonSend);

		fieldResponse = new JTextField();
		fieldResponse.setEditable(false);
		fieldResponse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldResponse.setBounds(10, 11, 1225, 35);
		frame.getContentPane().add(fieldResponse);

		frame.setVisible(true);
	}

	private void buttonSendClicked() {
		fieldResponse.setText("");
		String text = inputField.getText();
		inputField.setText("");
		inputEvent.onEvent(text);
	}

	@Override
	public void onEvent(String data) {
		fieldResponse.setText(data);
	}

}
