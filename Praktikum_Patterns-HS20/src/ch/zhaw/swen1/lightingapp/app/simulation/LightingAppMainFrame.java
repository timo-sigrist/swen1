package ch.zhaw.swen1.lightingapp.app.simulation;

import ch.zhaw.swen1.lightingapp.domain.*;
import ch.zhaw.swen1.lightingapp.ui.swing.JLampComponent;
import ch.zhaw.swen1.lightingapp.ui.swing.JRotarySwitchComponent;
import ch.zhaw.swen1.lightingapp.ui.swing.Lamp;
import ch.zhaw.swen1.lightingapp.ui.swing.Switch;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Swing main frame of simulation variant of the lighting application. 
 * @author fer
 *
 */
public class LightingAppMainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel lampPanel;
	private JPanel rotarySwitchPanel;
	private List<LampControlMode> availableControlModes = new ArrayList<>(); 
	private LampControl lampControl;

	/**
	 * Create the frame.
	 */
	public LightingAppMainFrame() {
		setTitle("Lampensteuerung");
		setBackground(Color.RED);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 250, 360, 300);
		// simple layout for simulation purposes
		lampPanel = new JPanel();
		lampPanel.setLayout(new BoxLayout(lampPanel, BoxLayout.LINE_AXIS));		
		rotarySwitchPanel = new JPanel();
		rotarySwitchPanel.setLayout(new BoxLayout(rotarySwitchPanel, BoxLayout.LINE_AXIS));		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 5, 10, 5));		
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));	
		contentPane.add(lampPanel);		
		lampPanel.setAlignmentX(Component.CENTER_ALIGNMENT); 
		contentPane.add(lampPanel);
		lampPanel.setBorder(BorderFactory.createTitledBorder("Lampen"));
		contentPane.add(Box.createVerticalStrut(10));
		rotarySwitchPanel.setAlignmentX(Component.CENTER_ALIGNMENT); 
		rotarySwitchPanel.setBorder(BorderFactory.createTitledBorder("Schalter"));
		contentPane.add(rotarySwitchPanel);
		setContentPane(contentPane);
		lampControl = new LampControl();
		createAndAddAvailableLampControlModes();
		initializeLampsAndRotarySwitches();
		lampPanel.add(Box.createHorizontalStrut(12));		
		rotarySwitchPanel.add(Box.createHorizontalStrut(12));
	}
	
	private void createAndAddAvailableLampControlModes() {
		availableControlModes.add(new LampControlModeForWorkplace());
		availableControlModes.add(new LampControlModeForHome());
		availableControlModes.add(new LampControlModeForStudy());
	}

	private void initializeLampsAndRotarySwitches() {
		Lamp lampLivingRoom1 = new JLampComponent("Wohnzimmer 1");
		addLamp(lampLivingRoom1);		
		Lamp lampLivingRoom2 = new JLampComponent("Wohnzimmer 2");
		addLamp(lampLivingRoom2);		
		Lamp lampBathRoom = new JLampComponent("Badezimmer");
		addLamp(lampBathRoom);		
		List<Lamp> lampsForRotarySwitchEntrance = new ArrayList<>();
		lampsForRotarySwitchEntrance.add(lampLivingRoom1);
		lampsForRotarySwitchEntrance.add(lampLivingRoom2);
		addRotarySwitch(new JRotarySwitchComponent("Eingang"), lampsForRotarySwitchEntrance);		
		List<Lamp> lampsForRotarySwitchBathRoom = new ArrayList<>();
		lampsForRotarySwitchBathRoom.add(lampBathRoom);
		addRotarySwitch(new JRotarySwitchComponent("Badezimmer"), lampsForRotarySwitchBathRoom);		
	}

	private void addLamp(Lamp lamp){
		lamp.setAlignmentX(Component.CENTER_ALIGNMENT); 
		JPanel lampWithName = new JPanel();
		lampWithName.setLayout(new BoxLayout(lampWithName, BoxLayout.PAGE_AXIS));	
		JLabel label = new JLabel(lamp.getName());
		label.setAlignmentX(Component.CENTER_ALIGNMENT); 
		lampWithName.add(label);
		lampWithName.add(lamp);
		lampPanel.add(Box.createHorizontalStrut(12));
		lampPanel.add(lampWithName);
		addLampToDomainLogic(lamp);
	}
		
	private void addLampToDomainLogic(Lamp lamp) {
		lampControl.addLamp(lamp); 
	}

	private void addRotarySwitch(Switch rotarySwitch, List<Lamp> lamps){
		rotarySwitch.setAlignmentX(Component.CENTER_ALIGNMENT); 
		JPanel rotarySwitchWithName = new JPanel();
		rotarySwitchWithName.setLayout(new BoxLayout(rotarySwitchWithName, BoxLayout.PAGE_AXIS));	
		JLabel label = new JLabel(rotarySwitch.getName());
		label.setAlignmentX(Component.CENTER_ALIGNMENT); 
		rotarySwitchWithName.add(label);
		rotarySwitchWithName.add(rotarySwitch);
		JComboBox<LampControlMode> comboBox = new JComboBox<LampControlMode>();
		availableControlModes.forEach((strategy)->comboBox.addItem(strategy));
		comboBox.addActionListener((e) -> lampControl.setControlModeForSwitch(rotarySwitch, (LampControlMode) comboBox.getSelectedItem()));
		if (!availableControlModes.isEmpty()){
			lampControl.setControlModeForSwitch(rotarySwitch, availableControlModes.get(0)); 
		}
		rotarySwitchWithName.add(comboBox);
		rotarySwitchPanel.add(Box.createHorizontalStrut(12));
		rotarySwitchPanel.add(rotarySwitchWithName);
		lampControl.addRotarySwitch(rotarySwitch, lamps); 
	}
		
	public static void main(String[] args) throws InterruptedException {
		LightingAppMainFrame frame = new LightingAppMainFrame();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
