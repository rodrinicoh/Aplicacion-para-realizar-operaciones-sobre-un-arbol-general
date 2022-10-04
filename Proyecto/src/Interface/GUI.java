package Interface;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Auxiliar.ArbolVacioException;
import Auxiliar.PosicionInvalidaException;
import Auxiliar.RotuloInvalidoException;
import Auxiliar.RotuloNoPerteneceAlArbolException;
import Auxiliar.YaExisteRaizException;
import Logica.*;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private JPanel contentPane;
	private Logica log;
	
	private JButton BotonCargar;
	private JButton BotonAgregarNodo;
	private JButton BotonPreorden;
	private JButton BotonPosOrden;
	private JButton BotonPorNiveles;
	private JButton BotonAncestroComun;
	private JButton BotonEspejo;
	private JButton BotonMapeo;
	private JPanel panelBotones;
	private JPanel panelTexto;
	private JTextArea Texto;
	private JScrollPane Barra;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 500, 500);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelBotones= new JPanel();
		panelBotones.setBounds(5, 5, 484, 141);
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setVgap(15);
		flowLayout.setHgap(15);
		contentPane.add(panelBotones);
		
		panelTexto = new JPanel();
		panelTexto.setBounds(5, 147, 484, 305);
		panelTexto.setLayout(new BoxLayout(panelTexto, BoxLayout.X_AXIS));
		contentPane.add(panelTexto);

		
		Texto = new JTextArea();
		Texto.setBackground(Color.WHITE);
		Texto.setBorder(BorderFactory.createLineBorder(Color.black,5));
		Texto.setEditable(false);
		Barra = new JScrollPane(Texto);;
		panelTexto.add(Barra);
		Texto.setColumns(10);
		
		BotonCargar = new JButton("<html> Cargar un<br /> <center> arbol <center /></html>");
		BotonCargar.addActionListener(new OyenteCrearArbol());
		panelBotones.add(BotonCargar);
		
		BotonAgregarNodo = new JButton("<html>Agregar<br/><center> nodo<center/></html>");
		BotonAgregarNodo.addActionListener(new OyenteAgregarNodo());
		panelBotones.add(BotonAgregarNodo);
		
		BotonPreorden = new JButton("<html>Mostrar en<br/><center> preorden<center/></html>");
		BotonPreorden.addActionListener(new OyentePreorden());
		panelBotones.add(BotonPreorden);
		
		BotonPosOrden = new JButton("<html>Mostrar en<br/><center> posorden<center/></html>");
		BotonPosOrden.addActionListener(new OyentePosOrden());
		panelBotones.add(BotonPosOrden);
		
		BotonPorNiveles = new JButton("<html>Mostrar por<br/><center> niveles<center/></html>");
		BotonPorNiveles.addActionListener(new OyentePorNiveles());
		panelBotones.add(BotonPorNiveles);
		
		BotonAncestroComun = new JButton("<html>Ancestro comun<br/><center> mas cercano<center/></html>");
		BotonAncestroComun.addActionListener(new OyenteAncestroComunMasCercano());
		panelBotones.add(BotonAncestroComun);
		
		BotonEspejo = new JButton("<html>Crear<br/><center>Espejo<center/></html>");
		BotonEspejo.addActionListener(new OyenteEspejo());
		panelBotones.add(BotonEspejo);
		
		BotonMapeo = new JButton("<html>Obtener<br/><center>Mapeo<center/></html>");
		BotonMapeo.addActionListener(new OyenteMapeo());
		panelBotones.add(BotonMapeo);
		
		ModificarEstadoTodoLosBotonesContrarioCargar(false);
		
		
		

	}
	
	
	//Oyentes
	class OyenteCrearArbol implements ActionListener {
		public void actionPerformed(ActionEvent event){
			try {
				JTextField CampoRaiz = new JTextField();
				Object[] ventana = {"Ingrese un rotulo entero para la raiz del arbol:", CampoRaiz};
				int option = JOptionPane.showConfirmDialog(null, ventana,"Crear Nodo", JOptionPane.OK_CANCEL_OPTION);
				
				if(option == JOptionPane.OK_OPTION) {
					String r = CampoRaiz.getText();
					int rotulo = CastearStringAInt(r);
					log = new Logica();
					log.CargarArbol(rotulo);
				
					ModificarEstadoTodoLosBotonesContrarioCargar(true);
				}
			} catch (RotuloInvalidoException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			} catch (YaExisteRaizException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class OyenteAgregarNodo implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				JTextField CampoRotulo = new JTextField();
				JTextField CampoPadre = new JTextField();
				Object[] ventana = {"Ingrese el padre del nuevo nodo:", CampoPadre, "Ingrese el rotulo del nuevo nodo:", CampoRotulo};
				
				int option = JOptionPane.showConfirmDialog(null, ventana,"Agregar Nodo", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String p = CampoPadre.getText();
					String r = CampoRotulo.getText();
					int padre = CastearStringAInt(p);
					int rotulo = CastearStringAInt(r);
					log.AgregarNodo(rotulo, padre);
				}
			
			} catch (RotuloInvalidoException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			} catch (RotuloNoPerteneceAlArbolException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			} catch (ArbolVacioException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	class OyentePreorden implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				String preO = log.preOrden();
				Texto.setText(preO);
			} catch (ArbolVacioException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			} catch (PosicionInvalidaException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class OyentePosOrden implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				String posO = log.posOrden();
				Texto.setText(posO);
			} catch (ArbolVacioException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			} catch (PosicionInvalidaException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class OyentePorNiveles implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				String niv = log.porNiveles();
				Texto.setText(niv);
			
			} catch (ArbolVacioException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			} catch (PosicionInvalidaException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class OyenteAncestroComunMasCercano implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				JTextField CampoRotulo1 = new JTextField();
				JTextField CampoRotulo2 = new JTextField();
				Object[] ventana = {"Ingrese el rotulo del primer nodo:", CampoRotulo1, "Ingrese el rotulo del segundo nodo:", CampoRotulo2};
				
				int option = JOptionPane.showConfirmDialog(null, ventana,"Ancestro Comun mas Cercano", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String r1 = CampoRotulo1.getText();
					String r2 = CampoRotulo2.getText();
					int rotulo1 = CastearStringAInt(r1);
					int rotulo2 = CastearStringAInt(r2);
					String s ="" + log.AncestroComunMasCercano(rotulo1, rotulo2);
					Texto.setText(s);
				}
				
			} catch (RotuloInvalidoException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			} catch (RotuloNoPerteneceAlArbolException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			} catch (PosicionInvalidaException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			} catch (ArbolVacioException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class OyenteEspejo implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try {
				
				JTextField CampoMult = new JTextField();
				Object[] ventana = {"Ingrese un multiplicativo:", CampoMult};
				int option = JOptionPane.showConfirmDialog(null, ventana,"Crear Espejo Multiplicativo", JOptionPane.OK_CANCEL_OPTION);
				
				if(option == JOptionPane.OK_OPTION) {
					String m = CampoMult.getText();
					int mult = CastearStringAInt(m);
					Texto.setText(log.CrearEspejo(mult));
				}
				
			} catch (ArbolVacioException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			} catch (RotuloInvalidoException e) {
				JOptionPane.showMessageDialog(null,"El entero multiplicativo ingresado no es valido","Error",JOptionPane.ERROR_MESSAGE);
			} catch (PosicionInvalidaException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class OyenteMapeo implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			try{
				String niv = log.Mapeo();
				Texto.setText(niv);
			} catch(PosicionInvalidaException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	//Metodo Auxiliar
	private void ModificarEstadoTodoLosBotonesContrarioCargar(boolean e) {
		BotonCargar.setEnabled(!e);
		BotonAgregarNodo.setEnabled(e);
		BotonPreorden.setEnabled(e);
		BotonPosOrden.setEnabled(e);
		BotonPorNiveles.setEnabled(e);
		BotonAncestroComun.setEnabled(e);
		BotonEspejo.setEnabled(e);
		BotonMapeo.setEnabled(e);
	}
	
	private int CastearStringAInt(String r) throws RotuloInvalidoException{
		try {
			return Integer.parseInt(r);
		
		} catch (NumberFormatException e) {
			throw new RotuloInvalidoException("Error: El rotulo " +r+ " no es un entero");
		}
	}
}
