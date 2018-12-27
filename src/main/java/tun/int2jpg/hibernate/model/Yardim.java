package tun.int2jpg.hibernate.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "YARDIM")
@NamedQueries({@NamedQuery(name = "Yardim.findById", query = "SELECT y FROM Yardim y WHERE y.id = :id"),
               @NamedQuery(name = "Yardim.findAll", query = "SELECT y FROM Yardim y")})
public class Yardim {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "Yardim_Seq_Gen")
    @SequenceGenerator(name = "Yardim_Seq_Gen", sequenceName = "S_YARDIM")
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 19, scale = 0)
    private Long id;
    @Basic(optional = true)
    @Column(name = "DOSYA_ID", nullable = true, precision = 19, scale = 0)
    private Long dosyaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BASLIK", nullable = false, length = 200)
    private String baslik;
    @NotNull
    @Column(name = "ICERIK", length = 10485760)
    private String icerik;
    @NotNull
    @Column(name = "REFERANS_SAYFA", length = 10485760)
    private String referansSayfa;
    @Basic(optional = false)
    @Column(name = "AKTIF", nullable = false)
    private boolean aktif;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDosyaId() {
        return dosyaId;
    }

    public void setDosyaId(Long dosyaId) {
        this.dosyaId = dosyaId;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public String getReferansSayfa() {
        return referansSayfa;
    }

    public void setReferansSayfa(String referansSayfa) {
        this.referansSayfa = referansSayfa;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void setAktif(boolean aktif) {
        this.aktif = aktif;
    }
}