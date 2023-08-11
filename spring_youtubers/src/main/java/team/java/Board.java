package team.java;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity 
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bid;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content;
	
	@ColumnDefault("0")
	private int count;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userid")
	private User user; // DB는 오브젝트를 저장할 수 없다. FK가 만들어짐
	
	@OneToMany(mappedBy = "board", fetch=FetchType.EAGER) // mappedBy 연관관계의 주인이 아니다 (난 FK가 아니에요)
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
