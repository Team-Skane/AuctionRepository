package skane.skaneshop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_number")
  private Long userNumber;

  private String id;

  private String password;

  private String name;

  private int age;

  @Enumerated(EnumType.STRING)
  private ManagementStatus managementStatus;

  @Enumerated(EnumType.STRING)
  private Gender gender; // (man,woman)

}
