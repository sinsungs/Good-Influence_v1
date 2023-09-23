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
      setIsModalOpen(true);
    };

    const handleModalClose = () => {
        setIsModalOpen(false);
      };
    

    useEffect(() => {
        // 서버에서 데이터 가져오기
        axios.get('/meet/list')
        .then(response => {
            setMeets(response.data);
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
    }, []);

    const [maxplayers, setMaxPlayers] = useState(0);
    const [title, setTitle] = useState('');
    const [content, setContent] = useState('');
    const [resion, setResion] = useState('');
    // const [imageFile, setImageFile] = useState('');
  
    const handleSubmit = (event) => {
      event.preventDefault();
  
      // 엔티티 데이터를 생성하고 서버로 전송하는 로직을 작성하세요.
      const meetData = {
        title,
        content,
        resion,
        maxplayers,
        // imageFile
  
      };
  
      // 예시: 데이터를 서버로 전송하는 코드
      axios.post('/meet/create', meetData, {
        headers: {
          Authorization: `Bearer ${jwtToken}`, // JWT 토큰을 헤더에 추가
        },
      })
        .then(response => {
          // 성공적으로 데이터를 전송한 후에 수행할 작업을 여기에 작성하세요.
          console.log('meet 엔티티가 생성되었습니다.', response.data);
          alert('meet 엔티티가 생성되었습니다.', response.data);
          handleModalClose();
        })
        .catch(error => {
          // 오류 처리 로직을 작성하세요.
          console.error('데이터 전송 중 오류가 발생했습니다.', error);
        });
    };

    const registerMeet = (meetid) => {
    
      axios.post(`/meeting/register/${meetid}`,{}, {
        headers: {
          Authorization: `Bearer ${jwtToken}`, // JWT 토큰을 헤더에 추가
        },
      })


        .then(response => {
          // 성공적으로 데이터를 전송한 후에 수행할 작업을 여기에 작성하세요.
          console.log('Usermeet 엔티티가 생성되었습니다.', response.data);
          alert('Usermeet 엔티티가 생성되었습니다.', response.data);
          handleModalClose();
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
                            <div class="flex-item-center">
                                <p>모임 내용</p> 
                            </div> 
                            <div class="flex-item-right">
                                <div class="full">
                                    <p>신청 여부</p>
                                </div>
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
                            <div class="flex-item-center">
                                <h3>{meets.title}</h3>
                                <span>{meets.content}</span> 
                            </div> 
                            <div class="flex-item-right">
                                <div class="full">
                                    <p>{meets.result}</p>
                                    <button onClick={() => registerMeet(meets.meetid)}>신청하기</button>
                                </div>
                            </div>
                    </li>
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
        <label htmlFor="players">수용 인원 : </label>
        <input
          type="text" id="maxPlayers"
          value={maxplayers} onChange={(event) => setMaxPlayers(event.target.value)}/>
      </div>

      <div>
        <label htmlFor="resion">모임 지역 : </label>
        <input
          type="text" id="region"
          value={resion} onChange={(event) => setResion(event.target.value)}/>
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
