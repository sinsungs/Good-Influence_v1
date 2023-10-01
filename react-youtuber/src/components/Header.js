import React from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import kakao_login_button from '../img/kakao_login_button.png';
import kakao_payment_button from '../img/kakao_payment_button.png';
import { useNavigate } from 'react-router-dom';
import {useState, useEffect} from 'react';
import { useRecoilValue } from 'recoil';
import { tokenState } from '../pages/JwtTokenState';

function Header() {
      // Recoil을 사용하여 JWT 토큰을 가져옵니다
  const jwtToken = useRecoilValue(tokenState); 

  const navigate = useNavigate();

  const [userProfile, setUserProfile] = useState([]);
  

  const handleKakaoPaymentClick = () => {

    const headers = {
      'Authorization': `Bearer ${jwtToken}`,
    };
  
    const paymentEndpoint = '/payment/ready';

    axios.get(paymentEndpoint, { headers })
      .then(response => {

        console.log('Payment response:', response);

        window.open(response.data, '_blank');

          // // navigate를 사용하여 페이지 이동
          // navigate('/meeting');
      })
      .catch(error => {
        // Handle errors, if necessary
        console.error('Error making payment request:', error);
      });
  };

  const handleLogout = () => {
            // navigate를 사용하여 페이지 이동
            document.cookie = `jwtToken=;`;
            navigate('/meet');
            window.location.reload();
  }

  useEffect(() => {

    if (jwtToken) {


      const headers = {
        'Authorization': `Bearer ${jwtToken}`, // JWT 토큰을 'Bearer' 스키마와 함께 보냅니다.
        'Content-Type': 'application/json', // 요청의 컨텐츠 타입을 지정할 수 있습니다.
      };

      // 사용자 프로필 정보를 가져오는 API 호출
      axios.post('/user/profile', {}, { headers })
        .then(response => {
          // setUserProfile(response.data); // 프로필 정보를 상태 변수에 저장
          // console(userProfile.username);
          setUserProfile(response.data);
          // console.log(userProfile.username)
          console.log(response.data);

        })
        .catch(error => {
          console.error('Error fetching user profile:', error);
        });


    } else {

    }
  }, [jwtToken]);

  return (
    <header>
      <div className='top'>

        <div>
        <Link to="/meet">
          <h1>Good Influence 인플루언스</h1>
        </Link>
        </div>

        <div>

          {/* <button class="mypage-button">내 정보</button> */}

          {/* <Link to="/mypage"><button class="login-button">마이페이지</button></Link> */}
          {/* <Link to="/join"><button class="login-button">회원가입</button></Link>
          <Link to="/login"><button class="login-button">로그인</button></Link>
          
          <button class="login-button">SNS 인증</button>         */}

{jwtToken ? (
            <>
              <button onClick={handleKakaoPaymentClick}>
              <img src={kakao_payment_button} alt='kakao_payment' />
            </button>
              <button onClick={handleLogout}>로그아웃</button>
              <Link to="/mypage">마이페이지</Link><br/>
              {/* <Link to="/profile"></Link> */}
              이메일 : {userProfile.email}<br/>
              닉네임 : {userProfile.username}<br/>
              보유금 : {userProfile.amount}<br/>
              경험치 : {userProfile.experience}
            </>
          ) : (
            <>
              <Link to="/admin"><button className="login-button">관리자</button></Link>
              <Link to="/join"><button className="login-button">회원가입</button></Link>
              <Link to="/login"><button className="login-button">로그인</button></Link>
            </>
          )}
        </div>



        {/* <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=b58919f7c93ec635d5c0b3697d4aac6b&redirect_uri=http://localhost:8080/auth/kakao/callback">
        <img src={kakao_login_button} alt='kakao_image' /></a> */}
        {/* <a href="http://localhost:8080/payment/ready">
        <img src={kakao_payment_button} alt='kakao_payment' /></a> */}


        {/* <form method="post" action="/kakaoPay">
        <button type="button">카카오페이로 결제하기</button>
        </form> */}
      </div>

      <div className='center'>
        <div class="explore--wrapper">

          <div class="explore--item">

            <Link to="/meet">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_heart.svg" />
              <p>인플루언서<br/>소셜 모임</p>
            </Link>
            <Link to="/influencer">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_earlybird.svg" />
              <p>인플루언서<br/>둘러보기</p>
            </Link>
            <Link to="/list">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_dribbler.svg" />
              <p>인플루언서<br/>추천 글</p>
            </Link>
            <Link to="/post">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_seeding.svg" />
              <p>인플루언서<br/>추천글 작성하기</p>
            </Link>
            <Link to="/rank">
              <img src="https://plab-football.s3.amazonaws.com/static/img/explore_fire.svg" />
              <p>HOT 인플루언서</p>
            </Link>

          </div>
        </div>
      </div>

    </header>
  );
}

export default Header;

