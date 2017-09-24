package kz.maks.barter.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
@MappedSuperclass
public abstract class IdEntity {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
