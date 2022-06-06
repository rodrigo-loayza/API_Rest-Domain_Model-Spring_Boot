package com.nostop.market.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "productos") //table annotation, as class name is not the same as table name in db
public class Producto {

    //---Attributes---

    //PK annotation
    @Id
    //since the PK is automatically generated, this annotation tells java to generate it too
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //column annotation, as attribute name is not the same as column name in db
    @Column(name = "id_producto")
    private Integer idProducto;

    private String nombre; //no different name, no column annotation

    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "codigo_barras")
    private String codigoBarras;

    @Column(name = "precio_venta")
    private Double precioVenta;

    @Column(name = "cantidad_stock")
    private Integer cantidadStock;

    private Boolean estado;

    //General note: JPA uses Java variable classes (i.e. not int, Integer)

    //---Relations---

    // Relation of "Product" with "Categoria"
    @ManyToOne
    // Join column resulting from the relation
    // Insertable and updatable false means that it won't be possible to insert or update
    // any data from "Producto", this must be done from "Categoria". This is just and indicator.
    @JoinColumn(name = "id_categoria", insertable = false, updatable = false)
    private Categoria categoria;

    // Relation of "Product" with "ComprasProducto"
    @OneToMany(mappedBy = "producto")
    private List<ComprasProducto> compras;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(Double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Integer getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(Integer cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<ComprasProducto> getCompras() {
        return compras;
    }

    public void setCompras(List<ComprasProducto> compras) {
        this.compras = compras;
    }
}
