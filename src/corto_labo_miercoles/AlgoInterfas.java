/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corto_labo_miercoles;

import Pelicula.DaoPelicula;
import Pelicula.Pelicula;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LN710Q
 */
public class AlgoInterfas extends JFrame{
    
    public JLabel lblnombre,lbldirector,lblpais,lblclasificacion,lblaño,lblproyeccion;
    
    public JTextField nombre,director,pais,año;//cajas de entrada de texto
    public JComboBox clasificacion;     //menu  de marcas
    
    ButtonGroup enproy=new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si; 
    
    public JTable resultados;//tabla
    
    public JPanel table;//contenedor
    
    public JButton buscar,eliminar,insertar,actualizar;
    
    private static final int ANCHOC=130,ALTOC=30; 
    
    DefaultTableModel tm;   //tabla 
    
    public AlgoInterfas(){
        super("cinepolix");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();//////////////////////////
        formulario();//////////////////////////////
        llenarTabla();///////////////////////////
        Container container =getContentPane();
        container.add(lblnombre);
        container.add(lbldirector);
        container.add(lblpais);
        container.add(lblclasificacion);
        container.add(lblaño);
        container.add(lblproyeccion);
        container.add(nombre);
        container.add(director);
        container.add(pais);
        container.add(año);
        container.add(clasificacion);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        
        container.add(table);
        setSize(650,650);
        eventos();
        
    }
    
    public final void agregarLabels(){//labels
        lblnombre = new JLabel("Nombre: ");
        lbldirector = new JLabel("Director:");
        lblpais = new JLabel ("Pais:");
        lblclasificacion = new JLabel("Clasificacion:");
        lblaño= new JLabel("Año:");
        lblproyeccion= new JLabel("En proyeccion:");
        lblnombre.setBounds(10,10, ANCHOC, ALTOC);
        lbldirector.setBounds(10,60, ANCHOC, ALTOC);
        lblpais.setBounds(10,100, ANCHOC, ALTOC);
        lblclasificacion.setBounds(10,140, ANCHOC, ALTOC);
        lblproyeccion.setBounds(310,140,ANCHOC,ALTOC);
        lblaño.setBounds(310,60, WIDTH, HEIGHT);
    }
    
    public final void formulario(){
        nombre =new JTextField();
        director =new JTextField();
        pais =new JTextField();
        año =new JTextField();
        clasificacion=new JComboBox();
        si=new JRadioButton("si",true);//botones de opcion
        no = new JRadioButton("no");
        
        resultados=new JTable();//tabla
        
        buscar = new JButton ("buscar");
        insertar = new JButton("insertar");
        eliminar = new JButton("eliminar");
        actualizar = new JButton("actualizar");
        
        table =new JPanel();//lugar donde se almacena algo
        
        clasificacion.addItem("G");
        clasificacion.addItem("PG");
        clasificacion.addItem("14A");
        clasificacion.addItem("18A");
        clasificacion.addItem("R");
        clasificacion.addItem("A");
        
        enproy=new ButtonGroup();
        
        enproy.add(si);
        enproy.add(no);
        
        
        nombre.setBounds(140,10,ANCHOC,ALTOC);
        director.setBounds(140,60,ANCHOC,ALTOC);
        pais.setBounds(140,100,ANCHOC,ALTOC);
        clasificacion.setBounds(140,150,ANCHOC,ALTOC);
        año.setBounds(400,60,ANCHOC,ALTOC);
        si.setBounds(400,140,50,ALTOC);
        no.setBounds(450,140,50,ALTOC);
        
        
        buscar.setBounds(300,10,ANCHOC,ALTOC);
        insertar.setBounds(10,210,ANCHOC,ALTOC);
        actualizar.setBounds(150,210,ANCHOC,ALTOC);
        eliminar.setBounds(300,210,ANCHOC,ALTOC);
        resultados=new JTable();
        
        table.setBounds(30, 250,500, 300);
        table.add(new JScrollPane(resultados));
    }
    
    
    public void llenarTabla(){
        tm = new DefaultTableModel(){

    public Class<?> getColumnClass(int column){
            switch(column){
                case 0:
                    return String.class;
                case 1:
                    return String.class;
                case 2:
                    return String.class;
                case 3:
                    return String.class;
                case 4:
                    return String.class;
                default:
                    return Boolean.class;
                    
            }
        }
        };
        tm.addColumn("nombre");
        tm.addColumn("director");
        tm.addColumn("pais");
        tm.addColumn("clasificacion");
        tm.addColumn("año");
        tm.addColumn("en proyeccion");
        
        DaoPelicula fd = new DaoPelicula();
        ArrayList<Pelicula> peli=fd.readAll();
        
        for(Pelicula f1:peli){
            tm.addRow(new Object[]{
                f1.getNombre(),f1.getDirector(),f1.getPais(),f1.getClasificacion(),f1.getAño(),f1.getProyeccion()});
        }
        resultados.setModel(tm);
    }
    
    public void eventos(){
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                DaoPelicula fd = new DaoPelicula();
                Pelicula f = new Pelicula(nombre.getText(),director.getText(),pais.getText(),clasificacion.getSelectedItem().toString(),Integer.parseInt(año.getText()),true);

                if(no.isSelected()){
                    f.setProyeccion(false);
                }
                if(fd.read(f.getNombre())==null){
                    if(fd.create(f)){
                        JOptionPane.showMessageDialog(null, "filtro registrado con exito");

                        llenarTabla();
                    }else{
                        JOptionPane.showMessageDialog(null,"ocurrio un problema al momento de llenar el filtro");
                    }
                }
            
            }
        });
      actualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                DaoPelicula fd =new DaoPelicula();
                Pelicula f = new Pelicula (nombre.getText(),true);
                if(no.isSelected()){
                    f.setProyeccion(false);
                }
                if(fd.read(f.getNombre())!=null){
                    if(fd.update(f)){
                        JOptionPane.showMessageDialog(null, "filtro modificado con exito");
                        llenarTabla();
                    }else{
                        System.out.println("entor");
                       JOptionPane.showMessageDialog(null,"ocurrio un problema al momento de modificar el filtro"); 
                    }
                }
            }
          
      });
        
      eliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                DaoPelicula fd=new DaoPelicula();
                if(fd.delete(nombre.getText())){
                    JOptionPane.showMessageDialog(null,"filtro eliminado con exito");
                  
                    llenarTabla();
                }else{
                    JOptionPane.showMessageDialog(null,"ocurrio un problema al momento de eliminar el filtro");
                }
            }
          
      });
    
      buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                DaoPelicula fd=new DaoPelicula();
                Pelicula  f = fd.read(nombre.getText());
                if(f==null){
                    JOptionPane.showMessageDialog(null,"el filtro no se ha encontrado");
                   
                }else{
                    nombre.setText(f.getNombre());
                    director.setText(f.getDirector());
                    año.setText(String.valueOf(f.getAño()));
                    pais.setText(f.getPais());
                    clasificacion.setSelectedItem(f.getClasificacion());
                   
                    
                    
                    
                    if(f.getProyeccion()){
                    si.setSelected(true);
                }else{
                        no.setSelected(false);
                    
                }
            }
            } 
      });
      
      }
    public static void main(String[] args){
       java.awt.EventQueue.invokeLater(new Runnable() {
           @Override
           public void run() {
               new AlgoInterfas().setVisible(true);           }
       });{
       
   }
   }

}
    
     

