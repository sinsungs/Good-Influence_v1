import React from 'react';
import { Link } from 'react-router-dom';

function Header() {
  return (
    <header>
      <div className='top'>
        <h1>My Blog</h1>
        <button class="login-button">로그인</button>
      </div>

      <div className='center'>
        <div class="explore--wrapper">

          <div class="explore--item">
            
            <a href="/explore/51/matches/">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_earlybird.svg" />
              <p>얼리버드</p>
            </a>
            <a href="/explore/51/matches/">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_dribbler.svg" />
              <p>여자 매치</p>
            </a>
            <a href="/explore/51/matches/">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_seeding.svg" />
              <p>스타터</p>
            </a>
            <a href="/explore/51/matches/">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_fire.svg" />
              <p>챌린지</p>
            </a>
            <a href="/explore/51/matches/">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_heart.svg" />
              <p>시작하기</p>
            </a>

          </div>
        </div>

      </div>
      <div className='bottom'>

      <nav>
          <ul>
            <li>
              <Link to="/">Home</Link>
            </li>
            <li>
              <Link to="/about">About</Link>
            </li>
            <li>
              <Link to="/contact">Contact</Link>
            </li>
          </ul>
        </nav>

      </div>




    </header>
  );
}

export default Header;

