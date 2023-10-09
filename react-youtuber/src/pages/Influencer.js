import '../App.css';
import axios from 'axios';
import data from '../data/data.js';
import { useRecoilValue } from 'recoil';
import { tokenState } from './JwtTokenState';

import React, {useState, useEffect} from 'react';

function Influencer() {

  // Recoil을 사용하여 JWT 토큰을 가져옵니다
  const jwtToken = useRecoilValue(tokenState); 

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');

  const handleRectangleClick = () => {
    setIsModalOpen(true);
  };

  const handleModalClose = () => {
    setIsModalOpen(false);
  };

  const handleSearchInputChange = (event) => {
    setSearchQuery(event.target.value);
  };

  const handleSearchSubmit = (event) => {
    event.preventDefault();
    // 검색 실행 로직 구현
    console.log('Search query:', searchQuery);
  };

  const [name, setName] = useState('');
  const [category, setCategory] = useState('');
  const [content, setContent] = useState('');
  
  const [file, setFile] = useState(null);
  const [imagePath, setImagePath] = useState('');

  const handleImageUpload = (event) => {
    const selectedImage = event.target.files[0];
    setFile(selectedImage);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    

    // 엔티티 데이터를 생성하고 서버로 전송하는 로직을 작성하세요.
    const influencerData = {
      name,
      content,
      category,
      sns
    };

    if (!name || !content || !category || !sns || !file) {
      alert('모든 필수 입력란을 채워주세요.');
      return;
    }

    const formData  = new FormData();

    formData.append('dto', new Blob([JSON.stringify(influencerData)], { type: "application/json" }));
    formData.append('file', file);

    // 예시: 데이터를 서버로 전송하는 코드
    axios.post('/api/influencer/register', formData)
      .then(response => {
        // 성공적으로 데이터를 전송한 후에 수행할 작업을 여기에 작성하세요.
        console.log('influencer 엔티티가 생성되었습니다.', response.data);
        alert('인플루언서 생성 완료');
        handleModalClose();
        window.location.reload();
      })
      .catch(error => {
        // 오류 처리 로직을 작성하세요.
        console.error('데이터 전송 중 오류가 발생했습니다.', error);
      });
  };

  const [influencers, setInfluencers] = useState([]);

  useEffect(() => {
    // 서버에서 Youtuber 데이터를 가져오는 요청을 수행합니다.
    axios.get('/api/influencer/list')
      .then(response => {
        // 요청에 성공하면 받아온 데이터를 상태로 설정합니다.
        console.log(response.data);
        setInfluencers(response.data);
        // setImagePath(response.data[4].image_url);

        // (response.data.verifyuser)
      })
      .catch(error => {
        // 오류 처리 로직을 작성하세요.
        console.error('데이터를 가져오는 중 오류가 발생했습니다.', error);
      });
  }, []);


  const handleYoutuberClick = () => {
    const APIKey = 'AIzaSyCCMXVU0v7DG21AGf7XzRdtF5lSz-RqHmU'
    // axios.get('https://www.googleapis.com/youtube/v3/search', {
    //   params: {
    //     part: 'snippet',
    //     key: APIKey,
    //     // type: 'video',
    //     // q: '핫식스',
    //     // channelId: 'UCIVTQcwt4K23mOokm5karKA',
    //     // order: 'date',
    //     // eventType: 'live',
    //     type : 'channel',
    //     regionCode : "KR",
    //     maxResults: 10,
    //   },
    // })
    axios.get('https://www.googleapis.com/youtube/v3/videos', {
      params: {
        part: 'snippet',
        key: APIKey,
        // type: 'video',
        // q: '핫식스',
        // channelId: 'UCIVTQcwt4K23mOokm5karKA',
        // order: 'date',
        // eventType: 'live',
        // type : 'channel',
        // regionCode : "KR",
        id : "JRRWcsPWi0c",
        maxResults: 10,
      },
    })
    .then((response) => {
      // const channelId = response.data.items[0].snippet.channelId;
      // this.getChannelLists(channelId, APIKey);
      // alert('추천받기 성공 console을 확인하세요')
      // console.log(response);
      console.log(response);
      console.log(response.data.items[0].snippet);
      console.log(response.data.items[0].snippet.channelTitle);
      alert(response.data.items[0].snippet.channelTitle);
    })
    .catch((error) => {
      console.error('Error:', error);
    });

  };
  


    const [sns, setSelectedOption] = useState('');
  
    const handleOptionChange = (event) => {
      setSelectedOption(event.target.value);
    };


    const handleVerifyClick = (ino) => {


      if (!jwtToken) {
        // JWT 토큰이 없으면 메시지를 출력하고 POST 요청을 보내지 않음
        alert('로그인이 필요합니다.');
        return;
      }

      
      alert('인플루언서에 유저 인증을 요청하시겠습니까 ?')
    
      axios.post(`/api/influencer/verify/${ino}`, {}, {
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
              인플루언서 등록하기
          </button>
          <input className="search-input" placeholder="인플루언서 검색" maxlength="30" type="text" value=""></input>
          <button className="login-button" onClick={handleYoutuberClick} style={{ float: 'left', backgroundColor: 'red' }}>
              인플루언서 추천받기
          </button>


        </div>

        {influencers.map((influencers) => (
          <div className="y-box" key={influencers.ino}>
            {/* <div className="y-box-header"><img src={imagePath} alt="s" style={{ width: '100%', height: '150px' }}/></div> */}
            <div className="y-box-header"><img src={influencers.image_url} alt="image" style={{ width: '100%', height: '150px' }}/></div>

            <div className="y-box-body">
              이름 : {influencers.name}<br/>
              소개 : {influencers.content}<br/>
              카테고리 : {influencers.category}<br/>

            {/* if({influencers.verifyuser} == null){
              <button onClick={() => handleVerifyClick(influencers.ino)} style={{float:'right', backgroundColor:'black'} }>유저 인증</button>
            } else {
              {influencers.verifyuser}
            } */}
            
            {(influencers.verifyuser === null) || (influencers.verifyuser == '관리자') ? (
              <button onClick={() => handleVerifyClick(influencers.ino)} style={{ float: 'right', backgroundColor: 'black' }}>유저 인증 필요</button>
            ) : (
              <span><button style={{ float: 'right', backgroundColor: 'red' }}>닉네임={influencers.verifyuser}</button></span>
            )}

            </div>
            <div className="y-box-footer">
                <span className="y-box-name"></span>
                sns = {influencers.sns}<br/>
              
                <span className="y-box-like">♥ {influencers.likes}</span>
            </div>

          </div>
        ))}


{isModalOpen && (
        <div className="modal">
            <div className="modal-content">
              <h3>인플루언서 생성하기</h3>

          <form onSubmit={handleSubmit}>

          <div>
              <label htmlFor="image">프로필 사진 :</label>
              <input
                type="file"
                id="image"
                accept="image/*" // 이미지 파일만 업로드 가능하도록 설정
                onChange={handleImageUpload}
              />
            </div>



            <div>
              <label htmlFor="name">이름 :</label>
              <input
                type="text"
                id="name"
                value={name}
                onChange={(event) => setName(event.target.value)}
              />
            </div>          

            <div>
              <label htmlFor="content">소개 :</label>
              <textarea
                id="content"
                value={content} onChange={(event) => setContent(event.target.value)}
              />
            </div>


            <div>
      <label>
        <label htmlFor="sns">활동 SNS :</label>
        <input
          type="radio"
          name="sns"
          value="YouTube"
          checked={sns === 'YouTube'}
          onChange={handleOptionChange}
        />
        YouTuber
      </label>
      <label>
        <input
          type="radio"
          name="sns"
          value="Instagram"
          checked={sns === 'Instagram'}
          onChange={handleOptionChange}
        />
        Instagram
      </label>
      <label>
        <input
          type="radio"
          name="sns"
          value="GoodInfluencer"
          checked={sns === 'GoodInfluencer'}
          onChange={handleOptionChange}
        />
        Good Influencer
      </label>

    </div>
    <div>
              <label htmlFor="category">카테고리 :</label>
              <input
                type="text" id="category"
                value={category} onChange={(event) => setCategory(event.target.value)}/>
            </div>

            <button type="submit">등록</button>
          </form>

              <button onClick={handleModalClose} style={{float:'right', marginTop:'20px'}}>닫기</button>

          </div>
        </div>
      )}

{/* {isModalOpen && (
        <div className="modal">
            <div className="modal-content">
              <form onSubmit={handleSearchSubmit}>
                <input
                  type="text"
                  value={searchQuery}
                  onChange={handleSearchInputChange}
                  placeholder="Enter your search query"
                />
                <button type="submit">유튜버 메인 url 입력</button>
                <p>url 인증 성공 시 자동 등록됨</p>
              </form>
              <button onClick={handleModalClose}>Close</button>
          </div>
        </div>
      )} */}


      </div>
      </div>
    </div>
  );
}

export default Influencer;
