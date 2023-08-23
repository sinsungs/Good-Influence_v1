import logo from '../logo.svg';
import '../App.css';
import $ from "jquery";
import axios from 'axios';
import data from '../data/data.js';
import React, {useState, useEffect} from 'react';

function YoutuberList() {

  const [youtubers, setYoutubers] = useState([]);

  useEffect(() => {
    // 서버에서 Youtuber 데이터를 가져오는 요청을 수행합니다.
    axios.get('/post/list')
      .then(response => {
        // 요청에 성공하면 받아온 데이터를 상태로 설정합니다.
        setYoutubers(response.data);
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
        {data.map(youtuber => (
          <div className="box" key={youtuber.id}>
            <div className="box-header">{youtuber.title}</div>
            <div className="box-body">{youtuber.description}</div>
            <div className="box-footer">
              <a>
                <span className='box-name'>by {youtuber.author}</span>
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
        {youtubers.map(youtuber => (
          <li>
            <h3>{youtuber.postName}</h3>
            <p>Title: {youtuber.youtuberName}</p>
            <p>Content: {youtuber.youtuberContent}</p>
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

export default YoutuberList;
