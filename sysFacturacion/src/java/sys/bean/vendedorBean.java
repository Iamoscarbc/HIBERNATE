/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sys.bean;

import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;
import sys.dao.vendedorDao;
import sys.imp.vendedorDaoImp;
import sys.model.Vendedor;

/**
 *
 * @author Sobrino
 */
@ManagedBean
@ViewScoped
public class vendedorBean {

    private List<Vendedor> listaVendedor;
    private Vendedor vendedor;
    
    public vendedorBean() {
        vendedor = new Vendedor();
    }

    public List<Vendedor> getListaVendedor() {
        vendedorDao vDao = new vendedorDaoImp();
        listaVendedor=vDao.listarVendedor();
        return listaVendedor;
    }

    public void setListaVendedor(List<Vendedor> listaVendedor) {
        this.listaVendedor = listaVendedor;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public void prepararNuevoVendedor(){
        vendedor = new Vendedor();
    }
    
    public void nuevoVendedor(){
        vendedorDao vDao = new vendedorDaoImp();
        vDao.newVendedor(vendedor);
    }
    
    public void modificarVendedor(){
        vendedorDao vDao = new vendedorDaoImp();
        vDao.updateVendedor(vendedor);
        vendedor = new Vendedor();
    }
    
    public void eliminarVendedor(){
        vendedorDao vDao = new vendedorDaoImp();
        vDao.deleteVendedor(vendedor);
        vendedor = new Vendedor();
    }
    
}
