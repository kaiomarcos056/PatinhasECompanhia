package Model;

public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double valor;
    private Integer quantidade;
    private String foto;
    private boolean servico;
    private Integer idMarca;
    private String marca;
    private Integer idCategoria;
    private String categoria;
    private Integer idEspecie;
    private String especie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isServico() {
        return servico;
    }

    public void setServico(boolean servico) {
        this.servico = servico;
    }

    public Integer getIdMarca() {
        return idMarca;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }
    
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getCategoria() {
        return categoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(Integer idEspecie) {
        this.idEspecie = idEspecie;
    }
    
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    
    public String getEspecie() {
        return especie;
    }
}
