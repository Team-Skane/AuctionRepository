package skane.skaneshop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
// 잠깐 사용
@AllArgsConstructor
@NoArgsConstructor
public class PostBoard {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "post_board_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "prouct_id")
  private Product productNumber;

  @Column(name = "post_name")
  private String name;

  @Column(name = "post_text")
  private String text;

  @Enumerated(EnumType.STRING)
  private Status status;

  private Timestamp time;
}
