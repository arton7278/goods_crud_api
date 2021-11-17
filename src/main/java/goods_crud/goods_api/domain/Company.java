package goods_crud.goods_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Id
    @GeneratedValue
    @Column(name="com_id")
    private Long comId;
    private String comNm;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Goods> goodsList = new ArrayList<>();

    //등록시간
    private LocalDateTime regDm;
    //등록자 Id
    private String regUserId;
    //수정 시간
    private LocalDateTime updDm;
    //수정 Id
    private String updUserId;

    @Builder
    public Company (String comNm, String regUserId, String updUserId) {
        this.comNm = comNm;
        this.regUserId = regUserId;
        this.updUserId = updUserId;
        this.regDm = LocalDateTime.now();
        this.updDm = LocalDateTime.now();
    }
}
