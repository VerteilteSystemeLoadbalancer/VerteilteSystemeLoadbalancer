package de.dhbw.loadbalancer.gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class ClientGUI implements StringEvent {

	private JFrame frame;
	private JTextField inputField;
	private JTextField fieldResponse;

	private StringEvent stringEvent;

	public ClientGUI(StringEvent stringEvent) {
		this.stringEvent = stringEvent;
		initializeGui();
	}

	private void initializeGui() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("ClientGUI");
		frame.setBounds(100, 230, 600, 144);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		inputField = new JTextField();
		inputField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		inputField.setBounds(10, 57, 426, 35);
		frame.getContentPane().add(inputField);
		inputField.setColumns(10);

		JButton buttonSend = new JButton("SENDEN");
		buttonSend.setFont(new Font("Tahoma", Font.PLAIN, 20));
		buttonSend.setBounds(456, 57, 128, 35);
		buttonSend.addActionListener(e -> buttonSendClicked());
		frame.getContentPane().add(buttonSend);

		fieldResponse = new JTextField();
		fieldResponse.setEditable(false);
		fieldResponse.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fieldResponse.setBounds(10, 11, 574, 35);
		frame.getContentPane().add(fieldResponse);

		frame.setVisible(true);
	}

	private void buttonSendClicked() {
		fieldResponse.setText("");
		String text = inputField.getText();
		inputField.setText("");
		stringEvent.onEvent(text);
	}

	@Override
	public void onEvent(String data) {
		fieldResponse.setText(data);
	}

}
