package dao;
 
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Cliente;

 
@Stateless
public class ClienteDAO {
 
    @PersistenceContext(unitName="TesteJPAPU")
    private EntityManager em;
 
    public boolean gravar(Cliente cliente){
        boolean sucesso = false;
        try {
            em.merge(cliente);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return sucesso;
    }
 
    public Cliente selecionar(Long codigo){
        Cliente cliente = null;
        try {
            cliente = em.find(Cliente.class, codigo);
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return cliente;
    }
 
    public boolean remover(Cliente cliente){
        boolean sucesso = false;
        try {
            cliente = em.find(Cliente.class, cliente.getCodigo());
            em.remove(cliente);
            sucesso = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return sucesso;
    }
 
    public List<Cliente> listar() {
        List<Cliente> clientes = null;
        try {
            Query query = em.createQuery("Select c from Cliente c");
            clientes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
         
        return clientes;
    }
}