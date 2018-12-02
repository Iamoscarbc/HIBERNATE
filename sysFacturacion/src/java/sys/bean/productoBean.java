package sys.bean;

import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import sys.dao.productoDao;
import sys.imp.productoDaoImp;
import sys.model.Producto;

@ManagedBean
@ViewScoped
public class productoBean {
    
    private List<Producto> listaProductos;
    private Producto producto;
        
    public productoBean() {
    }

    public List<Producto> getListaProductos() {
        productoDao cDao = new productoDaoImp();
        listaProductos=cDao.listarProductos();
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public void prepararNuevoProducto(){
        producto = new Producto();
    }
    
    public void nuevoProducto(){
        productoDao cDao = new productoDaoImp();
        cDao.newProducto(producto);
    }
    
    public void modificarProducto(){
        productoDao cDao = new productoDaoImp();
        cDao.updateProducto(producto);
        producto = new Producto();
    }
    
    public void eliminarProducto(){
        productoDao cDao = new productoDaoImp();
        cDao.deleteProducto(producto);
        producto = new Producto();
    }
    
}
