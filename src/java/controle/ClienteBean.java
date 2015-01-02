package controle;
 
import dao.ClienteDAO;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import model.Cliente;
 

@Named(value = "clienteBean")
@SessionScoped
@ManagedBean
public class ClienteBean implements Serializable {
 
    @EJB
    private ClienteDAO clienteDAO;
    
    private Cliente cliente = new Cliente();
    private List<Cliente> clientes;
 
    public void novo(){
        cliente = new Cliente();
    }
 
    public void gravar() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean resultado = clienteDAO.gravar(cliente);
 
        if (resultado) {
            cliente = new Cliente();
            context.addMessage(null, new FacesMessage("Cliente gravado com sucesso"));
        } else {
            context.addMessage(null, new FacesMessage("Falha ao gravar cliente!"));
        }
    }
 
    public void selecionar(ActionEvent evento) {
        Long codigo = (Long) evento.getComponent().getAttributes().get("codigo");
        cliente = clienteDAO.selecionar(codigo);
    }
 
    public void remover() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean resultado = clienteDAO.remover(cliente);
 
        if (resultado) {
            cliente = new Cliente();
            context.addMessage(null, new FacesMessage("Cliente removido com sucesso"));
        } else {
            context.addMessage(null, new FacesMessage("Falha ao remover cliente!"));
        }
    }
 
    //Getters e Setters
    public Cliente getCliente() {
        return cliente;
    }
 
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
 
    public List<Cliente> getClientes() {
        clientes = clienteDAO.listar();
        System.out.println("listando clientes");
        return clientes;
    }
 
    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}