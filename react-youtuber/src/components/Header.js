import React from 'react';
import { Link } from 'react-router-dom';

function Header() {
  return (
    <header>
      <div className='top'>
        <h1>Youtuber</h1>
        <button class="mypage-button">내 정보</button>
        <button class="login-button">로그인</button>
      </div>

      <div className='center'>
        <div class="explore--wrapper">

          <div class="explore--item">
            
            <Link to="/youtuber">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_earlybird.svg" />
              <p>유튜버 둘러보기</p>
            </Link>
            <Link to="/list">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_dribbler.svg" />
              <p>유튜버 추천글</p>
            </Link>
            <Link to="/post">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_seeding.svg" />
              <p>추천글 작성하기</p>
            </Link>
            <Link to="/list">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_fire.svg" />
              <p>HOT 유튜버</p>
            </Link>
            <Link to="/youtuber">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_heart.svg" />
              <p>좋아요 유튜버</p>
            </Link>

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

