package team.java;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor 
@ToString(exclude = {"writer"})

public class Youtuber {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long yno;
	private String name;
	private String title;
	private String content;	
	private int likes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Member writer;  // Member와 연관관계 지정
	
	
    @OneToMany(mappedBy = "youtuber")
    private List<PostYoutuber> postYoutubers = new ArrayList<>();

	
    
}

