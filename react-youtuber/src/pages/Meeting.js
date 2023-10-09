// import React from 'react';
import React, {useState, useEffect} from 'react';
import '../App.css';
import axios from 'axios';
import { useRecoilValue } from 'recoil';
import { tokenState } from './JwtTokenState';


function Meeting() {

    // Recoil을 사용하여 JWT 토큰을 가져옵니다
    const jwtToken = useRecoilValue(tokenState); 
    
    const [meets, setMeets] = useState([]);

    const [isModalOpen, setIsModalOpen] = useState(false);
  
    const handleRectangleClick = () => {

      if (!jwtToken) {
        alert('로그인이 필요합니다.');
        return;
      }
  
      setIsModalOpen(true);
    };

    const handleModalClose = () => {
        setIsModalOpen(false);
      };
    

    useEffect(() => {
        // 서버에서 데이터 가져오기
        axios.get('/api/meet/list')
        .then(response => {
            setMeets(response.data);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
    }, []);

    const [maxplayers, setMaxPlayers] = useState(10);
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [region, setRegion] = useState('');
    const [meettime, setMeettime] = useState('');
    // const [imageFile, setImageFile] = useState('');
  
    const handleSubmit = (event) => {

      event.preventDefault();



      if (!jwtToken) {
        // JWT 토큰이 없으면 메시지를 출력하고 POST 요청을 보내지 않음
        alert('로그인이 필요합니다.');
        return;
      }
  
      // 엔티티 데이터를 생성하고 서버로 전송하는 로직을 작성하세요.
      const meetData = {
        title,
        content,
        region,
        maxplayers,
        meettime,
        // imageFile
  
      };

      if (!title || !content || !region || !meettime) {
        alert('모든 필수 입력란을 채워주세요.');
        return;
      }
  
      // 예시: 데이터를 서버로 전송하는 코드
      axios.post('/api/meet/create', meetData, {
        headers: {
          Authorization: `Bearer ${jwtToken}`, // JWT 토큰을 헤더에 추가
        },
      })
        .then(response => {
          // 성공적으로 데이터를 전송한 후에 수행할 작업을 여기에 작성하세요.
          console.log('meet 엔티티가 생성되었습니다.', response.data);
          alert('소셜 모임을 생성했습니다.');
          handleModalClose();
          window.location.reload();
        })
        .catch(error => {
          // 오류 처리 로직을 작성하세요.
          console.error('데이터 전송 중 오류가 발생했습니다.', error);
        });
    };

    const registerMeeting = (meetid) => {


      if (!jwtToken) {
        // JWT 토큰이 없으면 메시지를 출력하고 POST 요청을 보내지 않음
        alert('로그인이 필요합니다.');
        return;
      }

      
      alert('소셜 모임을 신청하시겠습니까 ?')
    
      axios.post(`/api/meeting/register/${meetid}`,
        {
          price : 10000
        }, {
        headers: {
          Authorization: `Bearer ${jwtToken}`, // JWT 토큰을 헤더에 추가
        },
      })


        .then(response => {
          // 성공적으로 데이터를 전송한 후에 수행할 작업을 여기에 작성하세요.
          console.log(response.data);
          alert(response.data);
          window.location.reload();
          // handleModalClose();
        })
        .catch(error => {
          // 오류 처리 로직을 작성하세요.
          console.error('데이터 전송 중 오류가 발생했습니다.', error);
        });

    }


    const deleteMeeting = (meetid) => {


      if (!jwtToken) {
        // JWT 토큰이 없으면 메시지를 출력하고 POST 요청을 보내지 않음
        alert('로그인이 필요합니다.');
        return;
      }

      
      alert('소셜 모임을 취소 하시겠습니까 ?')
    
      axios.delete(`/api/meeting/delete/${meetid}`,
      {
        headers: {
          Authorization: `Bearer ${jwtToken}`, // JWT 토큰을 헤더에 추가
        },
      })

        .then(response => {
          // 성공적으로 데이터를 전송한 후에 수행할 작업을 여기에 작성하세요.
          console.log(response.data);
          alert(response.data);
          window.location.reload();
          // handleModalClose();
        })
        .catch(error => {
          // 오류 처리 로직을 작성하세요.
          console.error('데이터 전송 중 오류가 발생했습니다.', error);
        });

    }


    const deleteMeet = (meetid) => {


      if (!jwtToken) {
        // JWT 토큰이 없으면 메시지를 출력하고 POST 요청을 보내지 않음
        alert('로그인이 필요합니다.');
        return;
      }

      
      alert('소셜 모임을 삭제 하시겠습니까 ?')
    
      axios.delete(`/api/meet/delete/${meetid}`,
      {
        headers: {
          Authorization: `Bearer ${jwtToken}`, // JWT 토큰을 헤더에 추가
        },
      })

        .then(response => {
          // 성공적으로 데이터를 전송한 후에 수행할 작업을 여기에 작성하세요.
          console.log(response.data);
          alert(response.data);
          window.location.reload();
          // handleModalClose();
        })
        .catch(error => {
          // 오류 처리 로직을 작성하세요.
          console.error('데이터 전송 중 오류가 발생했습니다.', error);
        });

    }


return (
    <div className="App">
        <div className='back'>
            <div className="container">
            <div className="search">
            <button className="login-button" onClick={handleRectangleClick}>
                소셜 모임 만들기
            </button>
            </div>
                <ul>
                    <li class="flex-container">
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>모임 시간</p>
                            </div>
                            <div class="flex-item-center-one">
                                <p>모임 지역</p>
                            </div>
                            <div class="flex-item-center-two">
                                <p>모임장</p>
                            </div>
                            <div class="flex-item-center">
                                <p>모임 내용</p> 
                            </div> 
                            <div class="flex-item-right">
                                {/* <div class="full"> */}
                                    <p>신청 여부</p>
                                {/* </div> */}
                            </div>
                    </li>
                    <hr/>

                    {meets.map(meets => (
                    <div>
                    <li class="flex-container" key={meets.meetid}>
                        {/* <a>  */}
                            <div class="flex-item-left">
                                <p>{meets.meettime}</p>
                            </div>
                            <div class="flex-item-center-one">
                                <h3>{meets.region}</h3>
                            </div> 
                            <div class="flex-item-center-two">
                            <h3 style={{ whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis' }}>
                              {meets.writer}
                            </h3>
                            </div> 
                            <div class="flex-item-center">
                                <h3>{meets.title}</h3>
                                <span>{meets.content}</span> 
                            </div> 

                            <div class="flex-item-right">
                                <div class="full">
                                    현재 : {meets.currentPlayers}명 / {meets.maxplayers}명
                                    <p>{meets.result}</p>
                                    <button onClick={() => registerMeeting(meets.meetid)}>신청하기</button>
                                    <button onClick={() => deleteMeeting(meets.meetid)} style={{backgroundColor:"black"}}>취소하기</button>

                                </div>
                            </div>

                              

                    </li>

                    <div>
                      <button onClick={() => deleteMeet(meets.meetid)} style={{backgroundColor:"red"}}>모임 삭제하기</button>
                    </div>

                    <hr/>


                    </div>



))}
                </ul>

                {isModalOpen && (
        <div className="modal">
            <div className="modal-content">
              <h3>소셜 모임 등록하기</h3>

            <form onSubmit={handleSubmit}>
      {/* <div>
          <label htmlFor="imageFile">Image:</label>
          <input
            type="file"
            accept="image/*"
            id="imageFile"
            onChange={(event) => setImageFile(event.target.files[0])}
          />
      </div> */}        

      <div>
        <label htmlFor="title">모임 제목 : </label>
        <input
          type="text" id="title"
          value={title} onChange={(event) => setTitle(event.target.value)}/>
      </div>
      <div>
        <label htmlFor="content">모임 소개 : </label>
        <textarea
          id="content"
          value={content} onChange={(event) => setContent(event.target.value)}
        />
      </div>
      <div>
        <label htmlFor="maxPlayers">수용 인원 : </label>
        <input
          type="number" id="maxPlayers"
          value={maxplayers} onChange={(event) => setMaxPlayers(event.target.value)}  readOnly/>
      </div>

      <div>
        <label htmlFor="region">모임 지역 : </label>
        <input
          type="text" id="region"
          value={region} onChange={(event) => setRegion(event.target.value)}/>
      </div>
      <div>
      <label htmlFor="meettime">모임 날짜 및 시간 선택:</label>
      <input
        type="datetime-local" id="meettime"
        value={meettime} onChange={(event) => setMeettime(event.target.value)}
      />
      <p>선택한 날짜 및 시간: {meettime}</p>
      </div>

      <button type="submit">등록</button>
    </form>

              <button onClick={handleModalClose} style={{float:'right', marginTop:'20px'}}>닫기</button>

          </div>
        </div>
      )}

            </div>
        </div>
    </div>
);
}

export default Meeting;
