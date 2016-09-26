package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.Sql;
import com.avaje.ebean.annotation.SqlSelect;
import com.avaje.ebean.annotation.UpdatedTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Created by ishida on 2016/09/23.
 */

@Entity
@Table(name="application_tokens")
@Sql
public class ApplicationTokens extends Model {

    @CreatedTimestamp
    @Column(name = "created_at")
    public Timestamp createdAt;

    @UpdatedTimestamp
    @Column(name = "updated_at")
    public Timestamp updatedAt;

    @Id
    @Column(name = "id")
    public UUID id;

    @Column(name = "deleted")
    public boolean deleted;

    @Column(name = "label", columnDefinition = "varchar(256) not null")
    public String label;

    @Column(name = "access_token", columnDefinition = "varchar(256) not null")
    public String accessToken;

    @Column(name = "secret_token", columnDefinition = "varchar(256) not null")
    public String secretToken;

    @OneToOne(mappedBy = "applications")
    @Column(name = "application_id")
    public UUID application_id;

    public static Finder<UUID, ApplicationTokens> find = new Finder<>(
            UUID.class, ApplicationTokens.class
    );
}
