package edu.idat.eventosvirtuales.entity;

import com.fasterxml.jackson.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(length = 20, nullable = false)
    private String nroDocIdentidad;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String apellidoPaterno;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String apellidoMaterno;

    @NotBlank
    @Column(length = 150, nullable = false)
    private String nombres;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date fechaNacimiento;

    @NotBlank
    @Column(length = 1, nullable = false)
    private String estado;

    private boolean eliminado;

    @OneToMany(mappedBy = "persona")
    private List<InscripcionEventoVirtual> inscripcionEventoVirtuals;

    @OneToMany(mappedBy = "persona")
    private List<Usuario> usuarios;

    @OneToOne
    private DocumentoAlmacenado fotoPerfil;

    public Persona() {
        this.id = 0L;
        this.nroDocIdentidad = "";
        this.apellidoPaterno = "";
        this.apellidoMaterno = "";
        this.nombres = "";
        this.fechaNacimiento = new Date();
        this.estado = "A";
        this.eliminado = false;
        this.usuarios = new ArrayList<>();
        this.inscripcionEventoVirtuals = new ArrayList<>();
    }

    public Persona(@NotBlank String nroDocIdentidad, @NotBlank String apellidoPaterno, @NotBlank String apellidoMaterno, @NotBlank String nombres, Date fechaNacimiento, List<Usuario> usuarios, List<InscripcionEventoVirtual> inscripcionEventoVirtuals, DocumentoAlmacenado fotoPerfil) {
        this.nroDocIdentidad = nroDocIdentidad;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.nombres = nombres;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = "A";
        this.eliminado = false;
        this.usuarios = usuarios;
        this.inscripcionEventoVirtuals = inscripcionEventoVirtuals;
        this.fotoPerfil = fotoPerfil;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNroDocIdentidad() {
        return nroDocIdentidad;
    }

    public void setNroDocIdentidad(String nroDocIdentidad) {
        this.nroDocIdentidad = nroDocIdentidad;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public List<InscripcionEventoVirtual> getInscripcionEventoVirtuals() {
        return inscripcionEventoVirtuals;
    }

    public void setInscripcionEventoVirtuals(List<InscripcionEventoVirtual> inscripcionEventoVirtuals) {
        this.inscripcionEventoVirtuals = inscripcionEventoVirtuals;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public DocumentoAlmacenado getFotoPerfil() {
        if (fotoPerfil == null) {
            fotoPerfil = new DocumentoAlmacenado();
        }
        return fotoPerfil;
    }

    public void setFotoPerfil(DocumentoAlmacenado fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
}
