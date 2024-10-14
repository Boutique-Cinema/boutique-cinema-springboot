package com.cinema.dto.reservation;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
  @NotNull
  @Size(min = 12, message = "영화 예매 번호는 최소 12자 이상이어야 합니다.")
  private String rNum; // 영화예매번호

  @NotNull
  @Size(max = 3, message = "좌석 번호는 최대 3자까지 입력할 수 있습니다.")
  private String seatNum; // 영화예매 좌석번호

  @NotNull
  @Min(value = 0, message = "결제 금액은 0 이상이어야 합니다.")
  private int paymentAmount; // 영화예매 결제금액

  private Integer isCanceled; // 영화예매 취소여부 (0: false, 1: true)

  @Size(max = 500, message = "관람 후기는 최대 500자까지 입력할 수 있습니다.")
  private String reviewContent; // 관람후기내용

  @Digits(integer = 2, fraction = 1, message = "관람 후기 평점은 0.0 이상 10.0 이하이어야 합니다.")
  @DecimalMin(value = "0.0", message = "관람 후기 평점은 최소 0.0 이상이어야 합니다.")
  @DecimalMax(value = "10.0", message = "관람 후기 평점은 10.0을 초과할 수 없습니다.")
  private BigDecimal reviewRating; // 관람후기평점

  @NotNull
  @Size(max = 3, message = "예매 인원 구분은 최대 3자까지 입력할 수 있습니다.")
  private String rPersonType1; // 예매인원1 구분

  @Size(max = 3)
  private String rPersonType2; // 예매인원2 구분

  @Size(max = 3)
  private String rPersonType3; // 예매인원3 구분

  @Size(max = 3)
  private String rPersonType4; // 예매인원4 구분

  @Size(max = 3)
  private String rPersonType5; // 예매인원5 구분

  @Size(max = 3)
  private String rPersonType6; // 예매인원6 구분

  @NotNull
  @Min(value = 0, message = "예매 인원 가격은 0 이상이어야 합니다.")
  private int rPersonPrice1; // 예매인원1 가격

  private int rPersonPrice2; // 예매인원2 가격
  private int rPersonPrice3; // 예매인원3 가격
  private int rPersonPrice4; // 예매인원4 가격
  private int rPersonPrice5; // 예매인원5 가격
  private int rPersonPrice6; // 예매인원6 가격

  @NotNull private Long movieNum; // 영화번호 (외래키)
}
