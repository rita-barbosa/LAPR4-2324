package jobs4u.base.applicationmanagement.domain;


import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.*;

import java.io.File;
import java.util.Objects;

@Entity
@Table(name = "APPLICATION_FILE")
public class ApplicationFile implements ValueObject {

    private static final long serialVersionUID = 1L;
    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationFileId;

    @Column(nullable = false)
    private File file;


    public ApplicationFile(File file) {
        this.file = file;
    }

    protected ApplicationFile(){
        //for ORM
    }


    public static ApplicationFile valueOf(final File file){
        return new ApplicationFile(file);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationFile)) return false;
        ApplicationFile that = (ApplicationFile) o;
        return Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(file);
    }

    @Override
    public String toString(){
        return String.valueOf(file);
    }

    public File getApplicationFile(){
        return file;
    }
}
