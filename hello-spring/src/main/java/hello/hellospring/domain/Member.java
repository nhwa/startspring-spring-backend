package hello.hellospring.domain;


import javax.persistence.*;

@Entity
public class Member {
    //DB에서 자동으로 생성 => Identity 전략 (ex: 오라클에서는 sequence 또는 쿼리에서 생성되게 )
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //컬럼 이름이 유저네임이면 @Column 아노테이션 만들어줘야함.
//    @Column(name="username")
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
