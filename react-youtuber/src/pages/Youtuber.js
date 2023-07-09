import '../App.css';
import $ from "jquery";
import axios from 'axios';
import data from '../data/data.js';

import React, {useState, useEffect} from 'react';

function Youtuber() {


  const [showModal, setShowModal] = useState(false);
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [author, setAuthor] = useState('');
  const [selectedYouTubers, setSelectedYouTubers] = useState([]);

  const handleOpenModal = () => {
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleSubmit = (event) => {

    event.preventDefault();

    setTitle('');
    setDescription('');
    setAuthor('');
    setSelectedYouTubers([]);
  };


  const handleYouTuberSelection = (e) => {
    const options = e.target.options;
    const selectedValues = [];

    for (let i = 0; i < options.length; i++) {
      if (options[i].selected) {
        selectedValues.push(options[i].value);
      }
    }

    setSelectedYouTubers(selectedValues);
  };

  return (
    <div className="App">
      <div className="container">

        <button className="login-button" onClick={handleOpenModal}>
          유튜버 추천하기
        </button>

        {data.map(youtuber => (
          <div className="y-box" key={youtuber.id}>
            <div className="y-box-header">{youtuber.title}</div>
            <div className="y-box-body">{youtuber.description}</div>
            <div className="y-box-footer">
              <a>
                <span className='y-box-name'>by {youtuber.author}</span>
              </a>
              <span className='y-box-like'>
                  ♥ {youtuber.likes}
              </span>
            </div>
          </div>
        ))}


{showModal && (
        <div className="modal">
          <div className="modal-content">
            <span className="close" onClick={handleCloseModal}>
              &times;
            </span>
            <h2>유튜버 추천하기</h2>
            <form onSubmit={handleSubmit}>
              <label>
                게시글 제목
                <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
              </label>
              <label>
                게시글 설명:
                <textarea
                  value={description}
                  onChange={(e) => setDescription(e.target.value)}
                />
              </label>
              <label>
                Author:
                <input type="text" value={author} onChange={(e) => setAuthor(e.target.value)} />
              </label>
              <h3>선택한 유튜버:</h3>
              <ul>
                {selectedYouTubers.map((youTuber) => (
                  <li key={youTuber}>{youTuber}</li>
                ))}
              </ul>
              <h3>유튜버 선택:</h3>
              <select multiple value={selectedYouTubers} onChange={handleYouTuberSelection}>
                <option value="YouTuber1">YouTuber 1</option>
                <option value="YouTuber2">YouTuber 2</option>
                <option value="YouTuber3">YouTuber 3</option>
                {/* 등록되어 있는 다른 유튜버들에 대한 선택 옵션을 추가해주세요 */}
              </select>
              <button type="submit">등록</button>
            </form>
          </div>
        </div>
      )}

      </div>
    </div>
  );
}

export default Youtuber;
