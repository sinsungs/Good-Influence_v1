import logo from '../logo.svg';
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

  const handleOpenModal = () => {
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    // Do something with the entered data (e.g., send it to an API)

    // Reset the form inputs
    setTitle('');
    setDescription('');
    setAuthor('');
  };

  return (
    <div className="App">
      <div className="container">

        <button className="login-button" onClick={handleOpenModal}>
          유튜버 등록하기
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
              <h2>유튜버 등록하기</h2>
              <form onSubmit={handleSubmit}>
                <label>
                  Title:
                  <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} />
                </label>
                <label>
                  Description:
                  <textarea
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                  />
                </label>
                <label>
                  Author:
                  <input type="text" value={author} onChange={(e) => setAuthor(e.target.value)} />
                </label>
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
