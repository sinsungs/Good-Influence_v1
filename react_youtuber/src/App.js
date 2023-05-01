
import './App.css';
import './css/style.css';
import React, {useState, useEffect} from 'react';
import $ from "jquery";
import "jquery-ui/ui/widgets/datepicker.js";
import logo from './logo.svg';
import axios from 'axios';

function App() {

	useEffect(() => {
	  // Planner event------------------------------------------------------------
	 // div 요소에 datepicker 설정
	 $("#datepickerDiv").click(function() {
		// Datepicker가 표시되어 있는지 체크
		if ($(".ui-datepicker").is(":visible")) {
		  // 표시되어 있다면 숨김
		  $("#datepickerDiv").text("달력 보기");
		  $("#datepickerUI").hide();
		} else {
		  // 표시되어 있지 않다면 표시
		  $("#datepickerDiv").text("달력 숨기기");
		  $("#datepickerUI").show();
		}

		$("#datepickerUI").datepicker({
			onSelect: function(dateText, inst) {
			  // 선택한 날짜를 input 요소에 설정
			  $("#selected-date-input").val(dateText);
			},
			dateFormat: "yy-mm-dd" // 날짜 형식 설정
		});
	  });

		// "할일 추가" 버튼 클릭 시, 모달 창 보이기
		$('#addTaskBtn').click(function() {
			$('#addTaskModal').css('display', 'flex');
		});

		// "닫기" 버튼 클릭 시, 모달 창 숨기기
		$('#closeModalBtn').click(function() {
			$('#addTaskModal').css('display', 'none');
		});

		// 할일 추가 폼 제출 시, 모달 창 숨기기
		$('#addTaskForm').submit(function(event) {
			event.preventDefault();
			$('#addTaskModal').css('display', 'none');
			// 추가할일 처리 로직 작성
		});

		$(".planner_inputArea > div > ul > li").click(function (){
			let id = $(this).attr("class");
			id = id.substr(8,id.length-1);

			let title = $(this).children("div").eq(1).children("div").eq(1).text();

			$("#listModal").css('display','flex');
			$("#listModal h2").text(title);
		});

		$("#listModal .confirmBtn button").click( () => {
			$("#listModal").css('display','none');
		});

		$("#listModal .editBtn").click( () => {
			$("#listModal").css('display','none');
			$("#editModal").css('display','flex');
		});

		$("#addTaskModal .editBtn").click( () => {
			$("#addTaskModal").css('display','none');
			$("#editModal").css('display','flex');
		});
		

		$(".alarmChkBtn").click( () => {
			$("#editModal").css("display","none");
		});

		// 알람기능 -----------------------------------------------------------------
		var alarmSound = document.getElementById("alarm-sound");
	    var alarmInterval;
	    var isAlarmSet = false;

	    $("#alarm-time-display").on("click", function() {
	        if (isAlarmSet) return;

	        var alarmTime = prompt("알람 시간을 설정해주세요 (HH:mm)", "00 : 00");

	        if (alarmTime !== null && alarmTime !== "") {
	            $("#alarm-time-display").text(alarmTime);

	            if (alarmTime === "00:00" || alarmTime === "00 : 00" ) {
		            alert("알람 시간을 설정해주세요.");
		            return;
		        }

		        $("#set-alarm").prop("disabled", true);
		        $("#stop-alarm").prop("disabled", false);
		        isAlarmSet = true;

		        alarmInterval = setInterval(function() {
		            var currentTime = new Date();
		            var currentHour = currentTime.getHours();
		            var currentMinute = currentTime.getMinutes();
		            var alarmHour = parseInt(alarmTime.split(":")[0]);
		            var alarmMinute = parseInt(alarmTime.split(":")[1]);

		            if (currentHour === alarmHour && currentMinute === alarmMinute) {
		                alarmSound.play();
		                clearInterval(alarmInterval);
		                $("#set-alarm").prop("disabled", false);
		                $("#stop-alarm").prop("disabled", true);
		                isAlarmSet = false;
		            }
		        }, 1000);
	        }
	    });

		// 알람기능 -----------------------------------------------------------------
		// Planner event------------------------------------------------------------

		// Planner Write------------------------------------------------------------
		// 파일 선택 시
		$("#thumbnail").click(function() {
            $("#uploadInput").trigger("click");
        });
        $("#uploadInput").change(function() {

            var reader = new FileReader();
            reader.onload = function(e) {
                // 선택한 파일의 데이터 URL을 가져와서 이미지의 src로 설정
                $("#thumbnail").attr("src", e.target.result);
            }
            reader.readAsDataURL(this.files[0]);
        });
        // Planner Write------------------------------------------------------------


        // subFooter

        $(window).on('resize', updateSubFooterPosition);

		function updateSubFooterPosition() {
		    var subFooter = $('#subFooter');
		    if ($(window).scrollTop() + $(window).height() >= $(document).height()) {
		    	console.log($(window).height());
		    	console.log($(document.body).height());
		        // 스크롤이 없는 경우
		        subFooter.css('position', 'fixed');
		    } else {
		        // 스크롤이 있는 경우
		        console.log(123);
		        subFooter.css('position', 'sticky');
		    }
		}
		updateSubFooterPosition();

		// subFooter
	    
		// $("#addTaskModal .confirmBtn button").click( () => {
		// 	let p_id = "sinsung";
		// 	let p_title = $("input[name=p_title]").val();
		// 	let p_content = $("textarea[name=p_content]").val();
		// 	alert(p_title);
		// 	$("#addTaskModal").css('display','none');

		// 	// axios.post("url", {title, content, }, function())
		// })

	}, []);
	
	const [formData, setFormData] = useState({});
	const handleSubmit = (event) => {
		event.preventDefault();
		axios.post('/plan/api/addplan', formData)
		  .then((response) => {
			// Handle success response
		  })
		  .catch((error) => {
			// Handle error response
		  });
	  };

	  const handleChange = (event) => {
		setFormData({
		  ...formData,
		  [event.target.name]: event.target.value
		});
	  };
	

	// useEffect(() => {
	// 	const handleClick = () => {
	// 		let u_id = "sinsung";
	// 		let p_title = $("input[name=p_title]").val();
	// 		let p_content = $("textarea[name=p_content]").val();
	// 		let p_category = $("select[name=p_category]").val();
	// 		let p_startdate = $("input[name=p_startdate]").val();
	// 		let p_enddate = $("input[name=p_enddate]").val();
	// 		let p_starttime = $("input[name=p_starttime]").val();
	// 		let p_endtime = $("input[name=p_endtime]").val();
	// 		let p_remindornot = $("input[name=p_remindornot]").val();


	// 		$("#addTaskModal").css('display','none');
	// 		axios.post("/plan/api/addplan", 
	// 		{
	// 			u_id : u_id,
	// 			p_title : p_title,
	// 			p_content : p_content,
	// 			p_category : p_category,
	// 			p_startdate : p_startdate,
	// 			p_enddate : p_enddate,
	// 			p_starttime : p_starttime,
	// 			p_endtime : p_endtime,
	// 			p_remindornot : p_remindornot
	// 		 })
	// 		.then(response=>{
	// 			alert(response);
	// 		})
	// 		.error(error => {
	// 			alert(error);
	// 		});
	// 	};
		
	// 	$("#addTaskModal .confirmBtn button").on('click', handleClick);
		
	// 	// 이 부분이 추가된 부분입니다.
	// 	return () => {
	// 		$("#addTaskModal .confirmBtn button").off('click', handleClick);
	// 	};
	// }, []);

	
  const [plans, setPlans]=useState([]);
  useEffect(() => {
	axios.get('/plan/api/dailyplan')
	  .then(response => setPlans(response.data))
	  .catch(error => console.log(error));
  }, []);
  
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
    <div>
      <h1>Today's Plan List</h1>
      {plans.length > 0 ? (
        plans.map(plan => (
          <div key={plan.p_id}>
            <h2>{plan.p_title}</h2>
            <p>{plan.p_content}</p>
            <p>Start Date: {plan.p_startdate}</p>
            <p>Start Time: {plan.p_starttime}</p>
            <p>End Date: {plan.p_enddate}</p>
            <p>End Time: {plan.p_endtime}</p>
            <p>Category: {plan.p_category}</p>
            <p>Remind: {plan.p_remindornot ? 'Yes' : 'No'}</p>
          </div>
        ))
      ) : (
        <p>Loading...</p>
      )}
    </div>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>

      
    	{/* <!-- subHeader Start --> */}
	<div id="subHeader" className="container">
		<div className="container_inner">
			<div>
				<ul>
					<li className="sub_logo">
						<p>갓생플래너</p>
						<img src="/img/logo.png" alt="logo" />
					</li>
					<li className="sub_burger">
						<span></span>
						<span></span>
						<span></span>
					</li>
				</ul>
			</div>
		</div>
	</div>
  {/* <!-- subHeader End --> */}
	{/* <!-- planner_content Start --> */}
	<div id="planner_content" className="container">
		<div className="container_inner">
			<div>
				<ul>
					<li className="planner_profile">
						<div className="pl_pro_img">
							<img src="/img/profile.png" alt="profile" />
							<p>@sinsung test</p>
						</div>
						<div className="pl_pro_text">
							<p>영진상사</p>
							<p>lv. 10</p>

						</div>
					</li>
					<li className="planner_calendar">
          <div id="datepickerDiv" style={{cursor: "pointer", marginBottom:"10px", padding: "10px 0", width:"120px", textAlign: "center", border: "1px solid #ccc", display: "inline-block"}}>
            달력 보기
          </div>
						<input type="text" id="selected-date-input" />
						<div id="datepickerUI"></div>
					</li>
					<li className="planner_inputArea">
						<div>
							<ul>
								<li className="list_no_12">아침 8시에 기상하기</li>
								<li className="list_no_2">강아지 산책시키기</li>
								<li className="list_no_3">책 읽고 독후감 쓰기</li>
								<div className="btn_li">
									<button id="addTaskBtn">할 일 추가 <span>+</span> </button>
								</div>
								<div className="modal" id="listModal">
									<div className="modal-content">
										<h2>아침 8시에 기상하기</h2>
										<div className="btn_area">
											<button className="editBtn"><img src="/img/edit.png" alt="edit" />시간수정</button>
											<button><img src="/img/bin.png" alt="bin" />삭제하기</button>
										</div>
										<div className="text_area">
											<textarea name="" id="" >9시 수영 수업들으러 가야함!!</textarea>
										</div>
										<div className="etcClass">
											<p>기간 : 2023.04.15 ~ 2023.04.17</p>
											<p>시간 : 09:00 ~ 11:00</p>
										</div>
										<div className="editReminder">
											<div className="confirmBtn">
												<button>확인</button>
											</div>
											<div className="reminder_btn">
												<label for="chk_reminder"><img src="/img/reminders.png" alt="reminders" />리마인더 설정</label>
												<input type="checkbox" id="chk_reminder" name="chk_reminder" />
											</div>
										</div>
									</div>
								</div>
								<div id="editModal" className="modal">
									<div className="modal-content">
										<h2>시간 설정</h2>
										{/* <!-- <div className="btn_area">
											<button>오전</button>
											<button>오후</button>
										</div> --> */}
										<div id="alarm-wrapper">
									        <div id="alarm-time-display">00 : 00</div>
									    </div>
									    <audio id="alarm-sound">
									        <source src="/audio/alarm.mp3" type="audio/mpeg" />
									        Your browser does not support the audio element.
									    </audio>

									    <button className="alarmChkBtn">확인</button>
									</div>
								</div>
								<div id="addTaskModal" class="modal">
									<div class="modal-content">
										<form onSubmit={handleSubmit}>
											<label for="plan_title">제목</label>
											<input id="plan_title" type="text" name="p_title" onChange={handleChange} />
											<select name="p_category" id="" onChange={handleChange}>
												<option value="null">카테고리 선택</option>
												<option value="1">일상</option>
												<option value="2">운동</option>
												<option value="3">공부</option>
												<option value="4">취미</option>
											</select>
											<div class="btn_area">
												<button class="editBtn"><img src="./img/edit.png" alt="edit" />시간수정</button>
												<button><img src="./img/bin.png" alt="bin" />삭제하기</button>
											</div>
											<div class="text_area">
												<textarea name="p_content" id="" onChange={handleChange}></textarea>
											</div>
											<div class="add_date">
												<label for="s_date">시작일 : </label>
												<input type="date" id="s_date" name="p_startdate" onChange={handleChange} />
												<label for="e_date">종료일 : </label>
												<input type="date" id="e_date" name="p_enddate" onChange={handleChange}/>
											</div>
											<div class="add_time">
												<label for="s_time">시작시간 : </label>
												<input type="time" id="s_time" name="p_starttime" onChange={handleChange}/>
												<label for="e_time">종료시간 : </label>
												<input type="time" id="e_time" name="p_endtime" onChange={handleChange}/>
											</div>
											<div class="editReminder">
												<div class="confirmBtn">
													<button>확인</button>
												</div>
												<div class="reminder_btn">
													<label for="chk_reminder_w"><img src="./img/reminders.png" alt="reminders" />리마인더 설정</label>
													<input type="checkbox" id="chk_reminder_w" name="p_remindornot" onChange={handleChange} />
												</div>
											</div>
										</form>
									</div>
								</div>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
  {/* <!-- planner_content End --> */}
	{/* <!-- subFooter Start --> */}
	<div id="subFooter" className="container">
		<div className="container_inner">
			<div>
				<ul>
					<li><a href="/planner.html"><img src="/img/home.png" alt="home"/></a></li>
					<li><a href="/board.html"><img src="/img/list.png" alt="list"/></a></li>
					<li><a href="/challenge.html"><img src="/img/flag.png" alt="flag"/></a></li>
					<li><a href="/user.html"><img src="/img/user.png" alt="user"/></a></li>
				</ul>
			</div>
		</div>
	</div>
  {/* <!-- subFooter end --> */}
  
    </div>

    
  );
}

export default App;
