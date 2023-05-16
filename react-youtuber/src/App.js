import logo from './logo.svg';
import './App.css';
// import $ from "jquery";
import axios from 'axios';

import React, {useState, useEffect} from 'react';
// import $ from "jquery";
// import axios from 'axios';
function App() {

  const [msg, setMsg] = useState([]);
  useEffect(() => {
    fetch("/api/hello")
        .then((res) => {return res.json();})
        .then((data) => {setMsg(data);})
  }, []);

  // useEffect(() => {
	// 	// "할일 추가" 버튼 클릭 시, 모달 창 보이기
	// 	$('#addTaskBtn').click(function() {
	// 		$('#addTaskModal').css('display', 'flex');
	// 	});

	// 	// // "닫기" 버튼 클릭 시, 모달 창 숨기기
	// 	// $('#closeModalBtn').click(function() {
	// 	// 	$('#addTaskModal').css('display', 'none');
	// 	// });

	// 	// 할일 추가 폼 제출 시, 모달 창 숨기기
	// 	$('#addTaskForm').submit(function(event) {
	// 		event.preventDefault();
	// 		$('#addTaskModal').css('display', 'none');
	// 		// 추가할일 처리 로직 작성
	// 	});
  //   }, []);

  //   const [formData, setFormData] = useState({});
  //   const handleSubmit = (event) => {
  //     event.preventDefault();
  //     axios.post('/api/addplan', formData)
  //       .then((response) => {
  //       // Handle success response
  //       })
  //       .catch((error) => {
  //       // Handle error response
  //       });
  //     };
  
  //     const handleChange = (event) => {
  //     setFormData({
  //       ...formData,
  //       [event.target.name]: event.target.value
  //     });
  //     };


    const [formData, setFormData] = useState({
      title: '',
      content: '',
      writerEmail: ''
    });
  
    const handleSubmit = async (e) => {
      e.preventDefault();
  
      try {
        const response = await axios.post('/api/register', formData);
  
        // Handle the response as needed
        console.log('Response:', response);
      } catch (error) {
        console.error('Error:', error);
      }
    };
  
    const handleChange = (e) => {
      setFormData({ ...formData, [e.target.name]: e.target.value });
    };

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
          {/* <div>
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
        </div> */}
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>

        <ul>
          {msg.map((content, idx) => <li key={`${idx} - ${content}`}>{content}</li>)}
        </ul>


        <form onSubmit={handleSubmit}>
      <div className="form-group">
        <label>Title</label>
        <input
          type="text"
          className="form-control"
          name="title"
          placeholder="Enter Title"
          value={formData.title}
          onChange={handleChange}
        />
      </div>
      <div className="form-group">
        <label>Content</label>
        <textarea
          className="form-control"
          rows="5"
          name="content"
          value={formData.content}
          onChange={handleChange}
        ></textarea>
      </div>
      <div className="form-group">
        <label>Writer Email</label>
        <input
          type="text"
          className="form-control"
          name="writerEmail"
          placeholder="Writer Email"
          value={formData.writerEmail}
          onChange={handleChange}
        />
      </div>
      <button type="submit" className="btn btn-primary">
        Submit
      </button>
    </form>

      </header>
      
      <div className="container">

          <div className='menu'>
            <div className='menu-body'></div>
            <div className='menu-body'></div>
            <div className='menu-body'></div>
            <div className='menu-body'></div>
          </div>


        <div className="box">
          <div className="box-header">첫번째 박스</div>
          <div className="box-body">프론트엔드에 디자인 시스템 적용하기</div>
          <div className="box-footer">by seo_namu</div>
        </div>
      
        <div className="box">
          <div className="box-header">두번째 박스</div>
          <div className="box-body">프론트엔드에 디자인 시스템 적용하기</div>
          <div className="box-footer">by seo_namu</div>
        </div>
        <div className="box">
          <div className="box-header">세번째 박스</div>
          <div className="box-body">프론트엔드에 디자인 시스템 적용하기</div>
          <div className="box-footer">by seo_namu</div>
        </div>
        <div className="box">
          <div className="box-header">네번째 박스</div>
          <div className="box-body">프론트엔드에 디자인 시스템 적용하기</div>
          <div className="box-footer">by seo_namu</div>
        </div>
      </div>

      <button id="addTaskBtn">할 일 추가 <span>+</span> </button>
      <button id="closeModalBtn">할 일 삭제 <span>+</span> </button>

    {/* container End  */}
{/* 
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
								</div> */}

      
    </div>

    // App End
  );
}

export default App;
