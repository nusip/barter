package kz.maks.barter.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Maksat Nusipzhan
 * @version 2017-09-24
 */
@Entity
@Table(name = "users")
public class User extends IdEntity {
    @Column(unique = true)
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
