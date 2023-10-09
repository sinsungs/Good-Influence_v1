import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function KakaoLoginHandeler() {

  const code = new URL(window.location.href).searchParams.get("code");
  // const [jwtToken, setJwtToken] = useState('');
  console.log(code);
  const navigate = useNavigate();

  useEffect(() => {
    // GET 요청을 보낼 서버의 엔드포인트 URL을 입력합니다.
    const serverURL = '/api/auth/kakao/callback';

    // GET 요청으로 보낼 데이터를 객체로 생성합니다.
    const requestData = {
      code: code,
      // 다른 필요한 데이터를 추가할 수 있습니다.
    };

    // GET 요청을 보냅니다.
    axios.get(serverURL, {
      params: requestData, // 데이터를 쿼리 매개변수로 전달합니다.
    })
      .then((response) => {

        const jwt = response.data;
        console.log(jwt); // 서버 응답 확인


        // 요청이 성공하면 서버로부터 받은 JWT 토큰을 쿠키에 저장합니다.
        // document.cookie = `jwtToken=${jwt};`;
        document.cookie = `jwtToken=${jwt}; path=/;`;
        // 카카오 로그인은 path 설정 해줘야 한다 ,, ㅠ 

        navigate('/meet');
        window.location.reload();

      })
      .catch((error) => {
        console.error(error); // 오류 처리
      });

  },[code]);

  return (
    // <div className="LoginHandler">
    //   <div className="notice">
    //     <p>로그인 중입니다.</p>
    //     <p>잠시만 기다려주세요.</p>
    //     <div className="spinner"></div>
    //   </div>
    // </div>

    <div className="App">
    <div className='back'>
    <div className="container">

    <div  style={{ display: 'flex', justifyContent: 'center' , width: '100%' }}>

        <div style={{ width: '300px', height: '800px'  }}>

        <div style={{ textAlign: 'center', margin: '50px 0'}}>
          <h1>로그인 중입니다... </h1>
          <h3>잠시만 기다려주세요</h3>
        </div>

    </div>
    </div>
    </div>
    </div>
    </div>
  );
}

export default KakaoLoginHandeler;