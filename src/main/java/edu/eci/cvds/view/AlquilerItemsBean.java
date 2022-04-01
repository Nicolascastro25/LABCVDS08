package edu.eci.cvds.view;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.model.SelectableDataModel;

import java.util.List;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@ManagedBean(name = "AlquilerItemsBean")
@ApplicationScoped
public class AlquilerItemsBean extends BasePageBean implements SelectableDataModel<Cliente> {
    private List<Cliente> clientes = null;
    private Cliente selectedCliente;

    public AlquilerItemsBean() {
    }

    @Inject
    private ServiciosAlquiler serviciosAlquiler;

    public List<Cliente> consultarClientes() {
        try {
            clientes = serviciosAlquiler.consultarClientes();
        } catch (ExcepcionServiciosAlquiler e) {
            System.out.println(e.getMessage());
        }
        return clientes;
    }

    public void registrarCliente(long doc, String nombre, String telefono, String direccion, String mail) {
        try {
            Cliente cliente = new Cliente(nombre, doc, telefono, direccion, mail);
            serviciosAlquiler.registrarCliente(cliente);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void setSelectedCliente(Cliente cliente) {
        this.selectedCliente = cliente;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }


    public static java.util.Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public long consultarMultaAlquiler(int iditem) throws ExcepcionServiciosAlquiler {
        try {
            return serviciosAlquiler.consultarMultaAlquiler(iditem, new Date(System.currentTimeMillis()));
        } catch (ExcepcionServiciosAlquiler ex) {
            throw new ExcepcionServiciosAlquiler( "Error al consultar multa de item " + iditem);
        }

    }


    @Override
    public Object getRowKey(Cliente object) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Cliente getRowData(String rowKey) {
        // TODO Auto-generated method stub
        return null;
    }

    /*Cambiar a pagina de alquiler*/
    public String moveToPageRegistroAlquiler() {
        return "registroAlquilercliente";
    }
    /*Cambiar a pagina de clientes*/
    public String moveToPageRegistroCliente() {
        return "registrocliente";
    }

}