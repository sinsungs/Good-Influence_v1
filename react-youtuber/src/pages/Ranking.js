import logo from '../logo.svg';
import '../App.css';
import axios from 'axios';
import React, {useState, useEffect} from 'react';

function Ranking() {

  const [Influencer, setInfluencer] = useState([]);

  useEffect(() => {
    // 서버에서 Youtuber 데이터를 가져오는 요청을 수행합니다.
    axios.get('/post/list')
      .then(response => {
        // 요청에 성공하면 받아온 데이터를 상태로 설정합니다.
        setInfluencer(response.data);
      })
      .catch(error => {
        // 오류 처리 로직을 작성하세요.
        console.error('데이터를 가져오는 중 오류가 발생했습니다.', error);
      });
  }, []);

  return (
    <div className="App">
      <div className='back'>
      <div className="container">
        {Influencer.map(Influencer => (
          <div className="box" key={Influencer.id}>
            <div className="box-header"></div>
            <div className="box-body">

              인플루언서 내용 : {Influencer.postTitle}<br/>
              추천 글 내용 : {Influencer.postContent}<br/>
              추천 인플루언서 : {Influencer.influencerName}
              
            </div>
            <div className="box-footer">
              <a>
                <span className='box-name'>by </span>
              </a>
              <span className='box-like'>
                  ♥ 33
              </span>
            </div>
          </div>
        ))}


<div>
      <h2>Youtuber List</h2>
      <ul>
        {Influencer.map(Influencer => (
          <li>
            <h3>{Influencer.postName}</h3>
            <h3>{Influencer.postContent}</h3>
            <p>Title: {Influencer.influencerName}</p>
            <p>Content: {Influencer.influencerContent}</p>
            {/* <p>Likes: {youtuber.likes}</p>
            <p>WriterEmail: {youtuber.writerEmail}</p>
            <p>Writername: {youtuber.writerName}</p> */}
          </li>
        ))}
      </ul>
</div>

      </div>

      </div>
    </div>
  );
}

export default Ranking;
