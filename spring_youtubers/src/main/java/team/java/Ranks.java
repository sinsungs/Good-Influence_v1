package team.java;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity // 테이블 생성 효과
public class Ranks {
	
	@Id // PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement 
	private Long rankid;
	
	private String rankname;
	
	private Long activityscore;
	
}
