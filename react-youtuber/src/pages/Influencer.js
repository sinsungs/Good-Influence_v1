import '../App.css';
import axios from 'axios';
import data from '../data/data.js';

import React, {useState, useEffect} from 'react';

function Influencer() {

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
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [likes, setLikes] = useState(0);
  
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
      title,
      content,
      likes,
    };

    const formData  = new FormData();

    formData.append('dto', new Blob([JSON.stringify(influencerData)], { type: "application/json" }));
    formData.append('file', file);

    // 예시: 데이터를 서버로 전송하는 코드
    axios.post('/influencer/register', formData)
      .then(response => {
        // 성공적으로 데이터를 전송한 후에 수행할 작업을 여기에 작성하세요.
        console.log('influencer 엔티티가 생성되었습니다.', response.data);
        alert('influencer 엔티티가 생성되었습니다.', response.data);
        handleModalClose();
      })
      .catch(error => {
        // 오류 처리 로직을 작성하세요.
        console.error('데이터 전송 중 오류가 발생했습니다.', error);
      });
  };

  const [influencers, setInfluencers] = useState([]);

  useEffect(() => {
    // 서버에서 Youtuber 데이터를 가져오는 요청을 수행합니다.
    axios.get('/influencer/list')
      .then(response => {
        // 요청에 성공하면 받아온 데이터를 상태로 설정합니다.
        console.log(response.data);
        setInfluencers(response.data);
        setImagePath(response.data[4].image_url);
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
      alert('추천받기 성공 console을 확인하세요')
      // console.log(response);
      console.log(response);
      console.log(response.data.items[0].snippet);
      console.log(response.data.items[0].snippet.channelTitle);
    })
    .catch((error) => {
      console.error('Error:', error);
    });

  };
  




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
            <div className="y-box-header"><img src={imagePath} alt="s" />{influencers.name}</div>
            <div className="y-box-body">{influencers.title}{influencers.content}</div>
            <div className="y-box-footer">
                <span className="y-box-name">by {influencers.name}{influencers.sns}</span>

              
                <span className="y-box-like">♥ {influencers.likes}</span>
            </div>
          </div>
        ))}


{isModalOpen && (
        <div className="modal">
            <div className="modal-content">
              <h3>인플루언서 등록하기</h3>

          <form onSubmit={handleSubmit}>
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
              <label htmlFor="title">제목 :</label>
              <input
                type="text" id="title"
                value={title} onChange={(event) => setTitle(event.target.value)}/>
            </div>

            <div>
              <label htmlFor="content">소개 :</label>
              <textarea
                id="content"
                value={content} onChange={(event) => setContent(event.target.value)}
              />
            </div>

            <div>
              <label htmlFor="likes">Likes:</label>
              <input
                type="number" id="likes"
                value={likes} onChange={(event) => setLikes(event.target.value)}
              />
            </div>

            <div>
              <label htmlFor="image">Image:</label>
              <input
                type="file"
                id="image"
                accept="image/*" // 이미지 파일만 업로드 가능하도록 설정
                onChange={handleImageUpload}
              />
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
