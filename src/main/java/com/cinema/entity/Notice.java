package com.cinema.entity;


import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@SequenceGenerator(name ="NOTICE_SEQ_GEN",
        sequenceName = "NOTICE_SEQ",
        initialValue = 1,
        allocationSize = 1)
@Table(name="NOTICE_TBL")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_SEQ_GEN")
    private Long nNum;

    @NotNull
    private String nTitle;
    @NotNull
    private String nContent;

    private LocalDate nDate;

    public void changeNTitle(String nTitle) {
        this.nTitle = nTitle;
    }
    public void changeNContent(String nContent) {
        this.nContent = nContent;
    }
    public void changeNDate(LocalDate nDate) {
        this.nDate = nDate;
    }
}
