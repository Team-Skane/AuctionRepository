package skane.skaneshop.domain;

import lombok.Generated;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Admin {
  
  @Id @Generated
  @Column(name = "admin_id")
  private Long adminNumber;

  private String id;

  private String password;

  @Enumerated(EnumType.STRING)
  private ManagementStatus managementStatus;
}
