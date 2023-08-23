import '../App.css';
import axios from 'axios';
import data from '../data/data.js';

import React, {useState, useEffect} from 'react';

function Youtuber() {

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
  // const [imageFile, setImageFile] = useState('');

  const handleSubmit = (event) => {
    event.preventDefault();

    // 엔티티 데이터를 생성하고 서버로 전송하는 로직을 작성하세요.
    const youtuberData = {
      name,
      title,
      content,
      likes,
      // imageFile

    };

    // 예시: 데이터를 서버로 전송하는 코드
    axios.post('/influencer/register', youtuberData)
      .then(response => {
        // 성공적으로 데이터를 전송한 후에 수행할 작업을 여기에 작성하세요.
        console.log('Youtuber 엔티티가 생성되었습니다.', response.data);
        alert('Youtuber 엔티티가 생성되었습니다.', response.data);
        handleModalClose();
      })
      .catch(error => {
        // 오류 처리 로직을 작성하세요.
        console.error('데이터 전송 중 오류가 발생했습니다.', error);
      });
  };

  const [youtubers, setYoutubers] = useState([]);

  useEffect(() => {
    // 서버에서 Youtuber 데이터를 가져오는 요청을 수행합니다.
    axios.get('/youtuber/list')
      .then(response => {
        // 요청에 성공하면 받아온 데이터를 상태로 설정합니다.
        setYoutubers(response.data);
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

        {data.map((youtuber) => (
          <div className="y-box" key={youtuber.id}>
            <div className="y-box-header">{youtuber.title}</div>
            <div className="y-box-body">{youtuber.description}</div>
            <div className="y-box-footer">
              <a>
                <span className="y-box-name">by {youtuber.author}</span>
              </a>
              <span className="y-box-like">♥ {youtuber.likes}</span>
            </div>
          </div>
        ))}

<div>
      <h2>Youtuber List</h2>
      <ul>
        {youtubers.map(youtuber => (
          <li key={youtuber.yno}>
            <h3>{youtuber.name}</h3>
            <p>Title: {youtuber.title}</p>
            <p>Content: {youtuber.content}</p>
            <p>Likes: {youtuber.likes}</p>
            <p>WriterEmail: {youtuber.writerEmail}</p>
            <p>Writername: {youtuber.writerName}</p>
          </li>
        ))}
      </ul>
</div>





{isModalOpen && (
        <div className="modal">
            <div className="modal-content">
              <h3>인플루언서 등록하기</h3>

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
        <label htmlFor="name">Name:</label>
        <input
          type="text"
          id="name"
          value={name}
          onChange={(event) => setName(event.target.value)}
        />
      </div>          

      <div>
        <label htmlFor="title">Title:</label>
        <input
          type="text"
          id="title"
          value={title}
          onChange={(event) => setTitle(event.target.value)}
        />
      </div>
      <div>
        <label htmlFor="content">Content:</label>
        <textarea
          id="content"
          value={content}
          onChange={(event) => setContent(event.target.value)}
        />
      </div>
      <div>
        <label htmlFor="likes">Likes:</label>
        <input
          type="number"
          id="likes"
          value={likes}
          onChange={(event) => setLikes(event.target.value)}
        />
      </div>
      <div>
        {/* <label htmlFor="writer">Writer:</label>
        <input
          type="text"
          id="writer"
          value={writerEmail}
          onChange={(event) => setWriter(event.target.value)}
        /> */}
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

export default Youtuber;
